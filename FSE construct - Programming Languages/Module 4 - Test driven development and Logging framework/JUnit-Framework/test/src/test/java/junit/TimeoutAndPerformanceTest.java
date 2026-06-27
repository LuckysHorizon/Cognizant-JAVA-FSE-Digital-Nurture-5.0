package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Timeout and Performance Tests")
class TimeoutAndPerformanceTest {

    private final MathService mathService = new MathService();

    @Test
    void testAssertTimeout() {
        assertTimeout(Duration.ofMillis(500), () -> {
            int result = mathService.add(100, 200);
            assertEquals(300, result);
        });
    }

    @Test
    void testAssertTimeoutPreemptively() {
        assertTimeoutPreemptively(Duration.ofSeconds(1), () -> {
            for (int i = 0; i < 1000; i++) {
                mathService.isPrime(i);
            }
        });
    }

    @Test
    @Timeout(value = 2, unit = TimeUnit.SECONDS)
    void testWithTimeoutAnnotation() {
        int result = mathService.factorial(10);
        assertEquals(3628800, result);
    }

    @Test
    void testPerformanceBenchmark() {
        long startTime = System.nanoTime();
        for (int i = 0; i < 10000; i++) {
            mathService.add(i, i);
        }
        long duration = System.nanoTime() - startTime;
        assertTrue(duration < 1_000_000_000, "Operations should complete within 1 second");
    }
}
