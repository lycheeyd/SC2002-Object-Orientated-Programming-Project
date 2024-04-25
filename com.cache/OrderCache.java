package com.cache;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.order.Order;


public class OrderCache extends AppCache<Integer, Order, Order> {

    private static OrderCache instance;

    private OrderCache() {}

    public static synchronized OrderCache getInstance() {
        if (instance == null) {
            instance = new OrderCache();
        }
        return instance;
    }

    @Override
    public void addItem(Integer orderID, Order order) {
        if (cacheItems.containsKey(orderID)) {
            System.out.println("Order "+ orderID + " already exists.");
        } else {
            cacheItems.put(orderID, order);
            System.out.println("Order " + orderID + " successfully added.");
        }
    }

    @SafeVarargs
    @Override
    public final void removeItem(Integer orderID, Optional<Predicate<Order>>... filters) {
        if (cacheItems.containsKey(orderID)) {
            cacheItems.remove(orderID);
            System.out.println(orderID + "is removed.");
        } else {
            System.out.println("Unable to remove order. (Order not found)");
        }
    }

    @SafeVarargs
    @Override
    public final Order getItem(Integer orderID, Optional<Predicate<Object>>... order) {
        if (cacheItems.containsKey(orderID)) {
            return cacheItems.get(orderID);
        } else {
            // System.out.println("Unable to retrieve order (Order not found)");
            return null;
        }
    }

    @Override    
    public void printAllItems(Function<Order, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }
}
