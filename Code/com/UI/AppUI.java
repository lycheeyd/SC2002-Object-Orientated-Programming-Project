package com.UI;

import java.util.Scanner;

/**
 * An interface that defines the contract for displaying a menu-based user interface.
 */
public interface AppUI {
    /**
     * Displays a menu and handles user input.
     *
     * @param scanner The Scanner object used to read user input.
     */
    void displayMenu(Scanner scanner);
}
