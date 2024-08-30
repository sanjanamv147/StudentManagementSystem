package com.springeboot.example.ems.backend;

import com.springeboot.example.ems.backend.service.StudentCreationService;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import org.apache.coyote.BadRequestException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StudentServiceImplTest {

    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentCreationService studentCreationService;

    private Student student;
    private Student student1;
    private StudentDto studentDto;

    @BeforeEach
    void setUp() {

        student1 = new Student(1L, "xxxx", "Davis", "dsdgaex@gmail.com");
        studentDto = new StudentDto(1L, "xxxx", "Davis", "dsdgaex@gmail.com");

    }


    @Test
    void testCreateStudentSuccess()  {
      // Student student = new Student(1L, "yyyy", "Davis", "dsdgaex@gmail.com");
        when(employeeRepository.save(Mockito.any(Student.class))).thenReturn(student);

        StudentDto savedStudentDto = null;
        try {
            savedStudentDto = employeeCreationService.createStudent(employeeDto);
        } catch (BadRequestException e) {

        }

       // verify(employeeRepository, times(1)).save(any(Student.class));
        assertNotNull(savedStudentDto);
       assertEquals(employeeDto.getFirstName(), savedStudentDto.getFirstName());

    }
    
}

