package junit;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Execution Order")
class ExecutionOrderTest {

    private static final TaskManager sharedManager = new TaskManager();

    @Test
    @Order(1)
    void firstTest() {
        sharedManager.addTask("Task 1");
        assertEquals(1, sharedManager.getTaskCount());
    }

    @Test
    @Order(2)
    void secondTest() {
        sharedManager.addTask("Task 2");
        assertEquals(2, sharedManager.getTaskCount());
    }

    @Test
    @Order(3)
    void thirdTest() {
        assertTrue(sharedManager.containsTask("Task 1"));
        assertTrue(sharedManager.containsTask("Task 2"));
    }

    @Test
    @Order(4)
    void fourthTest() {
        sharedManager.clearAllTasks();
        assertEquals(0, sharedManager.getTaskCount());
    }
}
