package com.order;

import java.util.function.Predicate;

public enum OrderStatus implements Predicate<Object> {
    NEW,
    IN_PROGRESS,
    READY_TO_PICKUP,
    COMPLETED,
    CANCELLED;

    @Override
    public boolean test(Object o) {
        if (o instanceof Order) {
            return ((Order) o).getStatus().equals(this);
        }
        return false;
    }
}
