package test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(OrderAnnotation.class)
public class AdvancedJUnitTest {

    private Calculator calc;

    @BeforeAll
    static void beforeAllTests() {
        System.out.println("========== Test Execution Started ==========");
    }

    @BeforeEach
    void setup() {
        calc = new Calculator();
        System.out.println("Calculator Object Created");
    }

    @Test
    @Order(1)
    @Tag("basic")
    void testAddition() {

        int result = calc.add(10, 20);

        assertEquals(30, result);
    }

    @Test
    @Order(2)
    @Tag("basic")
    void testMultiplication() {

        int result = calc.multiply(5, 5);

        assertEquals(25, result);
    }

    @Test
    @Order(3)
    @Tag("boolean")
    void testTrueCondition() {

        assertTrue(20 > 10);
    }

    @Test
    @Order(4)
    void testFalseCondition() {

        assertFalse(5 > 10);
    }

    @Test
    @Order(5)
    void testNotNull() {

        assertNotNull(calc);
    }

    @ParameterizedTest
    @ValueSource(ints = {2,4,6,8,10})
    @Order(6)
    @Tag("parameterized")
    void testSquare(int number) {

        int square = number * number;

        assertEquals(number * number, square);
    }

    @Test
    @Order(7)
    @Tag("exception")
    void testException() {

        assertThrows(ArithmeticException.class, () -> {

            int result = 10 / 0;

        });
    }

    @Test
    @Order(8)
    @Tag("performance")
    void testTimeout() {

        assertTimeout(Duration.ofSeconds(2), () -> {

            Thread.sleep(1000);

        });
    }

    @AfterEach
    void tearDown() {
        System.out.println("Cleaning Resources");
    }

    @AfterAll
    static void afterAllTests() {
        System.out.println("========== Test Execution Completed ==========");
    }
}