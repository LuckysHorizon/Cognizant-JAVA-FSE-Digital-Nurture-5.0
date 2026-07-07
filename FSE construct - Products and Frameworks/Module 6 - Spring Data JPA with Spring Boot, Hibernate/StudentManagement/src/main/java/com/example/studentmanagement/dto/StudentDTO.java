package com.example.studentmanagement.dto;

public class StudentDTO {

    private String name;
    private String email;

    public StudentDTO(String name,String email){
        this.name=name;
        this.email=email;
    }

    public String getName(){
        return name;
    }

    public String getEmail(){
        return email;
    }
}