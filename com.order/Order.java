package com.order;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.menu.MenuItem;

public class Order {
    private int orderID;
    private Cart cart;
    private OrderType orderType;
    private OrderStatus status;
    private AutoCancelService cancelService;

    public Order(int orderID, Cart cart, OrderType orderType, OrderStatus status) {
    this.orderID = orderID;
    this.cart = cart;
    this.orderType = orderType;
    this.status = status;
    this.cancelService = AutoCancelService.getInstance();
    }

    public int getOrderID() {
        return this.orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public Cart getCart() {
        return this.cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }

    public OrderType getOrderType() {
        return this.orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public OrderStatus getStatus() {
        return this.status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
        if (status == OrderStatus.READY_TO_PICKUP) {
            cancelService.scheduleCancel(this, 2, TimeUnit.MINUTES);
        } else {
            cancelService.stopCancelTask(this);
        }
    }

    @Override
    // This is equivelant to print Reciept (print order itself as reciept)
    public String toString() {
        return
            "\norderID: " + getOrderID() + "\n" +
            "orderType: " + getOrderType() + "\n" +
            "cost: $" + cart.getCost() + "\n" +
            "cart: " + cart.getCartItems().stream().map(MenuItem::getName).collect(Collectors.toList()) + "\n" +
            "remarks: " + cart.getCustomMessage() + "\n" +
            "status: " + getStatus() + "\n";
    }

}