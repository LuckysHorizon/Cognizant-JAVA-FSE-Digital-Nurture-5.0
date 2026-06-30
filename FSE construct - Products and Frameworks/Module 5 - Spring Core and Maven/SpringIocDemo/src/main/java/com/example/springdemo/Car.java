package com.example.springdemo;

public class Car {
    private Engine engine;
    public Car()
    {
        engine = new Engine();
        System.out.println("Car object Created");
    }
    public void drive()
    {
        engine.start();
        System.out.println("Car is Running");
    }
}
