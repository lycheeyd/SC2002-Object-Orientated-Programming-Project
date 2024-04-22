package com.users;

import java.util.function.Predicate;

public enum StaffRole implements Predicate<Employee> {
    S, 
    M, 
    A;

    @Override
    public boolean test(Employee employee) {
        return employee.getRole().equals(this);
    } 
}
