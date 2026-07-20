package com.cognizant.slf4j;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PaymentProcessor {

    private static final Logger logger = LoggerFactory.getLogger(PaymentProcessor.class);

    public void processPayment(String transactionId, double amount) {
        if (amount <= 0) {
            logger.error("Failed to process payment {}: Invalid amount {}", transactionId, amount);
            throw new IllegalArgumentException("Payment amount must be greater than zero");
        }

        if (amount > 10000) {
            logger.warn("Large payment {} detected. Amount: {}. Requires manual review.", transactionId, amount);
        }

        logger.info("Successfully processed payment {} for amount {}", transactionId, amount);
    }
}
