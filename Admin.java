package com.users;

import com.branch.Branch;

public class Admin extends Employee{

    public Admin(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }



}
