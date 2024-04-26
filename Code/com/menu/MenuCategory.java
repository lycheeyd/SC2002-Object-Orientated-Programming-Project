package com.menu;

import java.util.function.Predicate;

/**
 * Enumerates the categories of items in a menu and provides a test method to filter items based on category.
 */
public enum MenuCategory implements Predicate<Object> {
    /** Category representing side items. */
    SIDE, 
    /** Category representing set meals. */
    SET_MEAL, 
    /** Category representing burgers. */
    BURGER, 
    /** Category representing drinks. */
    DRINK;

    /**
     * Tests whether the given object belongs to this menu category.
     *
     * @param o The object to test.
     * @return true if the object belongs to this category, false otherwise.
     */
    @Override
    public boolean test(Object o) {
        if (o instanceof MenuItem) {
            return ((MenuItem) o).getCategory().equals(this);
        }
        return false;
    } 

}
