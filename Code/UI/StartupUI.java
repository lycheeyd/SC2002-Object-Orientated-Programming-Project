package com.UI;

import java.util.InputMismatchException;
import java.util.Scanner;

import com.FOMSApp;

/**
 * Represents the user interface after booting up the system.
 * @see AppUI
 */
public class StartupUI implements AppUI {

    /**
     * Default constructor.
     */
    public StartupUI() {};
    
    /**
     * Displays the main menu for the Fast Food Management System and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
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
                        FOMSApp.Shutdown();
                        return;
                    default:
                        System.out.println("Invalid response. Try again.\n");
                        break;
                }
            } catch(InputMismatchException e) {
                System.out.println("Error input! Enter only numbers.\n");
                scanner.nextLine();
            } catch (Exception e) { //
                System.out.println("\nUnexpected error occurred which has been passed up to apex handler.\n" + e.getMessage());
                System.out.println("\n[=+=] System reloaded. Please start over.\n");
                scanner.nextLine();
            }
        } while (true);
    }
}
