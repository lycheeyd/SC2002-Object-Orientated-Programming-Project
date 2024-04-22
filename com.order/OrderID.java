package com.order;

public class OrderID {
    private static Integer uniqueID = 1000;

    public static synchronized Integer generateOrderId() {
        uniqueID++;
        return uniqueID;
    }
}
