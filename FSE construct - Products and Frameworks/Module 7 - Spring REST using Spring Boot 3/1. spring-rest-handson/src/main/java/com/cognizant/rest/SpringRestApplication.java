package com.cognizant.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringRestApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRestApplication.class, args);
        
        System.out.println("--- Loading ApplicationContext from country.xml ---");
        ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
        
        Country country = context.getBean("country", Country.class);
        
        System.out.println("Loaded Country Bean: " + country);
    }
}
