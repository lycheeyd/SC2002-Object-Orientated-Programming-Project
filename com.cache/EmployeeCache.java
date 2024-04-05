package com.cache;

import java.util.HashMap;
import java.util.Map;

import com.users.Employee;

public class EmployeeCache {
    
    private static EmployeeCache instance;
    private Map<String, Employee> employees = new HashMap<>();

    private EmployeeCache() {}

    public static synchronized EmployeeCache getInstance() {
        if (instance == null) {
            instance = new EmployeeCache();
        }
        return instance;
    }
 
    public void addEmployee(String loginID, Employee employee) {
        employees.put(loginID, employee);
    }

  
    public Employee getEmployee(String loginID) {
        return employees.get(loginID);
    }

    
    public void printAllItems() {
        employees.values().forEach(System.out::println);
    }

}
