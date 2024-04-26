package com.UI;

import com.users.Employee;
import com.users.Manager;
import com.branch.BranchName;
import com.branch.Branch;

import com.order.OrderStatus;
import com.menu.MenuCategory;
import com.menu.MenuItem;

import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.*;

/**
 * Represents the user interface for Manager Actions.
 * @see StaffUI
 */
public class ManagerUI extends StaffUI {

    /**
     * The Manager object representing the current manager.
     */
    private Manager manager;

    /**
     * Constructs a ManagerUI object with the given Employee object.
     *
     * @param employee The Employee object representing the manager.
     */
    public ManagerUI(Employee employee) {
        super(employee);
        this.manager = (Manager) employee;
    }
    
    /**
     * Displays the menu for the manager interface and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
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
                    readyToPickupOrder(scanner);
                    break;
                case 5:
                    trackOrder(scanner);
                    break;
                case 6:
                    displayStaffList(manager.getBranch().getBranchName());
                    break;
                case 7:
                    manageMenu(scanner);
                    break;
                case 8:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid response. Try again.");
                    break;
            }
        } while (true);
    }

    /**
     * Displays the instructions for the manager interface.
     */
    private void displayInstructions() {
        System.out.println("\n[=+=] Manager Interface [=+=]\n");
        System.out.println("(1) Display New Orders");
        System.out.println("(2) View Order Details");
        System.out.println("(3) Process Order");
        System.out.println("(4) Order is ready");
        System.out.println("(5) Track Order");
        System.out.println("(6) Display Staff List");
        System.out.println("(7) Add/Edit/Remove Menu items/price/availability");
        System.out.println("(8) Logout");
        System.out.print("\nWaiting for user input: ");
    }

    /**
     * Displays new orders for the manager's branch.
     */
    private void displayNewOrders() {
        System.out.println("Displaying new orders...");
        orderCache.printFilteredItems(OrderStatus.NEW, manager.getBranch().getBranchName());
        employeeCache.getItem(manager.getLoginID());
    }

    /**
     * Displays the staff list for the given branch.
     *
     * @param branchname The BranchName for which to display the staff list.
     */
    private void displayStaffList(BranchName branchname) {
        System.out.println("Displaying staff list...");
        employeeCache.printFilteredItems(branchname);
    }

    /**
     * Displays the menu for managing menu items and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    private void manageMenu(Scanner scanner) {
        
        do {
            System.out.println("Select function");
            System.out.println("(1) Add Menu Item");
            System.out.println("(2) Edit Item");
            System.out.println("(3) Remove Item");
            System.out.println("(4) Exit");
            System.out.print("\nWaiting for user input: ");
    
            List<MenuItem> menu = menuCache.getFilteredItems(manager.getBranch().getBranchName());
    
            int choice;

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
        } while (true);
    }

    /**
     * Adds a new menu item with user input.
     *
     * @param scanner The Scanner object used to read user input.
     * @param menu    The list of existing menu items.
     */
    private void addMenuItem(Scanner scanner, List<MenuItem> menu){
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size() ; i++) {
                System.out.printf("(%d) %s\n", i+1, menu.get(i));
            }
        
        System.out.print("\nEnter new menu item: ");
        String name = scanner.nextLine();
        BranchName branchName = manager.getBranch().getBranchName();
        List<MenuItem> duplicateItem = menuCache.getItem(name, Optional.of(branchName));

        if (duplicateItem == null) {
        	System.out.print("\nEnter price of new menu item: ");
            Double price = menuPrice(scanner);

            System.out.print("\nEnter category of new menu item: ");
            MenuCategory category = enterCategory(scanner);

            MenuItem newItem = new MenuItem(name, price, manager.getBranch(), category); 
            menuCache.addItem(name, newItem);

            System.out.println("Menu item added successfully.");
            return;
        } else {
            System.out.println("Menu item already exists.");
        }
    }

    /**
     * Edits an existing menu item with user input.
     *
     * @param scanner The Scanner object used to read user input.
     * @param menu    The list of existing menu items.
     */
    private void editMenuItem(Scanner scanner, List<MenuItem> menu){
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size() ; i++) {
            System.out.printf("(%d) %s\n", i+1, menu.get(i).getName());
        }

        System.out.print("\nEnter the index of the menu item to edit: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (index >= 1 && index <= menu.size()) {
                MenuItem existingItem = menu.get(index - 1);

                System.out.print("\nEnter new name of menu item: ");
                String newName = setMenuItem(scanner);

                System.out.print("\nEnter new price of menu item: ");
                Double newPrice = newPrice(scanner);

                System.out.print("\nEnter new category of menu item: ");
                MenuCategory newCategory = newCategory(scanner);

                Branch currentBranch = existingItem.getBranch();

                MenuItem editedItem = new MenuItem(newName, newPrice, currentBranch, newCategory);
                menuCache.addItem(newName, editedItem);

                menuCache.getItem(existingItem.getName(), Optional.of(currentBranch.getBranchName()));

                menuCache.removeItem(existingItem.getName(), Optional.of(currentBranch.getBranchName()));

                System.out.println("Menu item edited successfully.");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid index (number).");
            scanner.nextLine();
        }
        
    }

    /**
     * Removes an existing menu item with user input.
     *
     * @param scanner The Scanner object used to read user input.
     * @param menu    The list of existing menu items.
     */
    private void removeMenuItem(Scanner scanner, List<MenuItem> menu) {
        System.out.println("\nAvailable Menu Items:");
        for (int i = 0; i < menu.size(); i++) {
            System.out.printf("(%d) %s\n", i + 1, menu.get(i).getName());
        }

        System.out.print("\nEnter the index of the menu item to remove: ");
        try {
            int index = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            if (index >= 1 && index <= menu.size()) {
                MenuItem itemToRemove = menu.get(index - 1);
                menuCache.removeItem(itemToRemove.getName(), Optional.of(itemToRemove.getBranch().getBranchName()));
                System.out.println(itemToRemove.getName() + " removed");
            } else {
                System.out.println("Invalid index. Please enter a valid index.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid index (number).");
            scanner.nextLine();
        }
        
    }

    /**
     * Prompts the user to enter a menu item name and returns the input.
     * If an invalid input is entered, it prompts the user again.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The menu item name entered by the user.
     */
    private String setMenuItem(Scanner scanner){
        try {
            String name = scanner.nextLine();
            return name;
        } catch (Exception e) {
            System.out.println("Invalid input. Enter a name: ");
            return setMenuItem(scanner);
        }
    }

    /**
     * Prompts the user to enter a menu item price and returns the input.
     * If an invalid input is entered, it prompts the user again.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The menu item price entered by the user.
     */
    private Double menuPrice(Scanner scanner){
        try {
            double price = scanner.nextDouble();
            return price;
        } catch (Exception e) {
            System.out.println("Input numbers only. ");
            return menuPrice(scanner);
        }
    }

    /**
     * Displays the available menu categories, prompts the user to enter a category,
     * and returns the input.
     * If an invalid input is entered, it prompts the user again.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The menu category entered by the user.
     */
    private MenuCategory enterCategory(Scanner scanner){
        System.out.println("Available categories: ");
        Arrays.stream(MenuCategory.values()).forEach(System.out::println);
        System.out.print("\nEnter category: ");
        try {
            return MenuCategory.valueOf(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Try again. ");
            return enterCategory(scanner);
        }   
    }

    /**
     * Prompts the user to enter a new menu item price and returns the input.
     * If an invalid input is entered, it prompts the user again.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The new menu item price entered by the user.
     */
    private Double newPrice(Scanner scanner){
        try {
            double price = scanner.nextDouble();
            return price;
        } catch (Exception e) {
            System.out.println("Input numbers only.");
            return newPrice(scanner);
        }
    }

    /**
     * Displays the available menu categories, prompts the user to enter a new category,
     * and returns the input.
     * If an invalid input is entered, it prompts the user again.
     *
     * @param scanner The Scanner object used to read user input.
     * @return The new menu category entered by the user.
     */
    private MenuCategory newCategory(Scanner scanner){
        System.out.println("Available categories: ");
        Arrays.stream(MenuCategory.values()).forEach(System.out::println);
        System.out.print("\nEnter category: ");
        try {
            return MenuCategory.valueOf(scanner.next());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid category. Try again. ");
            return newCategory(scanner);
        }   
    }

}
