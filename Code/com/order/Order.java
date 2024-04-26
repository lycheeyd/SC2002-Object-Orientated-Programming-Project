package com.order;

import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import com.branch.BranchName;
import com.menu.MenuItem;

/**
 * The Order class represents an order in the system.
 */
public class Order {

    /**
     * The ID of the order.
     */
    private int orderID;

    /**
     * The cart of the order.
     */
    private Cart cart;

    /**
     * The name of the branch the order originates from.
     */
    private BranchName branchName;

    /**
     * The ordertype of the order.
     */
    private OrderType orderType;

    /**
     * The status of the order.
     */
    private OrderStatus status;

    /**
     * Order specific instance of automated service to cancel the order after exceeding specified collection time.
     */
    private AutoCancelService cancelService;


    /**
     * Constructor for creating an Order instance.
     * 
     * @param orderID The unique identifier for the order.
     * @param cart The cart containing items in the order.
     * @param branchName The branch name associated with the order.
     * @param orderType The type of the order (e.g., dine-in, takeaway).
     * @param status The status of the order (e.g., pending, ready for pickup).
     */
    public Order(int orderID, Cart cart, BranchName branchName, OrderType orderType, OrderStatus status) {
    this.orderID = orderID;
    this.cart = cart;
    this.branchName = branchName;
    this.orderType = orderType;
    this.status = status;
    this.cancelService = AutoCancelService.getInstance();
    }

    /**
     * Retrieves the order ID.
     * 
     * @return The order ID.
     */
    public int getOrderID() {
        return this.orderID;
    }

    /**
     * Sets the order ID.
     * 
     * @param orderID The new order ID to set.
     */
    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    /**
     * Retrieves the cart associated with the order.
     * 
     * @return The cart.
     */
    public Cart getCart() {
        return this.cart;
    }

    /**
     * Sets the cart for the order.
     * 
     * @param cart The cart to set.
     */
    public void setCart(Cart cart) {
        this.cart = cart;
    }

    /**
     * Retrieves the branch name associated with the order.
     * 
     * @return The branch name.
     */
    public BranchName getBranchName() {
        return this.branchName;
    }

    /**
     * Sets the branch name for the order.
     * 
     * @param branchName The branch name to set.
     */
    public void setBranchName(BranchName branchName) {
        this.branchName = branchName;
    }

    /**
     * Retrieves the order type.
     * 
     * @return The order type.
     */
    public OrderType getOrderType() {
        return this.orderType;
    }

    /**
     * Sets the order type.
     * 
     * @param orderType The order type to set.
     */
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    /**
     * Retrieves the status of the order.
     * 
     * @return The order status.
     */
    public OrderStatus getStatus() {
        return this.status;
    }

    /**
    * Sets the status of the order and schedules automatic cancellation if needed.
    * 
    * @param status The new order status.
    */
    public void setStatus(OrderStatus status) {
        this.status = status;
        if (status == OrderStatus.READY_TO_PICKUP) {
            cancelService.scheduleCancel(this, 1, TimeUnit.MINUTES);
        } else {
            cancelService.stopCancelTask(this);
        }
    }

    /**
    * Generates a string representation of the order (equivalent to printing a receipt).
    * 
    * @return The string representation of the order.
    */
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