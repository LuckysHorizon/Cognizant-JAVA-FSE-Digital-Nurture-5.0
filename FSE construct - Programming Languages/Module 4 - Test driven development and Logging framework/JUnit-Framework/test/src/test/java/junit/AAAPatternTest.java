package junit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("AAA Pattern Examples")
class AAAPatternTest {

    @Test
    @DisplayName("Arrange-Act-Assert: Addition")
    void testAdditionAAA() {
        // Arrange
        MathService service = new MathService();
        int a = 10;
        int b = 20;

        // Act
        int result = service.add(a, b);

        // Assert
        assertEquals(30, result);
    }

    @Test
    @DisplayName("Arrange-Act-Assert: Task Management")
    void testTaskManagementAAA() {
        // Arrange
        TaskManager manager = new TaskManager();

        // Act
        manager.addTask("Learn JUnit");
        manager.addTask("Practice TDD");

        // Assert
        assertEquals(2, manager.getTaskCount());
        assertTrue(manager.containsTask("Learn JUnit"));
    }

    @Test
    @DisplayName("Arrange-Act-Assert: Validation")
    void testUserValidationAAA() {
        // Arrange
        UserValidator validator = new UserValidator();
        String email = "user@example.com";

        // Act
        boolean isValid = validator.isValidEmail(email);

        // Assert
        assertTrue(isValid);
    }
}
