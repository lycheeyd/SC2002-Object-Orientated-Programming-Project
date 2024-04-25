package com.payment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class PaymentMethod {

    private static List<String> paymentMethods = new ArrayList<>(Arrays.asList("CREDIT_CARD", "DEBIT_CARD", "CASH", "PAYPAL", "NETS"));

    public static void addValue(String method) {
        if (!paymentMethods.contains(method)) {
            paymentMethods.add(method);
            System.out.println("Added new payment method: " + method);
        } else {
            System.out.println("Payment method already exists: " + method);
        }
    }

    public static void removeValue(String method) {
        if (paymentMethods.contains(method)) {
            paymentMethods.remove(method);
            System.out.println("Removed payment method: " + method);
        } else {
            System.out.println("Payment method does not exist: " + method);
        }
    }
    
    public static List<String> getOptions() {
        return paymentMethods;
    }

    public static PaymentStatus processPayment(String method, double cost) {
        System.out.println("Processing payment with " + method);
        System.out.println("Payment successful.\n");
        return PaymentStatus.SUCCESSFUL;
    }
}
