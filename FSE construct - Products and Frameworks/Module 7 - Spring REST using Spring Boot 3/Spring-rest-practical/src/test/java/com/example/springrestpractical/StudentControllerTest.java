package com.example.springrestpractical;

import com.example.springrestpractical.controller.StudentController;
import com.example.springrestpractical.dto.StudentResponseDTO;
import com.example.springrestpractical.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    StudentService studentService;

    @Test
    void testGetStudentById() throws Exception {

        StudentResponseDTO dto =
                new StudentResponseDTO();

        dto.setId(1L);
        dto.setFirstName("Manikanta");

        Mockito.when(studentService.getStudentById(1L))
                .thenReturn(dto);

        mockMvc.perform(get("/students/1"))

                .andExpect(status().isOk())

                .andExpect(jsonPath("$.id").value(1))

                .andExpect(jsonPath("$.firstName")
                        .value("Manikanta"));
    }
}