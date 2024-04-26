package com.datautils;

import com.cache.MenuCache;
import com.menu.MenuItem;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

/**
 * Implementation of the SaveData interface to save menu data to a CSV file.
 * @see SaveData
 */
public class SaveMenu implements SaveData {

    /**
     * Default constructor.
     */
    public SaveMenu() {};

    /**
     * Saves menu data to a CSV file.
     */
    @Override
    public void saveToFile() {
        MenuCache cache = MenuCache.getInstance();
        File tempFile = new File("menu_list" + ".tmp");
        File realFile = new File("menu_list.csv");

        try (CSVWriter writer = new CSVWriter(new FileWriter(tempFile))) {
            // Writing header and data
            writer.writeNext(new String[]{"Name","Price","Branch","Category"});
            for (List<MenuItem> list : cache.getAllItems().values()) {
                for (MenuItem item : list) {
                    writer.writeNext(new String[]{
                        item.getName(),
                        String.valueOf(item.getPrice()),
                        String.valueOf(item.getBranch().getBranchName()),
                        String.valueOf(item.getCategory())
                    });
                }
            }

        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to save menu data." + e);
            e.printStackTrace();
            if (tempFile.exists()) {
                tempFile.delete(); // Clean up
            }
        }

        try {
            Files.move(tempFile.toPath(), realFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Menu data successfully saved.");
        } catch (IOException e) {
            System.out.println("[FILE SAVE ERROR] Failed to rename temp file to real file.\n Please manually rename files.\n" + e);
            e.printStackTrace();
        } catch (FileSystemNotFoundException e) {}

    }
}