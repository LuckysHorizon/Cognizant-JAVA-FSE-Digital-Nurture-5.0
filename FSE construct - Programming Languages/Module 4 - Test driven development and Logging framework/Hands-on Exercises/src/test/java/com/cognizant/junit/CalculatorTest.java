package com.cognizant.junit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @AfterEach
    void tearDown() {
        calculator = null;
    }

    @Test
    void testAdd() {
        int a = 5;
        int b = 3;

        int result = calculator.add(a, b);

        assertEquals(8, result, "5 + 3 should equal 8");
    }

    @Test
    void testSubtract() {
        int a = 10;
        int b = 4;

        int result = calculator.subtract(a, b);

        assertEquals(6, result, "10 - 4 should equal 6");
        assertTrue(result > 0);
    }

    @Test
    void testDivideByZeroThrowsException() {
        int a = 10;
        int b = 0;

        assertNotNull(calculator);
        
        assertThrows(ArithmeticException.class, () -> {
            calculator.divide(a, b);
        }, "Dividing by zero should throw ArithmeticException");
    }
}
