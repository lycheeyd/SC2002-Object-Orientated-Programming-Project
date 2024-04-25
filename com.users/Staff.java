package com.users;

import com.branch.Branch;
import com.cache.AppCache;
import com.cache.OrderCache;
import com.order.Order;
import com.order.OrderID;
import com.order.OrderStatus;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.function.Predicate;

public class Staff extends Employee {

    public Staff(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    public void processOrder(OrderStatus status) {
        if (status == OrderStatus.NEW) {
            status = OrderStatus.IN_PROGRESS;
            System.out.println("Order status changed to IN_PROGRESS");
        } else {
            System.out.println("Order status cannot be changed.");
        }
    }

    public void readyToPickupOrder(OrderStatus status) {
        if (status == OrderStatus.IN_PROGRESS) {
            status = OrderStatus.READY_TO_PICKUP;

            System.out.println("Order status changed to READY_TO_PICKUP");

            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                public void run() {
                    setToCompleted(OrderStatus.READY_TO_PICKUP);
                }
            }, 30000);        
        } 
        else {
            System.out.println("Order status cannot be changed.");
        }
    }

    public void setToCompleted(OrderStatus status){
        status = OrderStatus.COMPLETED;
    }

}
