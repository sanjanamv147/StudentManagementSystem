package com.example.spring.project.ems;



import com.example.spring.project.ems.Controller.StudentDeleteController;
import com.example.spring.project.ems.Service.StudentDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class DeleteStudentTest {

    private MockMvc mockMvc;

    @Mock
    private StudentDeleteService studentService;

    @InjectMocks
    private StudentDeleteController deleteStudent;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(deleteStudent).build();
    }

    @Test
    void testDeleteStudent() throws Exception {
        // Arrange
        Long studentId = 1L;
        doNothing().when(studentService).deleteStudent(studentId);

        // Act & Assert
        mockMvc.perform(delete("/api/student/{id}", studentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        // Verify that the deleteStudent method in the service was called
        verify(studentService).deleteStudent(studentId);
    }
}
