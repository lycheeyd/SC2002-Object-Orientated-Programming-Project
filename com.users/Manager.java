package com.users;

import com.branch.Branch;

public class Manager extends Staff {

    public Manager(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }
    
}
