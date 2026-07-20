package com.cognizant.slf4j;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PaymentProcessorTest {

    @Test
    void triggerLoggingScenarios() {
        PaymentProcessor processor = new PaymentProcessor();

        processor.processPayment("TXN-001", 150.00);

        processor.processPayment("TXN-002", 15000.00);

        assertThrows(IllegalArgumentException.class, () -> {
            processor.processPayment("TXN-003", -50.00);
        });
    }
}
