package test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CalculatorTest {

    private Calculator calc;

    @BeforeAll
    static void initialize() {
        System.out.println("========== Test Execution Started ==========");
    }

    @BeforeEach
    void setup() {
        calc = new Calculator();
        System.out.println("Calculator Object Created");
    }

    @Test
    void testAddition() {

        // Arrange
        int a = 5;
        int b = 3;

        // Act
        int result = calc.add(a, b);

        // Assert
        assertEquals(8, result, "Addition test failed");
    }

    @Test
    void testMultiplication() {

        // Arrange
        int a = 5;
        int b = 5;

        // Act
        int result = calc.multiply(a, b);

        // Assert
        assertEquals(25, result, "Multiplication test failed");
    }

    @Test
    void testBoolean() {

        // Arrange & Act
        boolean result = (10 > 5);

        // Assert
        assertTrue(result, "Condition should be true");
    }

    @Test
    void testNotNull() {

        // Arrange & Act
        String language = "Java";

        // Assert
        assertNotNull(language, "Object should not be null");
    }

    @Test
    void testRandom() {

        // Arrange & Act
        int result = calc.random();

        // Assert
        assertNotEquals(10, result, "Random number should not be 10");
    }

    @Test
    void testAdditionFalse() {

        // Arrange
        int a = 3;
        int b = 2;

        // Act
        int result = calc.add(a, b);

        // Assert
        assertFalse(result == 6, "Addition should not be 6");
    }

    @Test
    void testSameObject() {

        // Arrange
        Calculator calculator = calc;

        // Assert
        assertSame(calc, calculator, "Both references should point to the same object");
    }

    @Test
    void testNotSameObject() {

        // Arrange
        Calculator calculator1 = new Calculator();
        Calculator calculator2 = new Calculator();

        // Assert
        assertNotSame(calculator1, calculator2, "Objects should not be the same");
    }

    @Test
    void testNull() {

        // Arrange
        String value = null;

        // Assert
        assertNull(value, "Value should be null");
    }

    @Test
    void testArgumentMatcher() {

        Calculator calculator = mock(Calculator.class);

        // Match any integer values
        when(calculator.add(anyInt(), anyInt())).thenReturn(100);

        assertEquals(100, calculator.add(10, 20));
        assertEquals(100, calculator.add(5, 7));
        assertEquals(100, calculator.add(200, 300));

        verify(calculator, times(3)).add(anyInt(), anyInt());
    }

    @AfterEach
    void cleanUp() {
        System.out.println("Cleaning Resources");
    }

    @AfterAll
    static void finish() {
        System.out.println("========== Test Execution Completed ==========");
    }
}