package com.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.FOMSApp;

public class StartupUI implements AppUI {

    public void displayMenu(Scanner scanner) {

        int choice = 0;

        do {
            System.out.println("[=+=] Fast Food Management System [=+=]");
            System.out.println("(1) New Order");
            System.out.println("(2) Check Order Status / Collect Order");
            System.out.println("(3) Staff Login");
            System.out.println("(4) Shutdown");
            System.out.print("\nWaiting for user input: ");

            try {    
                choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        new OrderUI().displayMenu(scanner);
                        break;
                    case 2:
                        new TrackOrderUI().displayMenu(scanner);
                        break;
                    case 3:
                        new LoginUI().displayMenu(scanner);
                        break;
                    case 4:
                        scanner.close();
                        System.out.println("Shutting down...");
                        FOMSApp.Shutdown();
                        break;
                    default:
                        System.out.println("Invalid response. Try again.\n");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.\n");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("\nUnexpected error occurred which has been passed up to apex handler.\n" + e.getMessage());
                System.out.println("\n[=+=] System reloaded. Please start over.\n");
            }
        } while (choice != 4);
    }
}
