package com.UI;

import com.branch.Branch;
import com.branch.BranchName;
import com.cache.BranchCache;
import com.cache.MenuCache;
import com.cache.OrderCache;
import com.menu.MenuItem;
import com.order.Cart;
import com.order.Order;
import com.order.OrderID;
import com.order.OrderStatus;
import com.order.OrderType;
import com.payment.PaymentMethod;
import com.payment.PaymentStatus;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class OrderUI implements AppUI {

    private static BranchCache branchCache = BranchCache.getInstance();
    private static MenuCache menuCache = MenuCache.getInstance();
    private static OrderCache orderCache = OrderCache.getInstance();

    OrderType orderType;
    Cart cart;

    public void displayMenu(Scanner scanner) {
        
        System.out.println("\n[=+=] Order Interface [=+=]");
        BranchName branchName = selectBranch(scanner);
        orderType = OrderType.selectOrderType(scanner);

        List<MenuItem> menu = menuCache.getFilteredItems(branchName);

        cart = placeOrder(scanner, menu);

        cart.setCustomMessage(scanner);

        checkOut(scanner, cart);
    }

    private BranchName selectBranch(Scanner scanner) {
        System.out.println("\nAvailable branches: ");
        branchCache.printAllItems(Branch::getBranchName);

        System.out.print("\nSelect branch: ");
        try {
            BranchName branchName = BranchName.valueOf(scanner.next().toUpperCase());
            return branchName;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid branch entered. Try again.");
            return selectBranch(scanner);            
        }
    }

    private Cart placeOrder(Scanner scanner, List<MenuItem> menu) {
        Cart cart = new Cart();
        
        do {
            int choice = 0;
            System.out.println("\nMenu List");
            for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }
            System.out.printf("(%d) Check out.\n", menu.size() + 1);

            System.out.print("\nSelect items or check out: ");
            try {
                choice = scanner.nextInt();
                if (choice == menu.size() + 1) {
                    break;
                }
                cart.addCart(menu.get(choice-1));
            } catch (InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Item! Try again.");
            } finally {
                scanner.nextLine();
            }
        } while(true);
        return cart;
    }

    private void checkOut(Scanner scanner, Cart cart) {
        double cost = cart.getPrice();
        String customMessage = cart.getCustomMessage();

        do {
            int choice = 0;
            System.out.println("\n[=+=] Check Out [=+=]");
            System.out.println("Items in cart: ");
            cart.printCartItems();
            System.out.println(orderType);
            System.out.println("Remarks: " + customMessage);
            System.out.printf("Total: $%.2f\n", cost);
            System.out.println("\nAvailable payment methods: ");
            List<String> methods = PaymentMethod.getOptions();
            for (int i = 0; i < methods.size(); i++) {
                System.out.printf("(%d) %s\n", i+1, methods.get(i));
            }
            System.out.printf("(%d) Start over.\n", methods.size() + 1);
            System.out.print("\nSelect payment method: ");
            try {
                choice = scanner.nextInt();
                if (choice == methods.size() + 1) {
                    System.out.println("[=+=] Starting over...\n");
                    break;
                }
                if (PaymentMethod.processPayment(methods.get(choice-1), cost).equals(PaymentStatus.SUCCESSFUL)) {
                Integer orderID = OrderID.generateOrderId();
                orderCache.addItem(orderID, new Order(orderID, cart, orderType, OrderStatus.NEW));
                System.out.printf("\n[=+=] Receipt [=+=]", orderID);
                System.out.println(orderCache.getItem(orderID));
                break;
                } else {
                System.out.println("\nPayment Cancelled or Failed! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.");        
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid payment method. Try again.");
            } finally {
                scanner.nextLine();
            }
        } while(true);
    }

}

    

