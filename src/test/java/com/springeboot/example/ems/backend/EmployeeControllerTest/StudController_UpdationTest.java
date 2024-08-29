package com.springeboot.example.ems.backend.StudentControllerTest;


import com.springeboot.example.ems.backend.controller.StudUpdationController;
import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentModificationService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudController_UpdationTest {

    @InjectMocks
    private StudUpdationController studUpdationController;

    @Mock
    private StudentModificationService studentModificationService;

    @Test
    void testUpdateStudentSuccess() {
        Long studentId = 1L;
        StudentDto updatedStudent = new StudentDto(1L, "John", "Doe", "john.doe@example.com");
        StudentDto returnedStudent = new StudentDto(1L, "John", "Doe", "john.doe@example.com");

        when(studentModificationService.updateStudent(eq(studentId), any(StudentDto.class))).thenReturn(returnedStudent);

        ResponseEntity<?> response = studUpdationController.updateStudent(studentId, updatedStudent);

        verify(studentModificationService, times(1)).updateStudent(eq(studentId), any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(returnedStudent, response.getBody());
    }

    @Test
    void testUpdateStudentNotFound() {
        Long studentId = 1L;
        StudentDto updatedStudent = new StudentDto(1L, "John", "Doe", "john.doe@example.com");

        when(studentModificationService.updateStudent(eq(studentId), any(StudentDto.class))).thenReturn(null);

        ResponseEntity<?> response = studUpdationController.updateStudent(studentId, updatedStudent);

        verify(studentModificationService, times(1)).updateStudent(eq(studentId), any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Student not found", response.getBody());
    }

    @Test
    void testUpdateStudentBadRequest() {
        Long studentId = 1L;
        StudentDto updatedStudent = new StudentDto(1L, "John", "Doe", "john.doe@example.com");

        doThrow(new IllegalArgumentException("Invalid Student data")).when(studentModificationService).updateStudent(eq(studentId), any(StudentDto.class));

        ResponseEntity<?> response = studUpdationController.updateStudent(studentId, updatedStudent);

        verify(studentModificationService, times(1)).updateStudent(eq(studentId), any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid Student data", response.getBody());
    }

    @Test
    void testUpdateStudentInternalServerError() {
        Long studentId = 1L;
        StudentDto updatedStudent = new StudentDto(1L, "John", "Doe", "john.doe@example.com");

        doThrow(new RuntimeException("An unexpected error occurred")).when(studentModificationService).updateStudent(eq(studentId), any(StudentDto.class));

        ResponseEntity<?> response = studUpdationController.updateStudent(studentId, updatedStudent);

        verify(studentModificationService, times(1)).updateStudent(eq(studentId), any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while updating the student", response.getBody());
    }
}
