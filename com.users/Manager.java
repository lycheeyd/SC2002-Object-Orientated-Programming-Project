package com.users;

import com.menu.MenuItem;
import com.branch.Branch;
import com.menu.Menu;

public class Manager extends Staff {

    //this constructor is for creating manager during file reading.
    public Manager(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    //this constructor is for promotion from staff to manager
    protected Manager promoteToManager(Staff staff) {
        return new Manager(staff.getName(), staff.getLoginID(), staff.getRole(), staff.getGender(), staff.getAge(), staff.getBranch(), staff.getPassword());
    }

    /* 
    public void addMenuItem(String name, double price, String branch, String category) {
        MenuItem newItem = new MenuItem(name, price, branch, category);
        this.menu.getItems().add(newItem);
    }

    public void removeMenuItem(String name) {
        this.menu.getItems().removeIf(item -> item.getName().equals(name));
    }
    */
    
}
