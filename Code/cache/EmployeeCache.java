package com.cache;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.users.Employee;


/**
* EmployeeCache is a singleton class that manages caching of Employee objects.
* Each employee is uniquely identified by their login ID (String).
* @see AppCache
*/
public class EmployeeCache extends AppCache<String, Employee, Employee>{
    
    /** The singleton instance of EmployeeCache. */
    private static EmployeeCache instance;

    /**
    * Constructs an EmployeeCache object.
    * Private constructor to prevent direct instantiation.
    */
    private EmployeeCache() {}

    
    /**
    * Retrieves the singleton instance of EmployeeCache.
    *
    * @return The singleton instance of EmployeeCache.
    */
    public static synchronized EmployeeCache getInstance() {
        if (instance == null) {
            instance = new EmployeeCache();
        }
        return instance;
    }
 

    /**
    * Adds an employee to the cache with the specified login ID.
    * If an employee with the same login ID already exists in the cache, a message is printed.
    *
    * @param loginID The login ID of the employee.
    * @param employee The employee object to add to the cache.
    */
    @Override
    public void addItem(String loginID, Employee employee) {
        if (cacheItems.containsKey(loginID)) {
            System.out.println("Employee "+ loginID + " already exists.");
        } else {
            cacheItems.put(loginID, employee);
            System.out.println("Employee " + loginID + " successfully added.");
        }
    }

    /**
    * Removes an employee from the cache using the specified login ID and optional filters.
    * If the employee is not found in the cache, a message is printed.
    *
    * @param loginID The login ID of the employee to remove.
    * @param filters Optional filters to apply when removing the employee. Not implemented.
    */
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

    /**
    * Retrieves an employee from the cache using the specified login ID and optional filters.
    * If the employee is not found in the cache, null is returned.
    *
    * @param loginID The login ID of the employee to retrieve.
    * @param filters Optional filters to apply when retrieving the employee.
    * @return The retrieved employee object, or null if not found.
    */
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

    /**
    * Prints all employees in the cache using the provided function for formatting.
    *
    * @param function The function to format the employees for printing.
    */
    @Override
    public void printAllItems(Function<Employee, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }

}
