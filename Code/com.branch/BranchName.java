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

public class BranchName implements Predicate<Object> { // this class is design to imitate enum class with added function to add new "enum".

    private String name;
    private static List<String> names = new ArrayList<>(Arrays.asList("NA")); // replaces previously enum
    private static final Map<String, BranchName> instances = new HashMap<>();

    private BranchName(String name) {
        if (names.contains(name.toUpperCase())) {
            this.name = name;
        }
    }

    public static synchronized BranchName use(String name) {
        if (!names.contains(name.toUpperCase())) {
            throw new IllegalArgumentException("Invalid branch name: " + name);
        }
        // Use existing instance or create a new one if it doesn't exist
        return instances.computeIfAbsent(name.toUpperCase(), BranchName::new);
    }

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

    public static List<String> getNames() {
        return names;
    }

    public String getName() {
        return this.name;
    }

    public static void addName(String name) {
        if (!names.contains(name.toUpperCase())) {
            names.add(name.toUpperCase());
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

}
