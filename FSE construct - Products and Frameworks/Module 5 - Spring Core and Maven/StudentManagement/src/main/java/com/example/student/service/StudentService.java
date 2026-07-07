package com.example.student.service;

import com.example.student.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private StudentRepository repository;

    @Autowired
    public StudentService()
    {
        System.out.println("Service Created");
    }
    //Setter Injection
    @Autowired
    public void setRepository(StudentRepository repository)
    {
        this.repository = repository;
    }
    public String registerStudent()
    {
        System.out.println("Registering Student");
        repository.saveStudent();
        return "Student Registered Successfully";
    }
}
