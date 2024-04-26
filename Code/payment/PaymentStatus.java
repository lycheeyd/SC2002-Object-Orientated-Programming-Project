package com.payment;

/**
 * Enumerates the possible statuses of a payment transaction.
 */
public enum PaymentStatus {
    /** The payment transaction was successful. */
    SUCCESSFUL, 
    /** The payment transaction failed. */
    FAILED, 
    /** The payment transaction was cancelled. */
    CANCELLED;

}
