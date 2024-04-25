package com.users;

import com.branch.Branch;
import com.branch.BranchName;
import com.cache.AppCache;
import com.cache.OrderCache;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.order.Order;
import com.order.OrderID;
import com.order.OrderStatus;
import com.menu.MenuItem;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Manager extends Staff {

    //this constructor is for creating manager during file reading.
    public Manager(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    //this constructor is for promotion from staff to manager
    protected Manager promoteToManager(Staff staff) {
        return new Manager(staff.getName(), staff.getLoginID(), staff.getRole(), staff.getGender(), staff.getAge(), staff.getBranch(), staff.getPassword());
    }

    /*public void viewOrderDetails(Scanner scanner) {
        
    }

    public void processOrder(Scanner scanner) {
        
    }

    public void displayStaffList() {
        
    }

    public void addMenuItem(Scanner scanner, BranchName branch) {
            
    }

    public void editMenuItem(Scanner scanner, BranchName branch) {
        
    }

    public void removeMenuItem(Scanner scanner, BranchName branch) {
    
    }*/

    





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
