package com.users;

import com.branch.Branch;
import com.order.Order;
import com.order.OrderStatus;

/**
 * The Staff class represents a staff member in the system, extending the Employee class.
 * @see Employee
 */
public class Staff extends Employee {

    /**
     * Constructs a new Staff member with the specified attributes.
     * @param name The name of the staff member.
     * @param loginID The login ID of the staff member.
     * @param role The role of the staff member.
     * @param gender The gender of the staff member.
     * @param age The age of the staff member.
     * @param branch The branch of the staff member.
     * @param password The password of the staff member.
     */
    public Staff(String name, String loginID, StaffRole role, Gender gender, int age, Branch branch, String password) {
        super(name, loginID, role, gender, age, branch, password);
    }

    /**
     * Process the given order, changing its status to IN_PROGRESS if it is currently NEW.
     * @param order The order to process.
     */
    public void processOrder(Order order) {
        if (order.getStatus() == OrderStatus.NEW) {
            order.setStatus(OrderStatus.IN_PROGRESS);
            System.out.println("Order status changed to IN_PROGRESS");
        } else {
            System.out.println("Order status cannot be changed.");
        }
    }

    /**
     * Set the given order's status to READY_TO_PICKUP if it is currently IN_PROGRESS.
     * @param order The order to set to READY_TO_PICKUP.
     */
    public void readyToPickupOrder(Order order) {
        if (order.getStatus() == OrderStatus.IN_PROGRESS) {
            order.setStatus(OrderStatus.READY_TO_PICKUP);
            System.out.println("Order status changed to READY_TO_PICKUP");        
        } 
        else {
            System.out.println("Order status cannot be changed.");
        }
    }

    /**
     * Set the given order's status to COMPLETED.
     * @param order The order to set to COMPLETED.
     */
    public void setToCompleted(Order order){
        order.setStatus(OrderStatus.COMPLETED);
    }

}
