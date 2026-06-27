package miniprojects;

import miniprojects.employeemgmt.Employee;
import miniprojects.employeemgmt.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Employee Service Tests")
class EmployeeServiceTest {

    private EmployeeService service;

    @BeforeEach
    void setUp() {
        service = new EmployeeService();
        service.addEmployee(new Employee(1, "Alice", "Engineering", 80000));
        service.addEmployee(new Employee(2, "Bob", "Engineering", 75000));
        service.addEmployee(new Employee(3, "Charlie", "Marketing", 65000));
    }

    @Test
    void testAddEmployee() {
        assertEquals(3, service.getEmployeeCount());
    }

    @Test
    void testGetEmployee() {
        assertEquals("Alice", service.getEmployee(1).getName());
    }

    @Test
    void testGetByDepartment() {
        assertEquals(2, service.getByDepartment("Engineering").size());
    }

    @Test
    void testTotalSalary() {
        assertEquals(220000, service.getTotalSalary());
    }

    @Test
    void testRemoveEmployee() {
        assertTrue(service.removeEmployee(1));
        assertEquals(2, service.getEmployeeCount());
    }

    @Test
    void testGetEmployeeNotFound() {
        assertThrows(RuntimeException.class, () -> service.getEmployee(99));
    }
}
