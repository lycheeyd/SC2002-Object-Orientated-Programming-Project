package com.UI;

import java.util.Scanner;

import com.users.Employee;

public class Authenticator {
    
    public boolean Authenticate(Employee employee, String password) {
        String realPassword = employee.getPassword();
        if (realPassword.equals(password)) {
            if (realPassword.equals("password")) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nYour password is default password. Please update your password.");
                changePassword(employee, scanner);
                return true;
            } 
            else {
                return true;

            }
        }
        return false;
        
    }

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
