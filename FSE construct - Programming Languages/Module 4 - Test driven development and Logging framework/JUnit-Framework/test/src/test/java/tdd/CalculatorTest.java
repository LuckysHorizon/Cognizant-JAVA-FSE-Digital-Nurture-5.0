package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TDD Calculator Tests")
class CalculatorTest {

    private Calculator calculator;

    @BeforeEach
    void setUp() {
        calculator = new Calculator();
    }

    @Test
    @DisplayName("Addition of two positive numbers")
    void testAddition() {
        assertEquals(8, calculator.add(5, 3));
    }

    @Test
    @DisplayName("Addition with negative numbers")
    void testAdditionWithNegatives() {
        assertEquals(-2, calculator.add(-5, 3));
    }

    @Test
    @DisplayName("Subtraction returns correct difference")
    void testSubtraction() {
        assertEquals(2, calculator.subtract(5, 3));
    }

    @Test
    @DisplayName("Multiplication of two numbers")
    void testMultiplication() {
        assertEquals(15, calculator.multiply(5, 3));
    }

    @Test
    @DisplayName("Division of two numbers")
    void testDivision() {
        assertEquals(2.5, calculator.divide(5, 2));
    }

    @Test
    @DisplayName("Division by zero throws ArithmeticException")
    void testDivisionByZero() {
        assertThrows(ArithmeticException.class, () -> calculator.divide(5, 0));
    }

    @Test
    @DisplayName("Modulo operation")
    void testModulo() {
        assertEquals(1, calculator.modulo(5, 2));
    }

    @Test
    @DisplayName("Power calculation")
    void testPower() {
        assertEquals(8, calculator.power(2, 3));
    }

    @Test
    @DisplayName("Power with zero exponent returns 1")
    void testPowerWithZeroExponent() {
        assertEquals(1, calculator.power(5, 0));
    }

    @Test
    @DisplayName("Power with negative exponent throws exception")
    void testPowerWithNegativeExponent() {
        assertThrows(IllegalArgumentException.class, () -> calculator.power(2, -1));
    }
}
