package com.example.studentmanagementmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StudentController {
    @GetMapping("/")
    public String home(Model model)
    {
        model.addAttribute("name","Manikanta");
        model.addAttribute("course","Spring MVC");
        return "home";
    }
}
