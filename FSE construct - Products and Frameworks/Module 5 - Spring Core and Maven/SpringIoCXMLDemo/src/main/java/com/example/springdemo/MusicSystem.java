package com.example.springdemo;

import org.springframework.stereotype.Component;

@Component
public class MusicSystem {

    public MusicSystem()
    {
        System.out.println("MusicSystem bean created");
    }
    public void playMusic()
    {
        System.out.println("Playing Music ... ");
    }
}
