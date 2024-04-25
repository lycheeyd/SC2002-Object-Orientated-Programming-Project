package com.cache;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.users.Employee;

public class EmployeeCache extends AppCache<String, Employee, Employee>{
    
    private static EmployeeCache instance;

    private EmployeeCache() {}

    public static synchronized EmployeeCache getInstance() {
        if (instance == null) {
            instance = new EmployeeCache();
        }
        return instance;
    }
 
    @Override
    public void addItem(String loginID, Employee employee) {
        if (cacheItems.containsKey(loginID)) {
            System.out.println("Employee "+ loginID + " already exists.");
        } else {
            cacheItems.put(loginID, employee);
            System.out.println("Employee " + loginID + " successfully added.");
        }
    }

    @SafeVarargs
    @Override
    public final void removeItem(String loginID, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(loginID)) {
            cacheItems.remove(loginID);
            System.out.println(loginID + " is removed.");
        } else {
            System.out.println("Unable to remove employee (Employee not found)");
        }
    }

    @SafeVarargs
    @Override
    public final Employee getItem(String loginID, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(loginID)) {
            return cacheItems.get(loginID);
        } else {
            // System.out.println("Unable to retrieve employee (Employee not found)");
            return null;
        }
    }
    
    @Override
    public void printAllItems(Function<Employee, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }

}
