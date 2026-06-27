package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class EmployeeServiceLogger {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeServiceLogger.class);
    private final Map<Integer, String> employees = new HashMap<>();
    private int idCounter = 1;

    public int addEmployee(String name) {
        logger.info("Adding employee: {}", name);
        if (name == null || name.isBlank()) {
            logger.error("Cannot add employee with blank name");
            throw new IllegalArgumentException("Employee name cannot be blank");
        }
        int id = idCounter++;
        employees.put(id, name);
        logger.info("Employee added - ID: {}, Name: {}", id, name);
        return id;
    }

    public String getEmployee(int id) {
        logger.debug("Fetching employee with ID: {}", id);
        String name = employees.get(id);
        if (name == null) {
            logger.warn("Employee not found with ID: {}", id);
        }
        return name;
    }

    public boolean removeEmployee(int id) {
        logger.info("Removing employee with ID: {}", id);
        if (!employees.containsKey(id)) {
            logger.warn("Cannot remove - Employee not found: {}", id);
            return false;
        }
        employees.remove(id);
        logger.info("Employee removed successfully - ID: {}", id);
        return true;
    }

    public int getEmployeeCount() {
        logger.trace("Employee count requested: {}", employees.size());
        return employees.size();
    }
}
