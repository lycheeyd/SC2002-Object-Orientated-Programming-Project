package com.UI;
import com.users.Authenticator;
import com.users.Employee;
import com.cache.EmployeeCache;

import java.util.Scanner;

public class LoginUI implements AppUI{

    EmployeeCache employeeCache = EmployeeCache.getInstance();

    public void displayMenu(Scanner scanner) {
        System.out.println("[=+=] Staff Login [=+=]");
        System.out.print("Enter User ID: ");
        String loginID = scanner.next().trim();
        Employee employee = employeeCache.getItem(loginID);
        System.out.print("Enter Password: ");
        String password = scanner.next().trim();

        if (employee != null) {
            if (new Authenticator().Authenticate(employee, password)) {
                switch (employee.getRole()) {
                    case S:
                        new StaffUI(employee).displayMenu(scanner);
                        break;
                    case M:
                        new ManagerUI(employee).displayMenu(scanner);
                        break;
                    case A:
                        new AdminUI(employee).displayMenu(scanner);
                        break;
                    default:
                        System.err.println("An error occurred. (Role not assigned)");
                        break;
                }
            } else {
                tryAgain(scanner);
            }
        } else {
            tryAgain(scanner);
        }
    }

    private void tryAgain(Scanner scanner) {
        System.out.println("\nWrong User ID or Password!");
        System.out.println("(1) Try again.");
        System.out.println("(Any) Return to previous page.");
        System.out.print("\nWaiting for user input: ");
        String input = scanner.next();
        try {
            int choice = Integer.parseInt(input);
            switch (choice) {
                case 1:
                    displayMenu(scanner);
                    break;
                default:
                    System.out.println("Invalid Input! Try again.");
                    tryAgain(scanner);
                    break;
            }
        } catch (NumberFormatException e) {
            System.out.println("[=+=] Returning to previous page...\n");
        }
    }
}
