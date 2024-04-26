package com.order;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;


/**
 * The OrderType enum represents different types of orders.
 */
public enum OrderType implements Predicate<Object>{

    /**
    * Represents a dine-in order type.
    */
    DINE_IN,

    /**
    * Represents a takeaway order type.
    */
    TAKEAWAY;


    /**
    * Allows the user to select an order type.
    * 
    * @param scanner The scanner object for input.
    * @return The selected order type.
    */
    public static OrderType selectOrderType(Scanner scanner) {

        while (true){
            int i = 1;

            for (OrderType type : OrderType.values()) {
                System.out.printf("Enter %d for " + type + "\n", i++);
            }

            try {
                System.out.print("\nWaiting for user input: ");
                int typeChoice = scanner.nextInt();
                if (typeChoice == 1) {
                    return OrderType.DINE_IN;
                }
                else if (typeChoice == 2){
                    return OrderType.TAKEAWAY;
                }
                else
                    System.out.println("Invalid response. Try again.\n");
                    continue;
            } catch (InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.\n");
            } finally {
                scanner.nextLine();
            }
        }
    }


    /**
    * Checks if the given object matches the order type.
    * 
    * @param o The object to test against.
    * @return true if the object matches the order type, false otherwise.
    */    
    @Override
    public boolean test(Object o) {
        if (o instanceof Order) {
            return ((Order) o).getOrderType().equals(this);
        }
        return false;
    }
}
