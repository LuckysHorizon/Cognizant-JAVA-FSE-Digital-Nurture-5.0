package com.example.studentmanagement.service;

import com.example.studentmanagement.entity.Student;

import java.util.List;

public interface StudentService {

    Student saveStudent(Student student);
    List<Student> getAllStudents();
    Student getStudentById(Integer id);
    Student updateStudent(Integer id,Student student);
    void deleteStudent(Integer id);
    List<Student> getStudentByName(String name);
}