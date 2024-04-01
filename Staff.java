package com.users;

import com.branch.Branch;

public class Staff extends Employee {

    public Staff(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    protected Staff demoteToStaff(Manager manager) {
        return new Staff(manager.getName(), manager.getLoginID(), manager.getRole(), manager.getGender(), manager.getAge(), manager.getBranch(), manager.getPassword());
    }

}
