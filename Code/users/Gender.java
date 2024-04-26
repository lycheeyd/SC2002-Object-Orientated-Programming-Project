package com.users;

import java.util.function.Predicate;

/**
 * The Gender enum represents the gender of an employee.
 * It also implements a Predicate for checking the gender of an Employee object.
 */
public enum Gender implements Predicate<Object> {
    
    /**
     * Represents a male staff member.
     */
    M,

    /**
     * Represents a female staff member.
     */
    F;

    /**
     * Tests whether the given object's gender matches this Gender enum value.
     * @param o The object to test.
     * @return true if the object is an Employee and has the same gender as this Gender enum value, false otherwise.
     */
    @Override
    public boolean test(Object o) {
        if (o instanceof Employee) {
            return ((Employee) o).getGender().equals(this);
        }
        return false;
    } 
}
