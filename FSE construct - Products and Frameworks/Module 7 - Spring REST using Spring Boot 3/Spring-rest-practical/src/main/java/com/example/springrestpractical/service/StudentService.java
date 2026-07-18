package com.example.springrestpractical.service;

import com.example.springrestpractical.dto.StudentRequestDTO;
import com.example.springrestpractical.dto.StudentResponseDTO;
import com.example.springrestpractical.entity.Student;
import org.springframework.stereotype.Service;

import java.util.List;

public interface StudentService {
    StudentResponseDTO saveStudent(StudentRequestDTO requestDTO);
    List<StudentResponseDTO> getAllStudents();
    StudentResponseDTO getStudentById(Long id);
    StudentResponseDTO updateStudent(Long id, StudentRequestDTO studentRequestDTO);
    void deleteStudent(Long id);

}
