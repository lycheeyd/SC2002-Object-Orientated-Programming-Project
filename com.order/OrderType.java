package com.order;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

public enum OrderType implements Predicate<Object>{
    DINE_IN, 
    TAKEAWAY;

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
    
    @Override
    public boolean test(Object o) {
        if (o instanceof Order) {
            return ((Order) o).getOrderType().equals(this);
        }
        return false;
    }
}
