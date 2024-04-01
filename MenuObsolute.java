package com.menu;
/* Obsoluted - Kept for code reference
package MenuUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> items;

    public Menu() {
        this.items = new ArrayList<>();
    }

    public void loadMenuFromFile(String filename) {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
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
    }


}
*/