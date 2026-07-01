package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Car {

    private final Engine engine;
    private final MusicSystem musicSystem;

    @Autowired
    public Car(Engine engine, MusicSystem musicSystem)
    {
        this.engine = engine;
        this.musicSystem = musicSystem;
        System.out.println("Car Bean Created");
    }
    public void drive() {

        engine.start();
        musicSystem.playMusic();

        System.out.println("Car Running");
    }
}