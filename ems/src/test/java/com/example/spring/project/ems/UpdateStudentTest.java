package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.StudentUpdateController;
import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Service.StudentUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.is;
import com.fasterxml.jackson.databind.ObjectMapper;

class UpdateStudentTest {

    private MockMvc mockMvc;

    @Mock
    private StudentUpdateService studentService;

    @InjectMocks
    private StudentUpdateController updateStudent;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(updateStudent).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testUpdateStudent() throws Exception {
        // Arrange
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentId);
        studentDTO.setName("John Doe");
        studentDTO.setDepartment("HR");


        when(studentService.updateStudent(eq(studentId), any(StudentDTO.class))).thenReturn(studentDTO);

        // Act & Assert
        mockMvc.perform(put("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.department", is("HR")));

    }

    @Test
    void testUpdateStudent_NotFound() throws Exception {
        // Arrange
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("John Doe");
        studentDTO.setDepartment("HR");


        when(studentService.updateStudent(eq(studentId), any(StudentDTO.class))).thenReturn(null);

        // Act & Assert
        mockMvc.perform(put("/api/students/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isNotFound());
    }
}
