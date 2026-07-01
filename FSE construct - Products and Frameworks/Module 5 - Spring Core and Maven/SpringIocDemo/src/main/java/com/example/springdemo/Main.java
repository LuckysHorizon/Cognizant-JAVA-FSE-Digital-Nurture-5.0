package com.example.springdemo;

public class Main {
    public static void main(String [] args)
    {
        Engine engine = new Engine();
        Car car = new Car(engine);
        car.drive();
    }
}
