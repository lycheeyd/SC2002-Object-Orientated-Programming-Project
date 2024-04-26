package com.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.cache.OrderCache;
import com.order.Order;
import com.order.OrderStatus;

/**
 * Represents the Customer interface for tracking of orders.
 * @see AppUI
 */
public class TrackOrderUI implements AppUI {

    /**
     * Default constructor.
     */
    public TrackOrderUI() {};

    /**
     * An instance of OrderCache to access order data.
     */
    private static OrderCache orderCache = OrderCache.getInstance();

    
    /**
     * Displays a menu for the user to track an order and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    @Override
    public void displayMenu(Scanner scanner) {
        System.out.println("\n[=+=] Track Order Interface [=+=]");
        System.out.printf("Enter orderID: ");
        try {
            int orderID = scanner.nextInt();
            Order order = orderCache.getItem(orderID);
            if (order != null) {
                trackOrder(scanner, order);
            } else {
                System.out.println("Order not found!\n");
            }
        } catch (InputMismatchException e) {
            System.out.println("Error input! Enter only numbers.\n");
            scanner.nextLine();
            displayMenu(scanner);
        }
    }

    /**
     * Tracks the status of an order and prompts the user to collect the order if it is ready.
     *
     * @param scanner The Scanner object used to read user input.
     * @param order   The Order object to be tracked.
     */
    private void trackOrder(Scanner scanner, Order order) {
        OrderStatus status = order.getStatus();
        switch (status) {
            case NEW:
                System.out.println("\nOrder is in the kitchen!\n");
                break;
            case IN_PROGRESS:
                System.out.println("\nOrder is in the kitchen!\n");
                break;
            case READY_TO_PICKUP:
                System.out.printf("\nOrder is ready! Do you want to collect your order? (Y/N): ");
                char collectChoice = Character.toUpperCase(scanner.next().charAt(0));
                if (collectChoice == 'Y') {
                    order.setStatus(OrderStatus.COMPLETED);
                    System.out.println("You have collected your order.\n");
                    break;
                } else if (collectChoice == 'N') {
                    System.out.println("You did not collect your order. Collect before it expires.\n");
                    break;
                } else {
                    System.out.println("Invalid response. Try again.\n");
                    trackOrder(scanner, order);
                    break;
                }
            case COMPLETED:
                System.out.println("\nOrder already completed!\n");
                break;
            case CANCELLED:
                System.out.println("\nOrder cancelled or expired!\n");
                break;
            default:
                break;
        }
    }
}