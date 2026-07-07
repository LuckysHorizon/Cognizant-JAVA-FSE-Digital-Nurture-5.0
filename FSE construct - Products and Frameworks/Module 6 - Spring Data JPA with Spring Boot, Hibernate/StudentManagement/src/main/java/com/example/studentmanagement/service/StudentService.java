package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.projection.StudentProjection;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student updateStudent(Integer id,Student student);
    void deleteStudent(Integer id);
    List<Student> getStudentByName(String name);

    Page<Student> getStudents(int page, int size);
    Page<Student> getStudentsSortedByName(int page, int size);
    Page<Student> getStudentsSortedByAgeDesc(int page, int size);
    Page<Student> getStudentsSortedByAgeDescAndName(int page, int size);

    List<StudentProjection> getStudentsByAge(Integer age);
}