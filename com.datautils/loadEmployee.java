package com.datautils;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import com.users.Staff;
import com.users.Manager;
import com.users.Admin;
import com.users.StaffRole;
import com.users.Gender;

import com.cache.BranchCache;
import com.cache.EmployeeCache;

import com.branch.Branch;
import com.branch.BranchName;

public class loadEmployee implements loadData {

    @Override
    public void loadFile() {
        EmployeeCache cache = EmployeeCache.getInstance(); // initialise cache

        try (CSVReader reader = new CSVReader(new FileReader("staff_list.csv"))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String name = line[0];
                String loginID = line[1];
                StaffRole role = StaffRole.valueOf(line[2].toUpperCase());
                Gender gender = Gender.valueOf(line[3].toUpperCase());
                int age = Integer.parseInt(line[4]);
                BranchName branchName;
                try {
                    branchName = BranchName.valueOf(line[5].toUpperCase());
                } catch (IllegalArgumentException e) {
                    branchName = null;
                }
                Branch branch = BranchCache.getInstance().getBranch(branchName);
                String password = line[6];
                
                switch(role) {
                    case S:
                        cache.addEmployee(loginID, new Staff(name, loginID, role, gender, age, branch, password));
                        break;
                    case M:
                        cache.addEmployee(loginID, new Manager(name, loginID, role, gender, age, branch, password));
                        break;
                    case A:
                        cache.addEmployee(loginID, new Admin(name, loginID, role, gender, age, branch, password));
                        break;
                    }
            }
        }
        catch (FileNotFoundException e) {
            System.err.println("[STARTUP ERROR] " + e.getMessage());
            System.exit(1);
        }
        catch (IOException e) {
            System.err.println("[STARTUP ERROR] An error occurred while reading the file: " + e.getMessage());
            System.exit(2);
        }
        catch (CsvValidationException e) {
            System.err.println("[STARTUP ERROR] CSV Validation Exception occurred: " + e.getMessage());
            System.exit(3);
        }
    }
}
