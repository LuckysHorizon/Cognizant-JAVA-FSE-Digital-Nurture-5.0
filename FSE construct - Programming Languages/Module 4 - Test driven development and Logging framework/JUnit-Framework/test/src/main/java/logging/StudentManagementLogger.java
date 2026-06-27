package logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class StudentManagementLogger {

    private static final Logger logger = LoggerFactory.getLogger(StudentManagementLogger.class);
    private final Map<Integer, Map<String, Object>> students = new LinkedHashMap<>();
    private int idCounter = 1;

    public int enrollStudent(String name, String course) {
        logger.info("Enrolling student - Name: {}, Course: {}", name, course);
        int id = idCounter++;
        Map<String, Object> studentData = new HashMap<>();
        studentData.put("name", name);
        studentData.put("course", course);
        studentData.put("grades", new ArrayList<Integer>());
        students.put(id, studentData);
        logger.info("Student enrolled - ID: {}", id);
        return id;
    }

    @SuppressWarnings("unchecked")
    public void addGrade(int studentId, int grade) {
        logger.debug("Adding grade {} for student ID: {}", grade, studentId);
        Map<String, Object> student = students.get(studentId);
        if (student == null) {
            logger.error("Student not found: {}", studentId);
            throw new RuntimeException("Student not found: " + studentId);
        }
        if (grade < 0 || grade > 100) {
            logger.warn("Invalid grade value: {} for student: {}", grade, studentId);
            throw new IllegalArgumentException("Grade must be between 0 and 100");
        }
        List<Integer> grades = (List<Integer>) student.get("grades");
        grades.add(grade);
        logger.info("Grade added - Student: {}, Grade: {}, Total Grades: {}", studentId, grade, grades.size());
    }

    @SuppressWarnings("unchecked")
    public double calculateAverage(int studentId) {
        logger.debug("Calculating average for student: {}", studentId);
        Map<String, Object> student = students.get(studentId);
        if (student == null) {
            logger.error("Cannot calculate average - Student not found: {}", studentId);
            throw new RuntimeException("Student not found: " + studentId);
        }
        List<Integer> grades = (List<Integer>) student.get("grades");
        if (grades.isEmpty()) {
            logger.warn("No grades available for student: {}", studentId);
            return 0.0;
        }
        double average = grades.stream().mapToInt(Integer::intValue).average().orElse(0);
        logger.info("Average calculated - Student: {}, Average: {}", studentId, average);
        return average;
    }

    public int getStudentCount() {
        return students.size();
    }
}
