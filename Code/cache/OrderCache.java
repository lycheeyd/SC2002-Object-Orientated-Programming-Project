package com.cache;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import com.order.Order;


/**
* A cache for managing Order objects, ensuring each order is uniquely identified by an order ID.
* @see AppCache
*/
public class OrderCache extends AppCache<Integer, Order, Order> {

    /** The singleton instance of OrderCache. */
    private static OrderCache instance;

    /**
    * Constructs an OrderCache object.
    * Private constructor to prevent direct instantiation.
    */
    private OrderCache() {}

    
    /**
    * Retrieves the singleton instance of OrderCache.
    *
    * @return The singleton instance of OrderCache.
    */
    public static synchronized OrderCache getInstance() {
        if (instance == null) {
            instance = new OrderCache();
        }
        return instance;
    }


    /**
    * Adds an order to the cache with the specified order ID.
    * If an order with the same ID already exists, a message is printed.
    *
    * @param orderID The unique identifier for the order.
    * @param order The Order object to add to the cache.
    */
    @Override
    public void addItem(Integer orderID, Order order) {
        if (cacheItems.containsKey(orderID)) {
            System.out.println("Order "+ orderID + " already exists.");
        } else {
            cacheItems.put(orderID, order);
            System.out.println("Order " + orderID + " successfully added.");
        }
    }

    /**
    * Removes an order from the cache based on the specified order ID and optional filters.
    * If the order is found and removed, a message is printed.
    *
    * @param orderID The ID of the order to remove.
    * @param filters Optional filters to apply when removing the order.
    */

    @SafeVarargs
    @Override
    public final void removeItem(Integer orderID, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(orderID)) {
            cacheItems.remove(orderID);
            System.out.println(orderID + "is removed.");
        } else {
            System.out.println("Unable to remove order. (Order not found)");
        }
    }

    /**
    * Retrieves an order from the cache based on the specified order ID and optional filters.
    * If the order is found, it is returned; otherwise, null is returned.
    *
    * @param orderID The ID of the order to retrieve.
    * @param filters Optional filters to apply when retrieving the order. Not implemented.
    * @return The Order object matching the criteria, or null if not found.
    */
    @SafeVarargs
    @Override
    public final Order getItem(Integer orderID, Optional<Predicate<Object>>... filters) {
        if (cacheItems.containsKey(orderID)) {
            return cacheItems.get(orderID);
        } else {
            // System.out.println("Unable to retrieve order (Order not found)");
            return null;
        }
    }


    /**
    * Prints all orders in the cache using the specified function.
    * Each order is processed by the provided function before printing.
    *
    * @param function The function to apply to each order before printing.
    */
    @Override    
    public void printAllItems(Function<Order, ?> function) {
        cacheItems.values().stream()
                        .map(function)
                        .forEach(System.out::println);
    }
}
