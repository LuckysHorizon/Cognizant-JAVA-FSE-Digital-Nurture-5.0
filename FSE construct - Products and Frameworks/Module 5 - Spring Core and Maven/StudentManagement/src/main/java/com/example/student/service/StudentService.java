package com.example.student.service;

import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepository repository;

    @Autowired
    public StudentService(StudentRepository repository)
    {
        this.repository = repository;
        System.out.println("StudentService Bean Created");
    }
    public void registerStudent()
    {
        System.out.println("Business Logic Started");
        repository.saveStudent();
        System.out.println("Business Logic Finished");
    }
}
