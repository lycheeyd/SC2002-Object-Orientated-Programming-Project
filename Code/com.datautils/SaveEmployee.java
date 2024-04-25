package com.datautils;

import com.cache.EmployeeCache;
import com.users.Employee;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class SaveEmployee implements SaveData {

    @Override
    public void saveToFile() {
        EmployeeCache cache = EmployeeCache.getInstance();
        File tempFile = new File("staff_list" + ".tmp");
        File realFile = new File("staff_list.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            // Writing header and data
            writer.writeNext(new String[]{"Name","Staff Login ID","Role","Gender","Age","Branch","Password"});
            for (Employee item : cache.getAllItems().values()) {
                writer.writeNext(new String[]{
                    item.getName(),
                    item.getLoginID(),
                    String.valueOf(item.getRole()),
                    String.valueOf(item.getGender()),
                    String.valueOf(item.getAge()),
                    String.valueOf(item.getBranch().getBranchName()),
                    item.getPassword()
                });
            }

        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to save employee data." + e);
            e.printStackTrace();
            if (tempFile.exists()) {
                tempFile.delete(); // Clean up
            }
        }

        try {
            Files.move(tempFile.toPath(), realFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Employee data successfully saved.");
        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to rename temp file to real file.\n Please manually rename files.\n" + e);
            e.printStackTrace();
        } catch (FileSystemNotFoundException e) {}

    }
}
