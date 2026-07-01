package com.example.student;

import com.example.student.config.AppConfig;
import com.example.student.controller.StudentController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String [] args)
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        StudentController controller = context.getBean(StudentController.class);
        controller.addStudent();
    }
}
