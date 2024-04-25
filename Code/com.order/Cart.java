package com.order;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.menu.MenuItem;

public class Cart {
    private List<MenuItem> cart = new ArrayList<>();
    private double cost = 0;
    private String customMessage;

    public void addCart(MenuItem item) {
        cart.add(item);
    }

    public void removeCart(MenuItem item) {
        if (cart.contains(item)) {
            cart.remove(item);
        } else {
            System.err.println("Item not found in cart!");
        }   
    }

    public double getPrice() {
        for (MenuItem item : cart) {
            cost += item.getPrice();
        }
        return cost;
    }

    public void setCustomMessage(Scanner scanner) {
        System.out.print("\nEnter remarks for your order (optional): ");
        String message = scanner.nextLine();
        this.customMessage = message;

    }

    public void printCartItems() {
        cart.forEach(System.out::println);
    }

    public List<MenuItem> getCartItems() {
        return this.cart;
    }

    public void setCart(List<MenuItem> cart) {
        this.cart = cart;
    }

    public String getCost() {
        return String.format("%.2f", this.cost);
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getCustomMessage() {
        return this.customMessage;
    }

    @Override
    public String toString() {
        return "{" +
            " cart='" + getCartItems() + "'" +
            ", cost='" + getCost() + "'" +
            ", customMessage='" + getCustomMessage() + "'" +
            "}";
    }

}
