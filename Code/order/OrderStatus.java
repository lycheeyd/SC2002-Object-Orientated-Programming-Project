package com.order;

import java.util.function.Predicate;


/**
* The OrderStatus enum represents different statuses of an order.
*/
public enum OrderStatus implements Predicate<Object> {

    /**
    * Indicates that the order is new and pending processing.
    */
    NEW,
    
    /**
   * Indicates that the order is in progress.
   */
    IN_PROGRESS,

    /**
    * Indicates that the order is ready for pickup.
    */
    READY_TO_PICKUP,

    /**
    * Indicates that the order has been completed.
    */
    COMPLETED,

    /**
    * Indicates that the order has been cancelled.
    */
    CANCELLED;


    /**
    * Checks if the given object matches the order status.
    * 
    * @param o The object to test against.
    * @return true if the object matches the order status, false otherwise.
    */
    @Override
    public boolean test(Object o) {
        if (o instanceof Order) {
            return ((Order) o).getStatus().equals(this);
        }
        return false;
    }
}
