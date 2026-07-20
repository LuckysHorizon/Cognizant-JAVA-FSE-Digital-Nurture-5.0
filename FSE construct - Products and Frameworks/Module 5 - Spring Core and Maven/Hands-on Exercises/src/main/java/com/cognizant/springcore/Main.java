package com.cognizant.springcore;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        NotificationManager manager = context.getBean("notificationManager", NotificationManager.class);
        
        manager.notifyUser("user123@example.com", "Your Spring Core application has been successfully configured!");
    }
}
