package miniprojects;

import miniprojects.studentmgmt.Student;
import miniprojects.studentmgmt.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Student Service Tests")
class StudentServiceTest {

    private StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentService();
        service.addStudent(new Student(1, "Alice", "CS", 3.8));
        service.addStudent(new Student(2, "Bob", "Math", 3.5));
    }

    @Test
    void testAddStudent() {
        service.addStudent(new Student(3, "Charlie", "Physics", 3.9));
        assertEquals(3, service.getStudentCount());
    }

    @Test
    void testAddDuplicateStudent() {
        assertThrows(IllegalArgumentException.class,
                () -> service.addStudent(new Student(1, "Duplicate", "CS", 3.0)));
    }

    @Test
    void testGetStudent() {
        assertEquals("Alice", service.getStudent(1).getName());
    }

    @Test
    void testGetStudentNotFound() {
        assertThrows(RuntimeException.class, () -> service.getStudent(99));
    }

    @Test
    void testRemoveStudent() {
        assertTrue(service.removeStudent(1));
        assertEquals(1, service.getStudentCount());
    }

    @Test
    void testAverageGrade() {
        assertEquals(3.65, service.getAverageGrade(), 0.01);
    }
}
