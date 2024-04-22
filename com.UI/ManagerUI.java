package com.UI;

import com.users.Staff;
import com.users.Employee;
import com.users.Manager;
import com.branch.Branch;
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
import java.util.function.Predicate;

public class ManagerUI implements AppUI{

    protected static EmployeeCache employeeCache = EmployeeCache.getInstance();
    protected static MenuCache menuCache = MenuCache.getInstance();
    protected static BranchCache branchCache = BranchCache.getInstance();
    protected static OrderCache orderCache = OrderCache.getInstance();

    private Manager manager;

    public ManagerUI(Employee employee) {
        this.manager = (Manager) employee;
    }

    @Override
    public void displayMenu(Scanner scanner){
        
        System.out.println("[=+=] Manager Interface [=+=]");
        System.out.println("(1) Display New Orders");
        System.out.println("(2) View Order Details");
        System.out.println("(3) Process Order");
        System.out.println("(4) Display Staff List");
        System.out.println("(5) Add/Edit/Remove Menu items/price/availability");
        System.out.println("(6) Logout");
        System.out.print("\nWaiting for user input: ");

        int choice;

        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Displaying new orders...");
                    Predicate<Order> filter = item -> item.getStatus() == OrderStatus.NEW;
                    orderCache.printFilteredItems(filter);
                    employeeCache.getFilteredItems(BranchName.getFilter(manager.getBranch().getBranchName()));
                    break;
                case 2:
                    try {
                        System.out.println("Enter the order ID: ");
                        int orderID = scanner.nextInt();
                        Order order = orderCache.getItem(orderID);
                        if (order != null) {
                            System.out.println(order);
                            break;
                        }
                    } catch (InputMismatchException e) {
                        System.out.println("Enter only numbers! Try again.");
                    }
                case 3:
                    try {
                        System.out.println("Enter order ID to process: ");
                        int orderId = scanner.nextInt();
                        Order order = orderCache.getItem(orderId);
                        if (order != null && order.getStatus() == OrderStatus.IN_PROGRESS) {
                            order.setStatus(OrderStatus.READY_TO_PICKUP);
                            System.out.println("Order processed successfully");
                            order.setStatus(OrderStatus.COMPLETED);
                        } else {
                            System.out.println("Order not found or already processed.");
                        }
                    } catch (Exception e) {
                        System.out.println("An error occurred while processing order: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Displaying staff list...");
                    manager.displayStaffList();
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
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4 && choice !=5 && choice !=6);
    }  
    public void manageMenu(Scanner scanner) {
        System.out.println("Select function");
        System.out.println("(1) Add Menu Item");
        System.out.println("(2) Edit Item");
        System.out.println("(3) Remove Item");
        System.out.println("(4) Exit");

        int choice;
        
        do {
            choice = scanner.nextInt();
            scanner.nextLine();
            switch (choice) {
                case 1:
                    System.out.println("Enter the name of the menu item to add: ");
                    String name = addMenuItem(scanner);

                    MenuItem duplicateItem = menuCache.getItem(name);

                    if (duplicateItem == null) {
                        System.out.println("Enter name of new menu item: ");
                        String nameNew = addMenuItem(scanner);

                        System.out.println("Enter price of new menu item: ");
                        Double price = menuPrice(scanner);

                        System.out.println("Enter category of new menu item: ");
                        menuCategory category = enterCategory(scanner);

                        MenuItem newItem = new MenuItem(nameNew, price, manager.getBranch(), category); 
                        menuCache.addItem(nameNew, newItem);

                        System.out.println("Menu item added successfully.");
                    }
                    else
                        System.out.println("Menu item already exist.");
                    break;
                case 2:
                    System.out.println("Enter the name of the menu item to edit: ");
                    String originalName = addMenuItem(scanner);

                    MenuItem existingItem = menuCache.getItem(originalName);

                    if (existingItem != null) {
                        System.out.println("Enter new name of menu item: ");
                        String newName = newMenuItem(scanner);

                        System.out.println("Enter new price of menu item: ");
                        Double newPrice = newPrice(scanner);

                        System.out.println("Enter new category of menu item: ");
                        menuCategory newCategory = newCategory(scanner);

                        MenuItem editedItem = new MenuItem(newName, newPrice, manager.getBranch(), newCategory);
                        menuCache.addItem(newName, editedItem);

                        menuCache.removeItem(originalName);

                        System.out.println("Menu item added successfully.");
                    }
                    break;
                case 3:
                    System.out.println("Enter name of menu item to remove");          
                    String nameremove = scanner.nextLine();
                    menuCache.removeItem(nameremove);
                    System.out.println(nameremove + " removed");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 3 && choice != 4);
    }

    private String addMenuItem(Scanner scanner){
        try {
            String name = scanner.nextLine();
            return name;
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a name: ");
            return addMenuItem(scanner);
        }
    }

    private Double menuPrice(Scanner scanner){
        try {
            double price = scanner.nextDouble();
            return price;
        } catch (Exception e) {
            System.out.println("Input numbers only.");
            return menuPrice(scanner);
        }
    }

    private menuCategory enterCategory(Scanner scanner){
        System.out.println("Available categories: ");
        System.out.println(menuCategory.values());
        System.out.print("\nEnter category: ");
        try {
            return menuCategory.valueOf(scanner.nextLine());
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
        System.out.println(menuCategory.values());
        System.out.print("\nEnter category: ");
        try {
            return menuCategory.valueOf(scanner.nextLine());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Try again. ");
            return newCategory(scanner);
        }   
    }
    
}
