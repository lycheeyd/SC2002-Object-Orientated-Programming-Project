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
import java.util.stream.Collectors;

/**
 * Represents the Customer interface for placing orders.
 * @see AppUI
 */
public class OrderUI implements AppUI {

    /**
     * An instance of BranchCache to access branch data.
     */
    private static BranchCache branchCache = BranchCache.getInstance();

    /**
     * An instance of BranchCache to access branch data.
     */
    private static MenuCache menuCache = MenuCache.getInstance();

    /**
     * An instance of OrderCache to access order data.
     */
    private static OrderCache orderCache = OrderCache.getInstance();

    /**
     * The selected branch for the order.
     */
    BranchName branchName;

    /**
     * The selected order type (e.g., dine-in, take-away).
     */
    OrderType orderType;

    /**
     * The cart containing the items in the order.
     */
    Cart cart;

    /**
     * Default constructor.
     */
    public OrderUI() {};

    /**
     * Displays the menu for placing an order and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    public void displayMenu(Scanner scanner) {
        
        System.out.println("\n[=+=] Order Interface [=+=]");
        selectBranch(scanner);
        orderType = OrderType.selectOrderType(scanner);

        List<MenuItem> menu = menuCache.getFilteredItems(branchName);

        placeOrder(scanner, menu);
        
    }

    /**
     * Prompts the user to select a branch and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void selectBranch(Scanner scanner) {
        System.out.println("\nAvailable branches: ");
        branchCache.printAllItems(Branch::getBranchName);

        System.out.print("\nSelect branch: ");
        try {
            branchName = BranchName.use(scanner.next().toUpperCase());
            if (branchCache.getItem(branchName) == null) {
                System.out.println("Branch not found. Try again.");
                selectBranch(scanner);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid branch entered. Try again.");
            selectBranch(scanner);            
        }
    }

    /**
     * Prompts the user to place an order and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     * @param menu    The list of menu items to display.
     */
    private void placeOrder(Scanner scanner, List<MenuItem> menu) {
        this.cart = new Cart();
        
        do {
            int choice = 0;
            System.out.println("\nMenu List");
            for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }
            System.out.printf("(%d) Check out.\n", menu.size() + 1);
            System.out.printf("(%d) Cancel Order.\n", menu.size() + 2);
            System.out.println("Use (-) to remove item. (e.g. -1)");

            System.out.print("\nSelect items or check out: ");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();

                if (choice == menu.size() + 1) {
                    //prevent checkout with empty cart
                    if (cart.getCartItems().isEmpty()){
                        System.out.println("Cannot check out with an empty cart. Please select at least one item.");
                        continue;
                    } 
                    cart.setCustomMessage(scanner);
                    checkOut(scanner, cart);
                    break;
                } else if (choice == menu.size() + 2) {
                    System.out.println("[=+=] Cancelling order...\n");
                    break;
                } else if (choice < 0) {
                    int remove = Math.abs(choice) - 1;
                    MenuItem itemToRemove = menu.get(remove);
                    cart.removeCart(itemToRemove);
                    System.out.printf("%s removed from cart.\n", itemToRemove.getName());
                    System.out.printf("Items in cart: %s\n", cart.getCartItems().stream().map(MenuItem::getName).collect(Collectors.toList()));
                } else if (choice > 0) {
                    MenuItem chosen = menu.get(choice-1);
                    cart.addCart(chosen);
                    System.out.printf("%s added to cart.\n", chosen.getName());
                    System.out.printf("Items in cart: %s\n", cart.getCartItems().stream().map(MenuItem::getName).collect(Collectors.toList()));
                }

            } catch (InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid Item! Try again.");
                
            }
        } while(true);
    }

    /**
     * Handles the checkout process and prompts the user for payment.
     *
     * @param scanner The Scanner object used to read user input.
     * @param cart    The cart containing the items in the order.
     */
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
            System.out.printf("(%d) Cancel Order.\n", methods.size() + 1);
            System.out.print("\nSelect payment method: ");
            try {
                choice = scanner.nextInt();
                if (choice == methods.size() + 1) {
                    System.out.println("[=+=] Cancelling order...\n");
                    break;
                }
                if (PaymentMethod.processPayment(methods.get(choice-1), cost).equals(PaymentStatus.SUCCESSFUL)) {
                Integer orderID = OrderID.generateOrderId();
                orderCache.addItem(orderID, new Order(orderID, cart, branchName, orderType, OrderStatus.NEW));
                System.out.printf("\n[=+=] Receipt [=+=]", orderID);
                System.out.println(orderCache.getItem(orderID));
                break;
                } else {
                System.out.println("\nPayment Cancelled or Failed! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.");
                scanner.nextLine();
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Invalid payment method. Try again.");
            }
        } while(true);
    }

}

    

