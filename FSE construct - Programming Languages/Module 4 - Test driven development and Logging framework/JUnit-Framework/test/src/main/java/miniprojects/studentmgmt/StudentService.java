package miniprojects.studentmgmt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class StudentService {

    private static final Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final Map<Integer, Student> students = new HashMap<>();

    public void addStudent(Student student) {
        logger.info("Adding student: {}", student.getName());
        if (students.containsKey(student.getId())) {
            throw new IllegalArgumentException("Student with ID " + student.getId() + " already exists");
        }
        students.put(student.getId(), student);
    }

    public Student getStudent(int id) {
        logger.debug("Fetching student with ID: {}", id);
        Student student = students.get(id);
        if (student == null) {
            throw new RuntimeException("Student not found: " + id);
        }
        return student;
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students.values());
    }

    public boolean removeStudent(int id) {
        logger.info("Removing student with ID: {}", id);
        return students.remove(id) != null;
    }

    public double getAverageGrade() {
        if (students.isEmpty()) return 0.0;
        return students.values().stream()
                .mapToDouble(Student::getGrade)
                .average().orElse(0.0);
    }

    public int getStudentCount() {
        return students.size();
    }
}
