package com.springeboot.example.ems.backend.StudentServiceTest;


import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import com.springeboot.example.ems.backend.service.StudentModificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudService_UpdationTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentModificationService studentModificationService;

    @Test
    void testUpdateStudentSuccess() {
        // Arrange
        Long studentId = 1L;
        Student existingStudent = new Student(studentId, "John", "Doe", "john.doe@example.com");
        StudentDto updatedStudentDto = new StudentDto(studentId, "Jane", "Smith", "jane.smith@example.com");
        Student updatedStudent = new Student(studentId, "Jane", "Smith", "jane.smith@example.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(existingStudent));
        when(studentRepository.save(existingStudent)).thenReturn(updatedStudent);

        // Act
        StudentDto result = studentModificationService.updateStudent(studentId, updatedStudentDto);

        // Assert
        assertNotNull(result);
        assertEquals(updatedStudentDto.getId(), result.getId());
        assertEquals(updatedStudentDto.getFirstName(), result.getFirstName());
        assertEquals(updatedStudentDto.getLastName(), result.getLastName());
        assertEquals(updatedStudentDto.getEmail(), result.getEmail());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, times(1)).save(existingStudent);
    }

    @Test
    void testUpdateStudentNotFound() {
        // Arrange
        Long studentId = 1L;
        StudentDto updatedStudentDto = new StudentDto(studentId, "Jane", "Smith", "jane.smith@example.com");

        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act & Assert
        ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
            studentModificationService.updateStudent(studentId, updatedStudentDto);
        });

        assertEquals("Student does not exists " + studentId, exception.getMessage());
        verify(studentRepository, times(1)).findById(studentId);
        verify(studentRepository, never()).save(any(Student.class));
    }
}
