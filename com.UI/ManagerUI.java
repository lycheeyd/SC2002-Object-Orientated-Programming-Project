package com.UI;

import com.users.Employee;
import com.users.Manager;
import com.branch.BranchName;
import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.cache.MenuCache;
import com.cache.OrderCache;
import com.order.OrderStatus;
import com.order.Order;
import com.menu.menuCategory;
import com.menu.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.*;

public class ManagerUI implements AppUI {

    private static EmployeeCache employeeCache = EmployeeCache.getInstance();
    private static MenuCache menuCache = MenuCache.getInstance();
    private static BranchCache branchCache = BranchCache.getInstance();
    private static OrderCache orderCache = OrderCache.getInstance();

    private Manager manager;


    public ManagerUI(Employee employee) {
        this.manager = (Manager) employee;
    }

    @Override
    public void displayMenu(Scanner scanner){
        
        int choice;

        do {
            displayInstructions();

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    displayNewOrders();
                    break;
                case 2:
                    viewOrderDetails(scanner);
                    break;
                case 3:
                    processOrder(scanner);
                    break;
                case 4:
                    displayStaffList();
                    break;
                case 5:
                    manageMenu(scanner);
                    break;
                case 6:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    break;
            }
        } while (choice != 6);
    }

    private void displayInstructions() {
        System.out.println("\n[=+=] Manager Interface [=+=]\n");
        System.out.println("(1) Display New Orders");
        System.out.println("(2) View Order Details");
        System.out.println("(3) Process Order");
        System.out.println("(4) Display Staff List");
        System.out.println("(5) Add/Edit/Remove Menu items/price/availability");
        System.out.println("(6) Logout");
        System.out.print("\nWaiting for user input: ");
    }

    private void displayNewOrders() {
        System.out.println("Displaying new orders...");
        orderCache.printFilteredItems(OrderStatus.NEW);
        employeeCache.getItem(manager.getLoginID());
    }

    private void viewOrderDetails(Scanner scanner) {
        try {
            System.out.println("Enter the order ID: ");
            int orderID = scanner.nextInt();
            Order order = orderCache.getItem(orderID);
            if (order != null) {
                System.out.println(order);
            }
        } catch (InputMismatchException e) {
            System.out.println("Enter only numbers! Try again.");
        }
    }

    private void processOrder(Scanner scanner) {
        try {
            System.out.println("Enter order ID to process: ");
            int orderId = scanner.nextInt();
            Order order = orderCache.getItem(orderId);
            if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
                order.setStatus(OrderStatus.READY_TO_PICKUP);
                System.out.println("Order processed successfully.");
                order.setStatus(OrderStatus.COMPLETED);
            } else {
                System.out.println("Order not found or already processed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred while processing order: " + e.getMessage());
        }
    }

    private void displayStaffList() {
        System.out.println("Displaying staff list...");
        employeeCache.printAllItems(Employee::toString);
    }

    private void manageMenu(Scanner scanner) {
        System.out.println("Select function");
        System.out.println("(1) Add Menu Item");
        System.out.println("(2) Edit Item");
        System.out.println("(3) Remove Item");
        System.out.println("(4) Exit");

        List<MenuItem> menu = menuCache.getFilteredItems(manager.getBranch().getBranchName());

        int choice;
        
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    addMenuItem(scanner, menu);
                    break;
                case 2:
                    editMenuItem(scanner, menu);
                    break;
                case 3:
                    removeMenuItem(scanner, menu);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
    }

    private void addMenuItem(Scanner scanner, List<MenuItem> menu){
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }

        System.out.println("Enter the name of the menu item to add: ");

        String name = scanner.nextLine();
        BranchName branchName = manager.getBranch().getBranchName();
        List<MenuItem> duplicateItem = menuCache.getItem(name, Optional.of(branchName));

        if (duplicateItem == null) {
        	System.out.println("Enter price of new menu item: ");
            Double price = menuPrice(scanner);

            System.out.println("Enter category of new menu item: ");
            menuCategory category = enterCategory(scanner);

            MenuItem newItem = new MenuItem(name, price, manager.getBranch(), category); 
            menuCache.addItem(name, newItem);

            System.out.println("Menu item added successfully.");
            return;
        } else {
            System.out.println("Menu item already exists.");
        }
    }

    private void editMenuItem(Scanner scanner, List<MenuItem> menu){
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }

        System.out.println("Enter the name of the menu item to edit: ");

        String originalName = setMenuItem(scanner);
        BranchName branchName = manager.getBranch().getBranchName();
        List<MenuItem> existingItem = menuCache.getItem(originalName, Optional.of(branchName));

        if (existingItem != null) {
            System.out.println("Enter new price of menu item: ");
            Double newPrice = newPrice(scanner);

            System.out.println("Enter new category of menu item: ");
            menuCategory newCategory = newCategory(scanner);

            MenuItem editedItem = new MenuItem(originalName, newPrice, manager.getBranch(), newCategory);
            menuCache.addItem(originalName, editedItem);

            menuCache.removeItem(originalName);

            System.out.println("Menu item edited successfully.");
            return;
        } else {
            System.out.println("Menu item not found.");
        }
    }


    private void removeMenuItem(Scanner scanner, List<MenuItem> menu){
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }

        System.out.println("Enter the name of the menu item to remove: ");

        String itemName = scanner.nextLine();
        BranchName branchName = manager.getBranch().getBranchName();
        List<MenuItem> itemToRemove = menuCache.getItem(itemName, Optional.of(branchName));

        if (itemToRemove != null) {
            menuCache.removeItem(itemName);
            System.out.println(itemName + " removed");
        } else {
            System.out.println("Menu item not found.");
        }
    }
    
    private String setMenuItem(Scanner scanner){
        try {
            String name = scanner.nextLine();
            return name;
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a name: ");
            return setMenuItem(scanner);
        }
    }

    private Double menuPrice(Scanner scanner){
        try {
            double price = scanner.nextDouble();
            return price;
        } catch (Exception e) {
            System.out.println("Input numbers only. ");
            return menuPrice(scanner);
        }
    }

    private menuCategory enterCategory(Scanner scanner){
        System.out.println("Available categories: ");
        Arrays.stream(menuCategory.values()).forEach(System.out::println);
        System.out.print("Enter category: ");
        try {
            return menuCategory.valueOf(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Try again. ");
            return enterCategory(scanner);
        }   
    }

    private String newMenuItem(Scanner scanner){
        try {
            String name = scanner.nextLine();
            return name;
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a name: ");
            return newMenuItem(scanner);
        }
    }

    private Double newPrice(Scanner scanner){
        try {
            double price = scanner.nextDouble();
            return price;
        } catch (Exception e) {
            System.out.println("Input numbers only.");
            return newPrice(scanner);
        }
    }

    private menuCategory newCategory(Scanner scanner){
        System.out.println("Available categories: ");
        Arrays.stream(menuCategory.values()).forEach(System.out::println);
        System.out.print("Enter category: ");
        try {
            return menuCategory.valueOf(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Try again. ");
            return newCategory(scanner);
        }   
    }

}
