package com.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.menu.MenuItem;

/**
* Singleton class to manage caching of MenuItem objects.
* Ensures that each MenuItem is uniquely identified by its name
* @see AppCache
*/
public class MenuCache extends AppCache<String, List<MenuItem>, MenuItem> {
    
    /** The singleton instance of MenuCache. */
    private static MenuCache instance;

    /**
    * Constructs an EmployeeCache object.
    * Private constructor to prevent direct instantiation.
    */
    private MenuCache() {}

    
    /**
    * Retrieves the singleton instance of MenuCache.
    *
    * @return The singleton instance of MenuCache.
    */
    public static synchronized MenuCache getInstance() {
        if (instance == null) {
            instance = new MenuCache();
        }
        return instance;
    }

    /**
    * Adds an MenuItem to the cache with the specified name
    * If a MenuItem with the same name already exists in the cache, an error message is printed.
    *
    * @param name The name of the new Menu Item
    * @param newItem The new item to be added to the cache.
    */
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

    /**
    * Removes a Menu Item from the cache using the specified name and optional filters.
    * If the name is not found in the cache, an error message is printed.
    *
    * @param name The name of the Menu Item to remove
    * @param filters Optional filters to apply when removing the menu item
    */
    @SafeVarargs
    @Override
    public final void removeItem(String name, Optional<Predicate<Object>>... filters) {
        Predicate<Object> combinedFilters = Stream.of(filters)
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
    
    /**
    * Retrieves a menu item from the cache using the specified name and optional filters.
    * If the menu item is not found in the cache, null is returned.
    *
    * @param name The name of the Menu item to retrieve.
    * @param filters Optional filters to apply when retrieving the Menu Item
    * @return The list containing the retrieved Menu Item object, or null if not found.
    */
    @SafeVarargs
    @Override
    public final List<MenuItem> getItem(String name, Optional<Predicate<Object>>... filters) {
        List<MenuItem> items = cacheItems.get(name);
        if (items == null) {
            // System.out.println("Unable to retrieve item. (Item not found)");
            return null;
        }

        Predicate<Object> combinedFilters = Stream.of(filters)
                                                    .filter(Optional::isPresent)
                                                    .map(Optional::get)
                                                    .reduce(Predicate::and)
                                                    .orElse(item -> true); // Defaults to return the specified MenuItems in all Branches

        return items.stream()
                    .filter(combinedFilters)
                    .collect(Collectors.toList());
    }

    /**
    * Prints all Menu items in the cache using the provided function for formatting.
    *
    * @param function The function to format the menu items for printing.
    */
    @Override
    public void printAllItems(Function<MenuItem, ?> function) {
        cacheItems.forEach((key, items) -> {
            System.out.println("MenuItem: " + key);
            items.stream()
                .map(function)
                .forEach(System.out::println);
        });
    }

}
