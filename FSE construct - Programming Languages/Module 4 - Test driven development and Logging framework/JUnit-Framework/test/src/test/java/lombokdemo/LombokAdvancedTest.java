package lombokdemo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Lombok Advanced Tests")
class LombokAdvancedTest {

    @Test
    void testEmployeeEqualsAndHashCode() {
        Employee e1 = new Employee();
        e1.setId(1);
        e1.setName("Alice");
        e1.setDepartment("Engineering");
        e1.setSalary(75000);

        Employee e2 = new Employee();
        e2.setId(1);
        e2.setName("Alice");
        e2.setDepartment("Engineering");
        e2.setSalary(75000);

        assertEquals(e1, e2);
    }

    @Test
    void testEmployeeToString() {
        Employee emp = new Employee();
        emp.setName("Alice");
        assertTrue(emp.toString().contains("Alice"));
    }

    @Test
    void testRequiredArgsConstructor() {
        DatabaseConnection conn = new DatabaseConnection("jdbc:h2:mem:test", "sa");
        assertEquals("Connected to jdbc:h2:mem:test", conn.connect());
    }

    @Test
    void testSlf4jAnnotation() {
        DatabaseConnection conn = new DatabaseConnection("jdbc:h2:mem:test", "sa");
        assertDoesNotThrow(conn::connect);
        assertDoesNotThrow(conn::disconnect);
    }

    @Test
    void testDatabaseQuery() {
        DatabaseConnection conn = new DatabaseConnection("jdbc:h2:mem:test", "sa");
        String result = conn.executeQuery("SELECT * FROM users");
        assertNotNull(result);
        assertTrue(result.contains("SELECT"));
    }

    @Test
    void testDatabaseInvalidQuery() {
        DatabaseConnection conn = new DatabaseConnection("jdbc:h2:mem:test", "sa");
        assertThrows(IllegalArgumentException.class, () -> conn.executeQuery(""));
    }

    @Test
    void testImmutableConfigEquality() {
        ImmutableConfig c1 = new ImmutableConfig("host", 3306, "db", false);
        ImmutableConfig c2 = new ImmutableConfig("host", 3306, "db", false);
        assertEquals(c1, c2);
    }
}
