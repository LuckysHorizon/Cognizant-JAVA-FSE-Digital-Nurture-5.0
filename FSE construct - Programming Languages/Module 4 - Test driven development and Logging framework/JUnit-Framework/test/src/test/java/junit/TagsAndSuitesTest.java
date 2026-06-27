package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tags and Suites")
class TagsAndSuitesTest {

    private final MathService mathService = new MathService();

    @Test
    @Tag("fast")
    void fastAdditionTest() {
        assertEquals(10, mathService.add(5, 5));
    }

    @Test
    @Tag("fast")
    void fastSubtractionTest() {
        assertEquals(5, mathService.subtract(10, 5));
    }

    @Test
    @Tag("slow")
    void slowPrimeTest() {
        assertTrue(mathService.isPrime(7919));
    }

    @Test
    @Tag("math")
    void mathFactorialTest() {
        assertEquals(120, mathService.factorial(5));
    }
}
