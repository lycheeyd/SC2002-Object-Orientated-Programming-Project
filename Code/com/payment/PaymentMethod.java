package com.payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a collection of payment methods and provides methods to manipulate them.
 */
public class PaymentMethod {

    /**
     * Default constructor.
     */
    public PaymentMethod() {};

    /**
     * Stores a list of available payment methods used during payment.
     */
    private static List<String> paymentMethods = new ArrayList<>(Arrays.asList("CREDIT_CARD", "DEBIT_CARD", "CASH", "PAYPAL", "NETS"));

    
    /** 
     * Adds a new payment method to the list if it doesn't already exist.
     *
     * @param method The payment method to add.
     */
    public static void addValue(String method) {
        if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
            System.out.println("Added new payment method: " + method);
        } else {
            System.out.println("Payment method already exists: " + method);
        }
    }

    /** 
     * Removes a payment method from the list if it exists.
     *
     * @param method The payment method to remove.
     */
    public static void removeValue(String method) {
        if (paymentMethods.contains(method)) {
            paymentMethods.remove(method);
            System.out.println("Removed payment method: " + method);
        } else {
            System.out.println("Payment method does not exist: " + method);
        }
    }
    
    /** 
     * Retrieves the list of available payment methods.
     *
     * @return The list of payment methods.
     */
    public static List<String> getOptions() {
        return paymentMethods;
    }

    /** 
     * Processes a payment using the specified method and cost.
     *
     * @param method The payment method to use.
     * @param cost The cost of the transaction.
     * @return The status of the payment processing.
     */
    public static PaymentStatus processPayment(String method, double cost) {
        System.out.println("Processing payment with " + method);
        System.out.println("Payment successful.\n");
        return PaymentStatus.SUCCESSFUL;
    }
}
