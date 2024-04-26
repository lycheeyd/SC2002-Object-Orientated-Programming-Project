package com.UI;
import com.users.Authenticator;
import com.users.Employee;
import com.cache.EmployeeCache;

import java.util.Scanner;

/**
 * Represents the user interface for staff to log in.
 * @see AppUI
 */
public class LoginUI implements AppUI{

    /**
     * Default constructor.
     */
    public LoginUI() {};

    /**
     * An instance of EmployeeCache to access employee data.
     */
    EmployeeCache employeeCache = EmployeeCache.getInstance();

    /**
     * Displays the login menu and handles user authentication.
     *
     * @param scanner The Scanner object used to read user input.
     */
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

    /**
     * Prompts the user to try again or return to the previous page after a failed login attempt.
     *
     * @param scanner The Scanner object used to read user input.
     */
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