package com.datautils;

import com.branch.Branch;
import com.branch.BranchName;
import com.cache.BranchCache;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class loadBranch implements loadData {
    
    @Override
    public void loadFile() {
        BranchCache cache = BranchCache.getInstance(); // initialise cache

        try (CSVReader reader = new CSVReader(new FileReader("branch_list.csv"))) {
            reader.readNext(); // skip header
            String[] line;
            while ((line = reader.readNext()) != null) {
                BranchName branchName = BranchName.valueOf(line[0].toUpperCase());
                String location = line[1];
                int staffQuota = Integer.parseInt(line[2]);

                cache.addBranch(branchName, new Branch(branchName, location, staffQuota));
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