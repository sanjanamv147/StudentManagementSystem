package com.springeboot.example.ems.backend.StudentServiceTest;


import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import com.springeboot.example.ems.backend.service.StudentRetrievalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudService_RetrievalTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentRetrievalService studentRetrievalService;

    @Test
    void testGetStudentByIdSuccess() {
        // Arrange
        Long studentId = 1L;
        Student student = new Student(studentId, "John", "Doe", "john.doe@example.com");
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        StudentDto result = studentRetrievalService.getStudentById(studentId);

        // Assert
        assertNotNull(result);
        assertEquals(studentId, result.getId());
        assertEquals(student.getFirstName(), result.getFirstName());
        assertEquals(student.getLastName(), result.getLastName());
        assertEquals(student.getEmail(), result.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
    }

    @Test
    void testGetStudentByIdNotFound() {
        // Arrange
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentRetrievalService.getStudentById(studentId);
        });

        assertEquals("Student does not exist with the given id " + studentId, exception.getMessage());
        verify(studentRepository, times(1)).findById(studentId);
    }
}
