package com.springeboot.example.ems.backend.StudentServiceTest;



import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import com.springeboot.example.ems.backend.service.StudentCreationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudService_CreationTest {

    @InjectMocks
    private StudentCreationService studentCreationService;

    @Mock
    private StudentRepository studentRepository;

    @Test
    void testCreateStudentSuccess() throws BadRequestException {
        // Arrange
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "john.doe@example.com");
        Student student = Student.mapToStudent(studentDto);  // Student to be passed in
        Student savedStudent = new Student(1L, "John", "Doe", "john.doe@example.com");

        when(studentRepository.save(any(Student.class))).thenReturn(savedStudent);

        // Act
        StudentDto result = studentCreationService.createStudent(studentDto);

        // Assert
        verify(studentRepository, times(1)).save(any(Student.class));  // Verifying with any(Student.class)
        assertNotNull(result);
        assertEquals(studentDto.getId(), result.getId());
        assertEquals(studentDto.getFirstName(), result.getFirstName());
        assertEquals(studentDto.getLastName(), result.getLastName());
        assertEquals(studentDto.getEmail(), result.getEmail());
    }

    @Test
    void testCreateStudentInvalidEmail() {
        // Arrange
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "invalid-email");

        // Act & Assert
        BadRequestException exception = assertThrows(BadRequestException.class, () -> {
            studentCreationService.createStudent(studentDto);
        });

        assertEquals("Invalid email", exception.getMessage());
        verify(studentRepository, never()).save(any(Student.class));
    }

    @Test
    void testCreateStudentInternalServerError() throws BadRequestException {
        // Arrange
        StudentDto studentDto = new StudentDto(1L, "John", "Doe", "john.doe@example.com");
        Student student = Student.mapToStudent(studentDto);

        when(studentRepository.save(any(Student.class))).thenThrow(new RuntimeException("Database error"));

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            studentCreationService.createStudent(studentDto);
        });

        assertEquals("Database error", exception.getMessage());
        verify(studentRepository, times(1)).save(any(Student.class));
    }
}

