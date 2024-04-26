package com.users;

import java.util.Scanner;

/**
 * The Authenticator class handles authentication logic for employees.
 */
public class Authenticator {

    /**
    * Constructs an Authenticator object.
    */
    public Authenticator() {};
    
    /**
     * Authenticates an employee's password.
     * If the password is the default password, prompts the user to change it.
     * @param employee The employee to authenticate.
     * @param password The password to authenticate.
     * @return True if authentication is successful, false otherwise.
     */
    public boolean Authenticate(Employee employee, String password) {
        String realPassword = employee.getPassword();
        if (realPassword.equals(password)) {
            if (realPassword.equals("password")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nYour password is the default password. Please update your password.");
                changePassword(employee, scanner);
                return true;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * Changes the employee's password.
     * @param employee The employee whose password will be changed.
     * @param scanner A Scanner object to read input.
     * @return The new password.
     */
    public String changePassword(Employee employee, Scanner scanner) {
        System.out.print("Enter new password: ");
        String password = scanner.next();
        System.out.print("Re-enter new password: ");
        String rePassword = scanner.next();
        if (password.equals(rePassword)) {
            employee.setPassword(password);
            return password;
        } else {
            System.out.println("\nPassword does not match. Try again.");
            return changePassword(employee, scanner);
        }
    }
}
