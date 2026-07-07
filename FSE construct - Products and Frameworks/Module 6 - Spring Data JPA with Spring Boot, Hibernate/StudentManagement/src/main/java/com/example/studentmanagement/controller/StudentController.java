package com.example.studentmanagement.controller;

import com.example.studentmanagement.entity.Student;
import com.example.studentmanagement.projection.StudentProjection;
import com.example.studentmanagement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService service;

    //Create
    @PostMapping
    public Student addStudent(@RequestBody Student student)
    {
        return service.saveStudent(student);
    }

    //Read All
    @GetMapping
    public List<Student> getAllStudents()
    {
        return service.getAllStudents();
    }

    //Read By Id
    @GetMapping("/{id}")
    public Student getStudent(@PathVariable Integer id)
    {
        return service.getStudentById(id);
    }

    //Update
    @PutMapping("{id}")
    public Student updateStudent(@PathVariable Integer id, @RequestBody Student student)
    {
        return service.updateStudent(id,student);
    }

    // Delete
    @DeleteMapping("/{id}")
    public String deleteStudent(@PathVariable Integer id) {
        service.deleteStudent(id);
        return "Student Deleted Successfully";
    }

    // Custom Query
    @GetMapping("/name/{name}")
    public List<Student> getStudentByName(@PathVariable String name) {
        return service.getStudentByName(name);
    }

    @GetMapping("/page")

    public Page<Student> getStudents(

            @RequestParam int page,

            @RequestParam int size){

        return service.getStudents(page,size);

    }
    @GetMapping("/sort/name")

    public Page<Student> sortByName(

            @RequestParam int page,

            @RequestParam int size){

        return service.getStudentsSortedByName(page,size);

    }
    @GetMapping("/sort/age")

    public Page<Student> sortByAge(

            @RequestParam int page,

            @RequestParam int size){

        return service.getStudentsSortedByAgeDesc(page,size);

    }

    @GetMapping("/sort/age-name")
    public Page<Student> sortByAgeAndName(
            @RequestParam int page,
            @RequestParam int size) {

        return service.getStudentsSortedByAgeDescAndName(page, size);
    }

    @GetMapping("/projection/{age}")
    public List<StudentProjection> getStudentsByAge(
            @PathVariable Integer age){

        return service.getStudentsByAge(age);

    }
}
