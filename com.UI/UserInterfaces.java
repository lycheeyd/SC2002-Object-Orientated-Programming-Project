package com.UI;

import java.util.Scanner;

import com.cache.BranchCache;
import com.cache.EmployeeCache;
import com.users.Employee;
import com.users.Manager;
import com.users.Staff;
import com.users.StaffRole;

public class UserInterfaces {

    public static void mainDisplay(Scanner scanner) {
        System.out.println("[=+=] Fast Food Management System [=+=]");
        System.out.println("(1) Order");
        System.out.println("(2) Staff Login");
        System.out.println("(3) Shutdown");
        System.out.print("\nWaiting for user input: ");
        int choice;
        
        do {
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    selectBranch(scanner);
                    break;
                case 2:
                    System.out.println("Enter User ID: ");
                    String loginID = scanner.nextLine();
                    System.out.println("Enter Password: ");
                    String password = scanner.nextLine();
                    
                    


                default:
                    System.out.println("Invalid response. Try again.");
                    break;
            }
        } while (choice != 1 && choice != 2 && choice != 3);
    }

    public static void selectBranch(Scanner scanner) {
        System.out.println("[=+=] Select Branch [=+=]");

        System.out.println("(1) Order");
        System.out.println("(2) Staff Login");
        System.out.println("(3) Shutdown");
        System.out.print("\nWaiting for user input: ");
    }

    public static void OrderDisplay(Scanner scanner) {
        System.out .println("f");
        for (i in menuCache.getValue)
    }

    public static Employee loginDisplay(Scanner scanner) {
        System.out.println("[=+=] Staff Login [=+=]");
        System.out.print("Enter User ID: ");
        String loginID = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        if (Authenticator.Authenticate(loginID, password)) {
            return EmployeeCache.getInstance().getItem(loginID);
        } else {
            System.out.println("Wrong User ID or Password!");
            System.out.println("(1) Try again.");
            System.out.println("(Any) Return to previous page.");
            String input = scanner.nextLine();
            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return null;
            }
            switch (choice) {
                case 1:
                    return loginDisplay(scanner);
                default:
                    System.out.println("Returning..");
                    return null;
            }
        }
    }
}
