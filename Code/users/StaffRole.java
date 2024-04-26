package com.users;

import java.util.function.Predicate;

/**
 * The StaffRole enum represents the role of a staff member in the system.
 */
public enum StaffRole implements Predicate<Object> {
    /**
     * Represents a standard staff member.
     */
    S, 
    /**
     * Represents a manager.
     */
    M, 
    /**
     * Represents an admin.
     */
    A;

    /**
     * Tests if the given object has the same role as this enum value.
     * @param o The object to test.
     * @return true if the object is an Employee with the same role, false otherwise.
     */
    @Override
    public boolean test(Object o) {
        if (o instanceof Employee) {
            return ((Employee) o).getRole().equals(this);
        }
        return false;
    } 
}

