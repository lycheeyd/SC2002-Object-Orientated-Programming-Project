package com.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.menu.MenuItem;

/**
* The Cart class represents a cart containing menu items.
*/
public class Cart {

    /**
     * Stores the list of menu items in the cart.
     */
    private List<MenuItem> cart = new ArrayList<>();

    /**
     * The total cost of the menu items in the cart.
     */
    private double cost = 0;

    /**
     * The custom message (remarks) for the order.
     */
    private String customMessage;

    /**
    * Constructs a Cart object.
    */
    public Cart() {};

    /**
    * Adds an item to the cart.
    *
    * @param item The item to add to the cart.
    */
    public void addCart(MenuItem item) {
        cart.add(item);
    }

    /**
    * Removes an item from the cart.
    *
    * @param item The item to remove from the cart.
    */
    public void removeCart(MenuItem item) {
        if (cart.contains(item)) {
            cart.remove(item);
        } else {
            System.err.println("Item not found in cart!");
        }   
    }

    /**
    * Calculates and returns the total price of items in the cart.
    *
    * @return The total price of items in the cart.
    */
    public double getPrice() {
        for (MenuItem item : cart) {
            cost += item.getPrice();
        }
        return cost;
    }

    /**
    * Sets a custom message for the order.
    *
    * @param scanner The scanner object for input.
    */
    public void setCustomMessage(Scanner scanner) {
        System.out.print("\nEnter remarks for your order (optional): ");
        String message = scanner.nextLine();
        this.customMessage = message;

    }

    /**
    * Prints all items in the cart.
    */
    public void printCartItems() {
        cart.forEach(System.out::println);
    }

    /**
    * Retrieves the list of items in the cart.
    *
    * @return The list of items in the cart.
    */
    public List<MenuItem> getCartItems() {
        return this.cart;
    }

    /**
    * Sets the list of menu items held by the cart.
    *
    * @param cart List of menu items to set for the cart.
    */
    public void setCart(List<MenuItem> cart) {
        this.cart = cart;
    }

    /**
    * Retrieves the cost of the cart as a formatted string.
    *
    * @return The cost of the cart as a formatted string.
    */
    public String getCost() {
        return String.format("%.2f", this.cost);
    }

    /**
    * Sets the cost of the cart.
    *
    * @param cost The cost to set for the cart.
    */
    public void setCost(double cost) {
        this.cost = cost;
    }

    /**
    * Retrieves the custom message for the order.
    *
    * @return The custom message for the order.
    */
    public String getCustomMessage() {
        return this.customMessage;
    }

    /**
    * Returns a string representation of the Cart object.
    * The string representation includes the list of items in the cart,
    * the total cost of the items, and any custom message associated with the order.
    *
    * @return A string representation of the Cart object.
    */
    @Override
    public String toString() {
        return "{" +
            " cart='" + getCartItems() + "'" +
            ", cost='" + getCost() + "'" +
            ", customMessage='" + getCustomMessage() + "'" +
            "}";
    }

}
