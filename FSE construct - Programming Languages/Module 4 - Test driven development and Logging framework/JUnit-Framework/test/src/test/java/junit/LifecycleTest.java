package junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("JUnit 5 Lifecycle Methods")
class LifecycleTest {

    private TaskManager taskManager;
    private static int testCount;

    @BeforeAll
    static void initializeAll() {
        testCount = 0;
        System.out.println("[BeforeAll] Test suite starting");
    }

    @BeforeEach
    void setUp() {
        taskManager = new TaskManager();
        testCount++;
        System.out.println("[BeforeEach] Setting up test #" + testCount);
    }

    @Test
    void testAddTask() {
        taskManager.addTask("Study JUnit");
        assertEquals(1, taskManager.getTaskCount());
    }

    @Test
    void testTaskManagerStartsEmpty() {
        assertEquals(0, taskManager.getTaskCount());
    }

    @AfterEach
    void tearDown() {
        taskManager.clearAllTasks();
        System.out.println("[AfterEach] Cleaning up after test #" + testCount);
    }

    @AfterAll
    static void cleanUpAll() {
        System.out.println("[AfterAll] Test suite completed. Total tests: " + testCount);
    }
}
