package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Exception Testing")
class ExceptionTestingExamples {

    private final MathService mathService = new MathService();
    private final TaskManager taskManager = new TaskManager();

    @Test
    void testDivisionByZeroException() {
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> mathService.divide(10, 0));
        assertEquals("Division by zero", exception.getMessage());
    }

    @Test
    void testNegativeFactorialException() {
        assertThrows(IllegalArgumentException.class,
                () -> mathService.factorial(-1));
    }

    @Test
    void testNullTaskException() {
        assertThrows(IllegalArgumentException.class,
                () -> taskManager.addTask(null));
    }

    @Test
    void testIndexOutOfBoundsException() {
        assertThrows(IndexOutOfBoundsException.class,
                () -> taskManager.getTaskAt(0));
    }

    @Test
    void testDoesNotThrowException() {
        assertDoesNotThrow(() -> mathService.add(5, 3));
    }
}
