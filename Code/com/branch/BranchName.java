package com.branch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import com.menu.MenuItem;
import com.order.Order;
import com.users.Employee;


/**
 * BranchName is a class that represents branch names and provides functionality similar to an enum with the ability to add new "enum" values dynamically.
 * @version 1.0
 * @since 2024-04-26
 */
public class BranchName implements Predicate<Object> { // this class is design to imitate enum class with added function to add new "enum".

     /** Stores the branch name "Enum" value. */
    private String name;

     /** List of available branch name value mimicing "Enum" constants. */
    private static List<String> names = new ArrayList<>(Arrays.asList("NA")); // replaces previously enum

     /** Hashmap to store single instance of "Enum" branch name values to mimic Enum constants. */
    private static final Map<String, BranchName> instances = new HashMap<>();

    /**
     * Private constructor to create a new BranchName instance.
     * @param name The name of the branch.
     */
    private BranchName(String name) {
        if (names.contains(name.toUpperCase())) {
            this.name = name;
        }
    }
    
    /**
     * Retrieves an existing BranchName instance or creates a new one if it doesn't exist.
     * 
     * @param name The name of the branch.
     * @return BranchName instance.
     * @throws IllegalArgumentException if the provided name is not valid.
     */
    public static synchronized BranchName use(String name) {
        if (!names.contains(name.toUpperCase())) {
            throw new IllegalArgumentException("Invalid branch name: " + name);
        }
        // Use existing instance or create a new one if it doesn't exist
        return instances.computeIfAbsent(name.toUpperCase(), BranchName::new);
    }

    /**
    * Tests if the given object has the same role as this value.
    * @param o The object to test.
    * @return true if the object is a Branch with the respective encapsulations, false otherwise.
    */
    @Override
    public boolean test(Object o) {
        if (o instanceof Branch) {
            return ((Branch) o).getBranchName().equals(this);
        }
        else if (o instanceof MenuItem) {
            return ((MenuItem) o).getBranch().getBranchName().equals(this);
        }
        else if (o instanceof Employee) {
            return ((Employee) o).getBranch().getBranchName().equals(this);
        }
        else if (o instanceof Order) {
            return ((Order) o).getBranchName().equals(this);
        }
        else if (o instanceof BranchName) {
            return ((BranchName) o).equals(this);
        }
        return false;
    }

    /**
     * Gets the list of all available branch names.
     *
     * @return The list of branch names.
     */
    public static List<String> getNames() {
        return names;
    }

    /**
     * Gets the name of the branch.
     *
     * @return The name of the branch.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Adds a new branch name to the list of available names.
     *
     * @param name The name to be added.
     */
    public static void addName(String name) {
        if (!names.contains(name.toUpperCase())) {
            names.add(name.toUpperCase());
        }
    }

    /**
    * Generates a string representation of the branch name.
    * 
    * @return The string representation of the branch name.
    */
    @Override
    public String toString() {
        return this.name;
    }

}
