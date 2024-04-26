package com.users;

import com.branch.Branch;

/**
 * The Manager class represents a manager in the system, extending the Staff class.
 * @see Staff
 */
public class Manager extends Staff {

    /**
     * Constructs a new Manager with the specified attributes.
     * @param name The name of the manager.
     * @param loginID The login ID of the manager.
     * @param role The role of the manager.
     * @param gender The gender of the manager.
     * @param age The age of the manager.
     * @param branch The branch of the manager.
     * @param password The password of the manager.
     */
    public Manager(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }
    
}

