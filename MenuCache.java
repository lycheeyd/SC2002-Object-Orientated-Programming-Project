package com.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.branch.BranchName;
import com.menu.MenuItem;
import com.menu.menuCategory;

public class MenuCache {
    
    private static MenuCache instance;
    private Map<String, List<MenuItem>> menuItem = new HashMap<>();

    private MenuCache() {}

    public static synchronized MenuCache getInstance() {
        if (instance == null) {
            instance = new MenuCache();
        }
        return instance;
    }
-
    public void addMenuItem(String name, MenuItem newItem) {
        menuItem.computeIfAbsent(name, k -> new ArrayList<>()).add(newItem);
    }

    
    public List<MenuItem> getMenuItem(String name) {
        return menuItem.getOrDefault(name, new ArrayList<>());
    }

    // Retrieve tiems by branch
   
    public List<MenuItem> getMenuItem(BranchName branch) {
        return menuItem.values().stream()
                   .flatMap(List::stream)
                   .filter(item -> item.getBranch().getBranchName().equals(branch))
                   .collect(Collectors.toList());
    }

    // Retrieve items by category
    
    public List<MenuItem> getMenuItem(menuCategory category) {
        return menuItem.values().stream()
                   .flatMap(List::stream)
                   .filter(item -> item.getCategory().equals(category))
                   .collect(Collectors.toList());
    }

    // Retrieve items by name, branch, and category
    
    public List<MenuItem> getMenuItem(String name, BranchName branch, menuCategory category) {
        return menuItem.getOrDefault(name, new ArrayList<>()).stream()
                   .filter(item -> item.getBranch().getBranchName().equals(branch) && item.getCategory().equals(category))
                   .collect(Collectors.toList());
    }

    public void printAllItems() {
        menuItem.forEach((key, items) -> {
            System.out.println("MenuItem: " + key);
            items.forEach(System.out::println);
        });

/*      // Same thing, Different Implementation

        for (Map.Entry<String, List<MenuItem>> entry : menuItem.entrySet()) {
            String key = entry.getKey();
            List<MenuItem> items = entry.getValue();
            System.out.println("MenuItem: " + key);
            for (MenuItem item : items) {
                System.out.println(item);
            }
        }
*/
    }
}
