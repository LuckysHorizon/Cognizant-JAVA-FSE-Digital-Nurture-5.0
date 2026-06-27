package miniprojects.employeemgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final Map<Integer, Employee> employees = new HashMap<>();

    public void addEmployee(Employee employee) {
        logger.info("Adding employee: {} to department: {}", employee.getName(), employee.getDepartment());
        employees.put(employee.getId(), employee);
    }

    public Employee getEmployee(int id) {
        Employee emp = employees.get(id);
        if (emp == null) {
            throw new RuntimeException("Employee not found: " + id);
        }
        return emp;
    }

    public List<Employee> getByDepartment(String department) {
        return employees.values().stream()
                .filter(e -> e.getDepartment().equals(department))
                .collect(Collectors.toList());
    }

    public double getTotalSalary() {
        return employees.values().stream().mapToDouble(Employee::getSalary).sum();
    }

    public boolean removeEmployee(int id) {
        return employees.remove(id) != null;
    }

    public int getEmployeeCount() {
        return employees.size();
    }
}
