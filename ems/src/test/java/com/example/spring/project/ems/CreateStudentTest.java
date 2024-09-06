package com.example.spring.project.ems;

import com.example.spring.project.ems.Controller.StudentCreateController;
import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Service.StudentCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.closeTo;
import com.fasterxml.jackson.databind.ObjectMapper;

class CreateStudentTest {

    private MockMvc mockMvc;

    @Mock
    private StudentCreateService studentService;

    @InjectMocks
    private StudentCreateController createStudent;

    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(createStudent).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    void testCreateStudent() throws Exception {
        // Arrange
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(1L);
        studentDTO.setName("John Doe");
        studentDTO.setEmail("john@gmail.com");
        studentDTO.setDepartment("JAVA");


        when(studentService.createStudent(any(StudentDTO.class))).thenReturn(studentDTO);

        // Act & Assert
        mockMvc.perform(post("/api/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(notNullValue())))
                .andExpect(jsonPath("$.name", is("John Doe")))
                .andExpect(jsonPath("$.email", is("john@gmail.com")))
                .andExpect(jsonPath("$.department", is("JAVA")));
    }
}
