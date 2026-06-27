package logging;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Logging Basics Tests")
class LoggingBasicsTest {

    private final LoggingBasics loggingBasics = new LoggingBasics();

    @Test
    void testDemonstrateLogLevels() {
        assertDoesNotThrow(() -> loggingBasics.demonstrateLogLevels());
    }

    @Test
    void testParameterizedLogging() {
        assertDoesNotThrow(() -> loggingBasics.demonstrateParameterizedLogging("TestUser", 3));
    }

    @Test
    void testExceptionLogging() {
        assertDoesNotThrow(() -> loggingBasics.demonstrateExceptionLogging());
    }

    @Test
    void testProcessData() {
        assertEquals("HELLO", loggingBasics.processData("  hello  "));
    }

    @Test
    void testProcessNullData() {
        assertEquals("", loggingBasics.processData(null));
    }
}
