package com.example.student.controller;

import com.example.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class StudentController {
    private final StudentService service;
    @Autowired
    public StudentController(StudentService service)
    {
        this.service = service;
        System.out.println("StudentController Bean Created");
    }
    public void addStudent()
    {
        System.out.println("Request Recieved");
        service.registerStudent();
    }
}
