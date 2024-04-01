/*package com.datautils;
import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.branch.Branch;
import com.menu.MenuItem;

public class DataUtils {

    protected static List<Branch> loadFiles(String filePath) {

        }
    

/*        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 4) { //file is formatted: name, price, branch, category
                    String name = parts[0].trim();
                    double price = Double.parseDouble(parts[1].trim());
                    String branch = parts[2].trim();
                    String category = parts[3].trim();
                    MenuItem item = new MenuItem(name, price, branch, category);
                    items.add(item);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
        this.menu = list;
    }


    protected static List<MenuItem> loadFiles(String filePath) {
        List<MenuItem> = items = new ArrayList<> ();
        try (CSVReader reader = new csvReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readNext()) ! null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                double price = Double.parseDouble(parts[1].trim());
                String branch = parts[2].trim();
                String category = parts[3].trim();
                MenuItem item = new MenuItem(name, price, branch, category);
                items.add(item);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: ");
            e.printStackTrace();
        }
        return items;
    }

    public static List<String[]> readCSV(String filePath) {
        List<String[]> records = new ArrayList<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(filePath))) {
            String[] values;
            while ((values = csvReader.readNext()) != null) {
                records.add(values);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (CsvValidationException e) {
            e.printStackTrace();
        }
        return records;
    }
    
    public static void writeCSV(String filePath, List<String[]> dataLines) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {
            writer.writeAll(dataLines);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
*/