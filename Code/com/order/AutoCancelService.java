package com.order;

import java.util.concurrent.*;

/**
* The AutoCancelService class manages automatic cancellation of orders based on a specified time.
* @version 1.0
* @since 2024-04-26
*/

public class AutoCancelService {
    private static AutoCancelService instance;
    private final ScheduledExecutorService scheduler;
    private final ConcurrentHashMap<Order, ScheduledFuture<?>> cancelTasks;

    
    /**
    * Private constructor to create an instance of AutoCancelService.
    */
    private AutoCancelService() {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.cancelTasks = new ConcurrentHashMap<>();
    }

    
    /**
    * Retrieves the singleton instance of AutoCancelService.
    * 
    * @return The AutoCancelService instance.
    */
    public static synchronized AutoCancelService getInstance() {
        if (instance == null) {
            instance = new AutoCancelService();
        }
        return instance;
    }

    
    /**
    * Schedules automatic cancellation of an order after a specified time.
    * 
    * @param order The order to be cancelled.
    * @param time The time delay before cancellation.
    * @param unit The time unit for the delay (e.g., TimeUnit.SECONDS).
    */
    public void scheduleCancel(Order order, long time, TimeUnit unit) {
        stopCancelTask(order); // Overrides previous timer, keeping track of newest status update. 

        ScheduledFuture<?> newTask = scheduler.schedule(() -> {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("\n[=+=] Order " + order.getOrderID() + " automatically cancelled. (Uncollected beyond specified time)");
            cancelTasks.remove(order);
        }, time, unit);

        cancelTasks.put(order, newTask);
    }


    /**
    * Stops the automatic cancellation task for a specific order.
    * 
    * @param order The order for which to stop the cancellation task.
    */
    public void stopCancelTask(Order order) {
        ScheduledFuture<?> currentTask = cancelTasks.get(order);
        if (currentTask != null && !currentTask.isDone()) {
            currentTask.cancel(false);  // Cancel the task - false specifies that the task should not be interrupted if it is running
            // System.out.println("Cancel task for order " + order.getOrderID() + " has been cancelled.");
        }
    }


    /**
    * Shuts down the scheduler and cancels all pending cancellation tasks.
    */
    public void shutdown() {
        scheduler.shutdownNow(); // force shutdown

    }
}
