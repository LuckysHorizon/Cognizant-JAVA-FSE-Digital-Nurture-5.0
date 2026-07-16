package com.example.springrestpractical.controller;

import com.example.springrestpractical.model.Student;
import org.springframework.context.annotation.Scope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@RestController
@RequestMapping("/")
public class StudentController {

    @GetMapping("/hello")
    public String hello()
    {
        return "Hello Spring REST";
    }

    @GetMapping("/getStudents")
    public Student getStudents()
    {
        return new Student(1,"Manikanta",20);
    }
    //Get Using PathVariable
    @GetMapping("/{id}")
    public String getStudent(@PathVariable int id)
    {
        return "Student ID: " + id;
    }

    @PostMapping("/createStudent/{name}")
    public String createStudent(@PathVariable String name)
    {
        long timeStamp = System.currentTimeMillis();
        int randomNum = ThreadLocalRandom.current().nextInt(1000,9999);
        String uniqueId = timeStamp + "-"+randomNum;
        String res = "Student Created Successfully " + name + " id: "+uniqueId;
        return res;
    }
    @PutMapping("/updateStudent/{id}")
    public String updateStudent(@PathVariable int id)
    {
        System.out.println("Updated Student " + id);
        return "Updated Student " + id;
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(@PathVariable int id)
    {
        return "Student deleted : " + id;
    }

//    @GetMapping("/students")
//    public List<Student> allStudents()
//    {
//        return List.of(
//          new Student(1,"Prabhas Raju Uppalapati",47),
//          new Student(2,"Manikanta Boda",20),
//          new Student(3,"Mahesh Babu",47)
//        );
//    }

    //Requesting Student City
    @GetMapping("/studentsCity")
    public String getStudentCity(
            @RequestParam String city)
    {
        return "City is: " + city;
    }

    @GetMapping("/students/search")
    public String searchStudents(
            @RequestParam String city,
            @RequestParam int age)
    {
        return city + " " + age;
    }

    @GetMapping("/students")
    public String getStudents(@RequestParam(required = false) String city)
    {
        return city;
    }

    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student)
    {
        return student;
    }

    @PostMapping("/saveStudent")
    public ResponseEntity<String> saveStudent()
    {
        return ResponseEntity.status(201).body("Student Created Succesfully");
    }

    @GetMapping("/welcome")
    public ResponseEntity<String> welcome()
    {
        return ResponseEntity
                .ok()
                .header("Company","Google Academy")
                .header("Veersion","3.9")
                .body("Welcome");
    }

    @GetMapping("/students/{id}")
    public String getStudents(

            @PathVariable int id){

        if(id!=1){

            throw new RuntimeException(
                    "Student Not Found");

        }

        return "Student Found";

    }
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleException(RuntimeException ex)
    {
        return ResponseEntity
                .status(404)
                .body(ex.getMessage());
    }
}
