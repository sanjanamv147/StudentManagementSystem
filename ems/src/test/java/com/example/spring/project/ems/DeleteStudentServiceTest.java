package com.example.spring.project.ems;


import com.example.spring.project.ems.Repositories.StudentRepository;
import com.example.spring.project.ems.Service.StudentDeleteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

class DeleteStudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentDeleteService deleteStudentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testDeleteStudent() {
        // Arrange
        Long studentId = 1L;

        // Mock the behavior of the repository
        doNothing().when(studentRepository).deleteById(studentId);

        // Act
        deleteStudentService.deleteStudent(studentId);

        // Assert
        verify(studentRepository).deleteById(studentId);
    }
}
