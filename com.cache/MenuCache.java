package com.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.menu.MenuItem;

public class MenuCache extends AppCache<String, List<MenuItem>, MenuItem> {
    
    private static MenuCache instance;

    private MenuCache() {}

    public static synchronized MenuCache getInstance() {
        if (instance == null) {
            instance = new MenuCache();
        }
        return instance;
    }

    @Override
    public void addItem(String name, MenuItem newItem) {
        List<MenuItem> items = cacheItems.computeIfAbsent(name, k -> new ArrayList<>());

        if (items.stream().anyMatch(item -> item.getBranch().equals(newItem.getBranch()))) {
            System.out.println(name + " already exists in branch " + newItem.getBranch().getBranchName() + ".");
        } else {
            items.add(newItem);
            System.out.println(name + " added to menu of " + newItem.getBranch().getBranchName());
        }
    }

    @SafeVarargs
    @Override
    public final void removeItem(String name, Optional<Predicate<MenuItem>>... filters) {
        Predicate<MenuItem> combinedFilters = Stream.of(filters)
                                                    .filter(Optional::isPresent)
                                                    .map(Optional::get)
                                                    .reduce(Predicate::and)
                                                    .orElseGet(() -> {
                                                        System.out.println("Operation terminated prematurely. [Code Error] No filter provided.");
                                                        return x -> false;
                                                    }); // Defaults to false if no filters provided - Nothing is removed.

        if (cacheItems.containsKey(name)) {
            List<MenuItem> items = cacheItems.get(name);
            if (items != null && !items.isEmpty()) {
                List<MenuItem> removedItems = items.stream()
                                                .filter(combinedFilters)
                                                .collect(Collectors.toList());
                if (!removedItems.isEmpty()) {
                    removedItems.forEach(item -> {
                                            items.remove(item);
                                            System.out.println("Removed item: " + item);
                });
                    if (items.isEmpty()) {
                        cacheItems.remove(name);
                    }
                } else {
                    System.out.println("No item found with name " + name + "that matches specified conditions.");
                }
            } else {
                cacheItems.remove(name); // Empty key found. Deletes empty key.
            }
        } else {
            System.out.println("Unble to remove item. (Item not found)");
        }
    }

    @SafeVarargs
    @Override
    public final List<MenuItem> getItem(String name, Optional<Predicate<MenuItem>>... filters) {
        List<MenuItem> items = cacheItems.get(name);
        if (items == null) {
            // System.out.println("Unable to retrieve item. (Item not found)");
            return null;
        }

        Predicate<MenuItem> combinedFilters = Stream.of(filters)
                                                    .filter(Optional::isPresent)
                                                    .map(Optional::get)
                                                    .reduce(Predicate::and)
                                                    .orElse(item -> true); // Defaults to return the specified MenuItems in all Branches

        return items.stream()
                    .filter(combinedFilters)
                    .collect(Collectors.toList());
    }

    @Override
    public void printAllItems(Function<MenuItem, ?> function) {
        cacheItems.forEach((key, items) -> {
            System.out.println("MenuItem: " + key);
            items.stream()
                .map(function)
                .forEach(System.out::println);
        });
    }

/*
    public void removeItem(String name, BranchName branch) {
        List<MenuItem> items = cacheItems.get(name);
        if (items != null) {
            items.removeIf(item -> item.getBranch().getBranchName().equals(branch));
            if (items.isEmpty()) {
                cacheItems.remove(name);
            }
        }
    }
*/
/*
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
*/

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
