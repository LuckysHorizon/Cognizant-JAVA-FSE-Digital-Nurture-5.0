package com.example.springrestpractical.service.impl;

import com.example.springrestpractical.dto.StudentRequestDTO;
import com.example.springrestpractical.dto.StudentResponseDTO;
import com.example.springrestpractical.entity.Student;
import com.example.springrestpractical.exception.StudentNotFoundException;
import com.example.springrestpractical.repository.StudentRepository;
import com.example.springrestpractical.service.StudentService;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;


    public StudentServiceImpl(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }
    @Override
    public StudentResponseDTO saveStudent(StudentRequestDTO studentRequestDTO)
    {
        Student student = new Student();
        student.setFirstName(studentRequestDTO.getFirstName());
        student.setLastName(studentRequestDTO.getLastName());
        student.setEmail(studentRequestDTO.getEmail());
        student.setAge(studentRequestDTO.getAge());
        student.setDepartment(studentRequestDTO.getDepartment());

        Student savedStudent = studentRepository.save(student);

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(savedStudent.getId());

        response.setFirstName(savedStudent.getFirstName());

        response.setLastName(savedStudent.getLastName());

        response.setEmail(savedStudent.getEmail());

        response.setAge(savedStudent.getAge());

        response.setDepartment(savedStudent.getDepartment());

        return response;
    }
    @Override
    public List<StudentResponseDTO> getAllStudents() {

        List<Student> students = studentRepository.findAll();

        return students.stream().map(student -> {

            StudentResponseDTO dto = new StudentResponseDTO();

            dto.setId(student.getId());
            dto.setFirstName(student.getFirstName());
            dto.setLastName(student.getLastName());
            dto.setEmail(student.getEmail());
            dto.setAge(student.getAge());
            dto.setDepartment(student.getDepartment());

            return dto;

        }).toList();
    }
    @Override
    public StudentResponseDTO getStudentById(Long id) {

        Student student = studentRepository
                .findById(id)
                .orElseThrow(() ->
                        new StudentNotFoundException(
                                "Student Not Found"
                        ));

        if (student == null) {
            return null;
        }

        StudentResponseDTO dto = new StudentResponseDTO();

        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setAge(student.getAge());
        dto.setDepartment(student.getDepartment());

        return dto;
    }

    @Override
    public StudentResponseDTO updateStudent(Long id, StudentRequestDTO requestDTO) {

        Student existingStudent = studentRepository.findById(id).orElse(null);

        if (existingStudent == null) {
            return null;
        }

        existingStudent.setFirstName(requestDTO.getFirstName());
        existingStudent.setLastName(requestDTO.getLastName());
        existingStudent.setEmail(requestDTO.getEmail());
        existingStudent.setAge(requestDTO.getAge());
        existingStudent.setDepartment(requestDTO.getDepartment());

        Student updatedStudent = studentRepository.save(existingStudent);

        StudentResponseDTO response = new StudentResponseDTO();

        response.setId(updatedStudent.getId());
        response.setFirstName(updatedStudent.getFirstName());
        response.setLastName(updatedStudent.getLastName());
        response.setEmail(updatedStudent.getEmail());
        response.setAge(updatedStudent.getAge());
        response.setDepartment(updatedStudent.getDepartment());

        return response;
    }
    @Override
    public void deleteStudent(Long id)
    {
        studentRepository.deleteById(id);
    }
}
