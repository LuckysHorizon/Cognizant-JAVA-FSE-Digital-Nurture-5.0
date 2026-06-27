package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingBasics {

    private static final Logger logger = LoggerFactory.getLogger(LoggingBasics.class);

    public void demonstrateLogLevels() {
        logger.trace("TRACE level: Most detailed information");
        logger.debug("DEBUG level: Debugging information");
        logger.info("INFO level: General information");
        logger.warn("WARN level: Warning - potential issue");
        logger.error("ERROR level: Error occurred");
    }

    public void demonstrateParameterizedLogging(String username, int attempts) {
        logger.info("User '{}' logged in after {} attempts", username, attempts);
        logger.debug("Login details - User: {}, Attempts: {}, Timestamp: {}", username, attempts, System.currentTimeMillis());
    }

    public void demonstrateExceptionLogging() {
        try {
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            logger.error("Division by zero error occurred", e);
        }

        try {
            String text = null;
            text.length();
        } catch (NullPointerException e) {
            logger.error("Null pointer exception: {}", e.getMessage(), e);
        }
    }

    public String processData(String data) {
        logger.info("Processing data: {}", data);
        if (data == null) {
            logger.warn("Received null data, returning empty string");
            return "";
        }
        logger.debug("Data length: {}", data.length());
        String processed = data.trim().toUpperCase();
        logger.info("Data processed successfully: {}", processed);
        return processed;
    }
}
