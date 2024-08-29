package com.springeboot.example.ems.backend.StudentControllerTest;



import com.springeboot.example.ems.backend.controller.StudCreationController;
import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentCreationService;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudController_CreationTest {

    @InjectMocks
    private StudCreationController studentController;

    @Mock
    private StudentCreationService studentCreationService;

    @Test
    void testCreateStudentSuccess() throws BadRequestException {
        StudentDto validRequestDto = new StudentDto(1L, "John", "Doe", "john.doe@example.com");

        when(studentCreationService.createStudent(any(StudentDto.class))).thenReturn(validRequestDto);

        ResponseEntity<?> response = studentController.createStudent(validRequestDto);

        verify(studentCreationService, times(1)).createStudent(any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(validRequestDto, response.getBody());
    }

    @Test
    void testCreateStudentBadRequest() throws BadRequestException {
        StudentDto invalidRequestDto = new StudentDto(1L, "John", "Doe", "invalid-email");
        when(studentCreationService.createStudent(any(StudentDto.class))).thenThrow(new BadRequestException("Invalid data"));

        ResponseEntity<?> response = studentController.createStudent(invalidRequestDto);

        verify(studentCreationService, times(1)).createStudent(any(StudentDto.class));
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid data", response.getBody());
    }
}









