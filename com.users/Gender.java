package com.users;

import java.util.function.Predicate;

public enum Gender implements Predicate<Employee> {
    M, 
    F;

    @Override
    public boolean test(Employee employee) {
        return employee.getGender().equals(this);
    } 
}
