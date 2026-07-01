package com.example.student.repository;

import org.springframework.stereotype.Repository;

@Repository
public class StudentRepository {
    public StudentRepository()
    {
        System.out.println("StudentRepository Bean Created");
    }
    public void saveStudent()
    {
        System.out.println("Student saved into Database");
    }
}
