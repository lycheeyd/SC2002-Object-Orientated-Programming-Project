package com.order;


/**
* The OrderID class generates unique order IDs.
*/
public class OrderID {

    /**
     * Stores the last generated uniqueID which begins from a default of 1000.
     */
    private static Integer uniqueID = 1000;

    /**
     * Default constructor.
     */
    public OrderID() {};

    /**
    * Generates a new unique order ID.
    * 
    * @return The generated order ID.
    */
    public static synchronized Integer generateOrderId() {
        uniqueID++;
        return uniqueID;
    }
}
