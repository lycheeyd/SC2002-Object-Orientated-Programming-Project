package com.UI;

import com.order.Order;
import com.order.OrderStatus;
import com.users.Employee;
import com.users.Staff;
import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.cache.OrderCache;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Represents the user interface for Staff Actions.
 * @see AppUI
 */
public class StaffUI implements AppUI {

    /**
     * An instance of EmployeeCache to access employee data.
     */
    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();

    /**
     * An instance of MenuCache to access menu data.
     */
    protected static MenuCache menuCache = MenuCache.getInstance();

    /**
     * An instance of BranchCache to access branch data.
     */
    protected static BranchCache branchCache = BranchCache.getInstance();

    /**
     * An instance of OrderCache to access order data.
     */
    protected static OrderCache orderCache = OrderCache.getInstance();

    /**
     * The Staff object representing the current staff member.
     */
    private Staff staff;

    /**
     * Constructs a StaffUI object with the given Employee object.
     *
     * @param employee The Employee object representing the staff member.
     */
    public StaffUI(Employee employee) {
        this.staff = (Staff) employee;
    }

    
    /**
     * Displays the menu for the staff interface and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    @Override
    public void displayMenu(Scanner scanner) {

        do {
            System.out.println("\n[=+=] Staff Interface [=+=]");
            System.out.println("(1) Display New Orders");
            System.out.println("(2) View Order Details");
            System.out.println("(3) Process Order");
            System.out.println("(4) Order is Ready");
            System.out.println("(5) Track Order");
            System.out.println("(6) Logout");
            System.out.print("\nWaiting for user input: ");
    
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Displaying new orders...");
                        orderCache.printFilteredItems(OrderStatus.NEW, staff.getBranch().getBranchName());
                        break;
                    case 2:
                        viewOrderDetails(scanner);
                        break;
                    case 3:
                        processOrder(scanner);
                        break;
                    case 4:
                        readyToPickupOrder(scanner);
                        break;
                    case 5:
                        trackOrder(scanner);
                        break;
                    case 6:
                        System.out.println("Logging out...");
                        return;
                    default:
                        System.out.println("Invalid response. Try again.");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.\n");
                scanner.nextLine();
            }
        } while (true);
    }

    /**
     * Prompts the user to enter an order ID and displays the order details if found.
     *
     * @param scanner The Scanner object used to read user input.
     */
    protected void viewOrderDetails(Scanner scanner) {
        try {
            System.out.print("\nEnter the order ID: ");
            int orderID = scanner.nextInt();
            Order order = orderCache.getItem(orderID);
            if (order != null) {
                System.out.println(order.toString());
            } else {
                System.out.println("Order does not exist.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter only numbers! ");
        }
    }

    /**
     * Prompts the user to enter an order ID and processes the order if it belongs to the staff's branch.
     *
     * @param scanner The Scanner object used to read user input.
     */
    protected void processOrder(Scanner scanner) {
        try {
            System.out.print("\nEnter order ID: ");
            int orderId = scanner.nextInt();
            Order order = orderCache.getItem(orderId);
            if (order != null) {
                if(order.getBranchName().equals(staff.getBranch().getBranchName())) {
                    staff.processOrder(order);
                } else {
                    System.out.println("This order is not from your branch.");
                }
            } else {
                System.out.println("Order does not exist.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Error input! Enter only numbers.\n");
            scanner.nextLine();
        }
    }

    /**
     * Prompts the user to enter an order ID and marks the order as ready for pickup if it belongs to the staff's branch.
     *
     * @param scanner The Scanner object used to read user input.
     */
    protected void readyToPickupOrder(Scanner scanner) {
        try {
            System.out.print("\nEnter order ID: ");
            int orderId = scanner.nextInt();
            Order order = orderCache.getItem(orderId);
            if (order != null) {
                if(order.getBranchName().equals(staff.getBranch().getBranchName())) {
                    staff.readyToPickupOrder(order);
                } else {
                    System.out.println("This order is not from your branch.");
                }
            } else {
                System.out.println("Order does not exist.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Error input! Enter only numbers.\n");
            scanner.nextLine();
        }
    }

    /**
     * Prompts the user to enter an order ID and displays the order status if the order belongs to the staff's branch.
     *
     * @param scanner The Scanner object used to read user input.
     */
    protected void trackOrder(Scanner scanner) {
        try {
            System.out.print("\nEnter order ID to track: ");
            int orderId = scanner.nextInt();
            Order order = orderCache.getItem(orderId);
            if (order != null) {
                if(order.getBranchName().equals(staff.getBranch().getBranchName())) {
                    System.out.println(order.getStatus());
                } else {
                    System.out.println("This order is not from your branch.");
                }
            } else {
                System.out.println("Order does not exist.");
            }
        } catch(InputMismatchException e) {
            System.out.println("Error input! Enter only numbers.\n");
            scanner.nextLine();
        }
    }

}
