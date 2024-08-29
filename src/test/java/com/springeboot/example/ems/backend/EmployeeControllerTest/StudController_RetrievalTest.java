package com.springeboot.example.ems.backend.StudentControllerTest;

import com.springeboot.example.ems.backend.controller.StudRetrievalController;
import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentRetrievalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
public class StudController_RetrievalTest {

    @InjectMocks
    private StudRetrievalController studentController;

    @Mock
    private StudentRetrievalService studentServiceMock;

    @Test
    void testGetStudentById(){
        Long StudId=1L;
        StudentDto studentDto = new StudentDto(StudId,"John", "Doe", "john.doe@example.com");

        //when
        when(studentServiceMock.getStudentById(StudId)).thenReturn(studentDto);

        ResponseEntity<?> response=studentController.getStudentById(StudId);

        assertEquals(HttpStatus.OK,response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(studentDto,response.getBody());

        verify(studentServiceMock,times(1)).getStudentById(StudId);
    }
}
