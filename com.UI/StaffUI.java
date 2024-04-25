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

public class StaffUI implements AppUI {

    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();
    protected static MenuCache menuCache = MenuCache.getInstance();
    protected static BranchCache branchCache = BranchCache.getInstance();
    protected static OrderCache orderCache = OrderCache.getInstance();


    private Staff staff;

    public StaffUI(Employee employee) {
        this.staff = (Staff) employee;
    }

    @Override
    public void displayMenu(Scanner scanner) {

        do {
            System.out.println("[=+=] Staff Interface [=+=]");
            System.out.println("(1) Display New Orders");
            System.out.println("(2) View Order Details");
            System.out.println("(3) Process Order");
            System.out.println("(4) Complete Order");
            System.out.println("(5) Track Order");
            System.out.println("(6) Logout");
            System.out.print("\nWaiting for user input: ");
    
            try {
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Displaying new orders...");
                        orderCache.printFilteredItems(OrderStatus.NEW);
                        break;
                    case 2:
                        try {
                            System.out.println("Enter the order ID: ");
                            int orderID = scanner.nextInt();
                            Order order = orderCache.getItem(orderID);
                            if (order != null) {
                                System.out.println(order.toString());
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred while viewing order details: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            System.out.println("Enter order ID to process: ");
                            int orderId = scanner.nextInt();
                            Order order = orderCache.getItem(orderId);
                            if (order != null) {
                                staff.processOrder(order.getStatus());
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred while processing order: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            System.out.println("Enter order ID to complete: ");
                            int orderId = scanner.nextInt();
                            Order order = orderCache.getItem(orderId);
                            if (order != null) {
                                staff.readyToPickupOrder(order.getStatus());
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred while completing order: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            System.out.println("Enter order ID to track: ");
                            int orderId = scanner.nextInt();
                            Order order = orderCache.getItem(orderId);
                            if (order != null) {
                                order.getStatus();
                            }
                        } catch (Exception e) {
                            System.out.println("An error occurred while tracking order: " + e.getMessage());
                        }
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

    
}

