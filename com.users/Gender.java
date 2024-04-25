package com.users;

import java.util.function.Predicate;

public enum Gender implements Predicate<Object> {
    M, 
    F;

    @Override
    public boolean test(Object o) {
        if (o instanceof Employee) {
            return ((Employee) o).getGender().equals(this);
        }
        return false;
    } 
}
