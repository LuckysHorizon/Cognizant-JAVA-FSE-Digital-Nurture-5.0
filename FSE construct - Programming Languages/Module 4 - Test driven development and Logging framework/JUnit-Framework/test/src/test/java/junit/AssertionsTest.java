package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit 5 Assertions")
class AssertionsTest {

    private final MathService mathService = new MathService();

    @Test
    void testAssertEquals() {
        assertEquals(10, mathService.add(4, 6));
    }

    @Test
    void testAssertNotEquals() {
        assertNotEquals(0, mathService.add(4, 6));
    }

    @Test
    void testAssertTrue() {
        assertTrue(mathService.isEven(4));
    }

    @Test
    void testAssertFalse() {
        assertFalse(mathService.isEven(5));
    }

    @Test
    void testAssertNull() {
        String value = null;
        assertNull(value);
    }

    @Test
    void testAssertNotNull() {
        assertNotNull(mathService);
    }

    @Test
    void testAssertArrayEquals() {
        int[] expected = {1, 2, 3};
        int[] actual = {1, 2, 3};
        assertArrayEquals(expected, actual);
    }

    @Test
    void testAssertIterableEquals() {
        List<String> expected = Arrays.asList("A", "B", "C");
        List<String> actual = Arrays.asList("A", "B", "C");
        assertIterableEquals(expected, actual);
    }

    @Test
    void testAssertAll() {
        assertAll("Math operations",
                () -> assertEquals(10, mathService.add(5, 5)),
                () -> assertEquals(0, mathService.subtract(5, 5)),
                () -> assertTrue(mathService.isEven(10))
        );
    }

    @Test
    void testAssertSame() {
        MathService service1 = mathService;
        assertSame(mathService, service1);
    }

    @Test
    void testAssertNotSame() {
        MathService service1 = new MathService();
        MathService service2 = new MathService();
        assertNotSame(service1, service2);
    }
}
