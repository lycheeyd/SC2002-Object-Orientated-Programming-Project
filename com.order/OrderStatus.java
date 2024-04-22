package com.order;

import java.util.function.Predicate;

public enum OrderStatus implements Predicate<Order> {
    NEW,
    IN_PROGRESS,
    READY_TO_PICKUP,
    COMPLETED,
    CANCELLED;

    @Override
    public boolean test(Order order) {
        return order.getStatus().equals(this);
    } 
}
