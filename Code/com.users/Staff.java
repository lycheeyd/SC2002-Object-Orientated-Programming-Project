package com.users;

import com.branch.Branch;
import com.order.Order;
import com.order.OrderStatus;

public class Staff extends Employee {

    public Staff(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    public void processOrder(Order order) {
        if (order.getStatus() == OrderStatus.NEW) {
            order.setStatus(OrderStatus.IN_PROGRESS);
            System.out.println("Order status changed to IN_PROGRESS");
        } else {
            System.out.println("Order status cannot be changed.");
        }
    }

    public void readyToPickupOrder(Order order) {
        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.READY_TO_PICKUP);
            System.out.println("Order status changed to READY_TO_PICKUP");        
        } 
        else {
            System.out.println("Order status cannot be changed.");
        }
    }

    public void setToCompleted(Order order){
        order.setStatus(OrderStatus.COMPLETED);
    }

}
