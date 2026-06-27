package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingBestPractices {

    private static final Logger logger = LoggerFactory.getLogger(LoggingBestPractices.class);

    public void useParameterizedLogging(String name, int age) {
        logger.info("User details - Name: {}, Age: {}", name, age);
    }

    public void useGuardedLogging(Object expensiveObject) {
        if (logger.isDebugEnabled()) {
            logger.debug("Expensive computation result: {}", expensiveObject.toString());
        }
    }

    public void logAtAppropriateLevel() {
        logger.trace("Entering method logAtAppropriateLevel");
        logger.debug("Variable state during computation");
        logger.info("Business operation completed");
        logger.warn("Deprecated API call detected");
        logger.error("Failed to process critical operation");
    }

    public void logExceptionsCorrectly() {
        try {
            throw new RuntimeException("Sample exception");
        } catch (RuntimeException e) {
            logger.error("Operation failed due to: {}", e.getMessage(), e);
        }
    }

    public String processWithLogging(String input) {
        logger.info("Processing started for input length: {}", input != null ? input.length() : 0);
        if (input == null) {
            logger.warn("Null input received, returning default");
            return "default";
        }
        String result = input.toUpperCase();
        logger.info("Processing completed successfully");
        return result;
    }
}
