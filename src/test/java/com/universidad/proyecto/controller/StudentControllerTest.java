package com.universidad.proyecto.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.universidad.proyecto.dto.StudentDTO;
import com.universidad.proyecto.model.Student;
import com.universidad.proyecto.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private StudentService service;

    @Test
    void shouldListStudents() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("Ana");
        student.setLastName("Gomez");
        student.setEmail("ana@example.com");
        student.setDateOfBirth(LocalDate.parse("2003-05-12"));
        student.setProgram("Ingeniería");
        student.setDocumentNumber("12345");

        when(service.findAll()).thenReturn(List.of(student));

        mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void shouldCreateStudent() throws Exception {
        StudentDTO dto = new StudentDTO();
        dto.setFirstName("Ana");
        dto.setLastName("Gomez");
        dto.setEmail("ana@example.com");
        dto.setDateOfBirth("2003-05-12");
        dto.setProgram("Ingeniería");
        dto.setDocumentNumber("12345");

        Student saved = new Student();
        saved.setId(1L);
        saved.setFirstName("Ana");
        saved.setLastName("Gomez");
        saved.setEmail("ana@example.com");

        when(service.create(any(StudentDTO.class))).thenReturn(saved);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated());
    }
}

