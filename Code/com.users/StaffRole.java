package com.users;

import java.util.function.Predicate;

public enum StaffRole implements Predicate<Object> {
    S, 
    M, 
    A;

    @Override
    public boolean test(Object o) {
        if (o instanceof Employee) {
            return ((Employee) o).getRole().equals(this);
        }
        return false;
    } 
}
