package com.springeboot.example.ems.backend.controller;

import com.springeboot.example.ems.backend.service.StudentDeletionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudController_DeletionTest {

    @InjectMocks
    private StudDeletionController studDeletionController;

    @Mock
    private StudentDeletionService studentDeletionService;

    @Test
    void testDeleteStudentSuccess() {
        Long studentId = 1L;

        // Mock the deletion to do nothing (successful deletion)
        doNothing().when(studentDeletionService).deleteStudent(studentId);

        // Call the method and verify the response
        ResponseEntity<?> response = studDeletionController.deleteStudent(studentId);

        verify(studentDeletionService, times(1)).deleteStudent(studentId);
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Student deleted successfully", response.getBody());
    }

    @Test
    void testDeleteStudentNotFound() {
        Long studentId = 1L;

        // Mock the deletion to throw a NoSuchElementException (student not found)
        doThrow(new NoSuchElementException("Student not found")).when(studentDeletionService).deleteStudent(studentId);

        // Call the method and verify the response
        ResponseEntity<?> response = studDeletionController.deleteStudent(studentId);

        verify(studentDeletionService, times(1)).deleteStudent(studentId);
        assertNotNull(response);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Student not found", response.getBody());
    }

    @Test
    void testDeleteStudentInternalServerError() {
        Long studentId = 1L;

        // Mock the deletion to throw a generic exception (internal server error)
        doThrow(new RuntimeException("An unexpected error occurred")).when(studentDeletionService).deleteStudent(studentId);

        // Call the method and verify the response
        ResponseEntity<?> response = studDeletionController.deleteStudent(studentId);

        verify(studentDeletionService, times(1)).deleteStudent(studentId);
        assertNotNull(response);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An error occurred while deleting the student", response.getBody());
    }
}
