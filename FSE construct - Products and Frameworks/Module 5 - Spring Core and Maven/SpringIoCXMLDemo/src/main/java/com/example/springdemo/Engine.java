package com.example.springdemo;

import org.springframework.stereotype.Component;

@Component
public class Engine {

    public Engine() {
        System.out.println("Engine Bean Created");
    }

    public void start() {
        System.out.println("Engine Started");
    }

}