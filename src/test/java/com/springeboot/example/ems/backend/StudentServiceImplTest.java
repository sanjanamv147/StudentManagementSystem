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
    private StudentRepository employeeRepository;

    @InjectMocks
    private StudentCreationService employeeCreationService;

    private Student employee;
    private Student employee1;
    private StudentDto employeeDto;

    @BeforeEach
    void setUp() {

        employee1 = new Student(1L, "xxxx", "Davis", "dsdgaex@gmail.com");
        employeeDto = new StudentDto(1L, "xxxx", "Davis", "dsdgaex@gmail.com");

    }


    @Test
    void testCreateStudentSuccess()  {
      // Student employee = new Student(1L, "yyyy", "Davis", "dsdgaex@gmail.com");
        when(employeeRepository.save(Mockito.any(Student.class))).thenReturn(employee1);

        StudentDto savedStudentDto = null;
        try {
            savedStudentDto = employeeCreationService.createStudent(employeeDto);
        } catch (BadRequestException e) {

        }

       // verify(employeeRepository, times(1)).save(any(Student.class));
        assertNotNull(savedStudentDto);
       assertEquals(employeeDto.getFirstName(), savedStudentDto.getFirstName());

    }
    @Test
    void testCreateStudentBadRequest()  {
      StudentDto  invalidRequestDto = new StudentDto(1L, "xxxx", "Davis", "dsdgaexgmail.com");
       when(employeeRepository.save(Mockito.any(Student.class))).thenReturn(employee1);

       // StudentDto savedStudentDto = null;
        try {
            employeeCreationService.createStudent(invalidRequestDto);
            Assertions.fail("Exception not thrown");
        } catch (BadRequestException e) {
            verify(employeeRepository, times(0)).save(any(Student.class));
           Assertions.assertEquals(e.getMessage(),"invalid email");
        }



    }
//    @Test
//    void testGetStudentById_Success() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
//
//        StudentDto foundStudent = employeeService.getStudentById(1L);
//
//        assertNotNull(foundStudent);
//        assertEquals(employee.getFirstName(), foundStudent.getFirstName());
//        verify(employeeRepository, times(1)).findById(anyLong());
//    }
//
//    @Test
//    void testGetStudentById_NotFound() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            employeeService.getStudentById(1L);
//        });
//    }
//
//    @Test
//    void testGetAllStudents() {
//        List<Student> employees = Arrays.asList(employee);
//        when(employeeRepository.findAll()).thenReturn(employees);
//
//        List<StudentDto> allStudents = employeeService.getAllStudents();
//
//        assertFalse(allStudents.isEmpty());
//        assertEquals(1, allStudents.size());
//        verify(employeeRepository, times(1)).findAll();
//    }
//
//    @Test
//    void testUpdateStudent_Success() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
//        when(employeeRepository.save(any(Student.class))).thenReturn(employee);
//
//        StudentDto updatedStudentDto = employeeService.updateStudent(1L, employeeDto);
//
//        assertNotNull(updatedStudentDto);
//        assertEquals(employeeDto.getFirstName(), updatedStudentDto.getFirstName());
//        verify(employeeRepository, times(1)).findById(anyLong());
//        verify(employeeRepository, times(1)).save(any(Student.class));
//    }
//
//    @Test
//    void testUpdateStudent_NotFound() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            employeeService.updateStudent(1L, employeeDto);
//        });
//    }
//
//    @Test
//    void testDeleteStudent_Success() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.of(employee));
//
//        employeeService.deleteStudent(1L);
//
//        verify(employeeRepository, times(1)).findById(anyLong());
//        verify(employeeRepository, times(1)).deleteById(anyLong());
//    }
//
//    @Test
//    void testDeleteStudent_NotFound() {
//        when(employeeRepository.findById(anyLong())).thenReturn(Optional.empty());
//
//        assertThrows(ResourceNotFoundException.class, () -> {
//            employeeService.deleteStudent(1L);
//        });
//    }
}

