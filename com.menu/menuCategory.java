package com.menu;

import java.util.function.Predicate;

public enum menuCategory implements Predicate<Object> {
    SIDE, 
    SET_MEAL, 
    BURGER, 
    DRINK;

    @Override
    public boolean test(Object o) {
        if (o instanceof MenuItem) {
            return ((MenuItem) o).getCategory().equals(this);
        }
        return false;
    } 

}
