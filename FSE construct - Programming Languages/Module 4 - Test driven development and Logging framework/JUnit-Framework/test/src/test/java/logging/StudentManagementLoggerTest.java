package logging;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Student Management Logger Tests")
class StudentManagementLoggerTest {

    private StudentManagementLogger studentService;

    @BeforeEach
    void setUp() {
        studentService = new StudentManagementLogger();
    }

    @Test
    void testEnrollStudent() {
        int id = studentService.enrollStudent("Alice", "Computer Science");
        assertTrue(id > 0);
    }

    @Test
    void testAddGrade() {
        int id = studentService.enrollStudent("Bob", "Math");
        assertDoesNotThrow(() -> studentService.addGrade(id, 85));
    }

    @Test
    void testInvalidGrade() {
        int id = studentService.enrollStudent("Charlie", "Physics");
        assertThrows(IllegalArgumentException.class, () -> studentService.addGrade(id, 150));
    }

    @Test
    void testCalculateAverage() {
        int id = studentService.enrollStudent("Diana", "Biology");
        studentService.addGrade(id, 80);
        studentService.addGrade(id, 90);
        assertEquals(85.0, studentService.calculateAverage(id));
    }

    @Test
    void testAverageNoGrades() {
        int id = studentService.enrollStudent("Eve", "Chemistry");
        assertEquals(0.0, studentService.calculateAverage(id));
    }

    @Test
    void testStudentNotFound() {
        assertThrows(RuntimeException.class, () -> studentService.addGrade(999, 80));
    }
}
