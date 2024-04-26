package com.datautils;

import com.branch.Branch;
import com.cache.BranchCache;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

/**
 * Implementation of the SaveData interface to save branch data to a CSV file.
 * @see SaveData
 */
public class SaveBranch implements SaveData {

    /**
     * Default constructor.
     */
    public SaveBranch() {};

    /**
     * Saves branch data to a CSV file.
     */
    @Override
    public void saveToFile() {
        BranchCache cache = BranchCache.getInstance();
        File tempFile = new File("branch_list.tmp");
        File realFile = new File("branch_list.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            // Writing header and data
            writer.writeNext(new String[]{"Name","Location","Staff Quota"});
            for (Branch item : cache.getAllItems().values()) {
                writer.writeNext(new String[]{
                    String.valueOf(item.getBranchName()),
                    item.getLocation(),
                    String.valueOf(item.getStaffQuota())
                });
            }

        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to save branch data." + e);
            e.printStackTrace();
            if (tempFile.exists()) {
                tempFile.delete(); // Clean up
            }
        }

        try {
            Files.move(tempFile.toPath(), realFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Branch data successfully saved.");
        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to rename temp file to real file.\n Please manually rename files.\n" + e);
            e.printStackTrace();
        } catch (FileSystemNotFoundException e) {}
        
    }
}
