package com.example.studentmanagement.repository;

import com.example.studentmanagement.dto.StudentDTO;
import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.projection.StudentProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepository
        extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<StudentProjection> findByAge(Integer age);
    @Query("""
    SELECT new com.example.studentmanagement.dto.StudentDTO(
    s.name,
    s.email)
    FROM Student s
    """)
    List<StudentDTO> getStudentDTO();
}