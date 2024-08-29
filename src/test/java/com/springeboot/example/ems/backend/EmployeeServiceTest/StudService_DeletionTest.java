package com.springeboot.example.ems.backend.StudentServiceTest;



import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import com.springeboot.example.ems.backend.service.StudentDeletionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class StudService_DeletionTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentDeletionService studentDeletionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deleteStudentSuccess() {
        // Arrange
        Long studentId = 1L;
        Student student = new Student(); // Assuming Student class has a no-arg constructor
        when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

        // Act
        studentDeletionService.deleteStudent(studentId);

        // Assert
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).deleteById(studentId);
    }

    @Test
    void deleteStudentNotFound() {
        // Arrange
        Long studentId = 1L;
        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> {
            studentDeletionService.deleteStudent(studentId);
        });

        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, never()).deleteById(studentId);
    }
}
