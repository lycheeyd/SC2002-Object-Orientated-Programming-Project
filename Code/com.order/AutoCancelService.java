package com.order;

import java.util.concurrent.*;

public class AutoCancelService {
    private static AutoCancelService instance;
    private final ScheduledExecutorService scheduler;
    private final ConcurrentHashMap<Order, ScheduledFuture<?>> cancelTasks;

    private AutoCancelService() {
        this.scheduler = Executors.newScheduledThreadPool(1);
        this.cancelTasks = new ConcurrentHashMap<>();
    }

    public static synchronized AutoCancelService getInstance() {
        if (instance == null) {
            instance = new AutoCancelService();
        }
        return instance;
    }

    public void scheduleCancel(Order order, long time, TimeUnit unit) {
        stopCancelTask(order); // Overrides previous timer, keeping track of newest status update. 

        ScheduledFuture<?> newTask = scheduler.schedule(() -> {
            order.setStatus(OrderStatus.CANCELLED);
            System.out.println("\n[=+=] Order " + order.getOrderID() + " automatically cancelled. (Uncollected beyond specified time)");
            cancelTasks.remove(order);
        }, time, unit);

        cancelTasks.put(order, newTask);
    }

    public void stopCancelTask(Order order) {
        ScheduledFuture<?> currentTask = cancelTasks.get(order);
        if (currentTask != null && !currentTask.isDone()) {
            currentTask.cancel(false);  // Cancel the task - false specifies that the task should not be interrupted if it is running
            // System.out.println("Cancel task for order " + order.getOrderID() + " has been cancelled.");
        }
    }

    public void shutdown() {
        scheduler.shutdownNow(); // force shutdown

    }
}
