package com.menu;

import java.util.function.Predicate;

public enum menuCategory implements Predicate<MenuItem> {
    SIDE, 
    SET_MEAL, 
    BURGER, 
    DRINK;

    @Override
    public boolean test(MenuItem item) {
        return item.getCategory().equals(this);
    } 

}
