package com.example.student.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Before("execution(* com.example.student.service.StudentService.registerStudent(..))")
    public void beforeMethod(JoinPoint joinPoint)
    {
        System.out.println("================ LOG START ====================");
        System.out.println("Calling Method : "+ joinPoint.getSignature().getName());
        System.out.println("===============================================");
    }

}
