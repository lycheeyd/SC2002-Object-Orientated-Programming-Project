package com.datautils;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import com.branch.Branch;
import com.branch.BranchName;
import com.cache.BranchCache;
import com.cache.MenuCache;
import com.menu.MenuItem;
import com.menu.menuCategory;

public class loadMenu implements loadData {
    
    @Override
    public void loadFile() {
        MenuCache cache = MenuCache.getInstance(); // initialise cache

        try (CSVReader reader = new CSVReader(new FileReader("menu_list.csv"))) {
            reader.readNext();
            String[] line;
            while ((line = reader.readNext()) != null) {
                String name = line[0];
                double price = Double.parseDouble(line[1]);
                BranchName branchName = BranchName.valueOf(line[2].toUpperCase());
                Branch branch = BranchCache.getInstance().getItem(branchName);
                menuCategory category = menuCategory.valueOf(line[3].replace(" ", "_").toUpperCase());
                /* try {
                    category = menuCategory.valueOf(line[3].replace(" ", "_").toUpperCase());
                } catch (IllegalArgumentException e) {
                    System.err.println("[START UP ERROR] " + line[3] + " is not a valid category.");
                    System.exit(4);
                } */

                cache.addItem(name, new MenuItem(name, price, branch, category));
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
