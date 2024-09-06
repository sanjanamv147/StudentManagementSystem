package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import com.example.spring.project.ems.Service.StudentUpdateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentUpdateServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentUpdateService studentUpdateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testUpdateStudent_Success() {
        // Arrange
        Long studentId = 1L;
        Student studentEntity = new Student();
        studentEntity.setId(studentId);
        studentEntity.setName("Old Name");
        studentEntity.setDepartment("Old Department");


        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentId);
        studentDTO.setName("New Name");
        studentDTO.setDepartment("New Department");


        when(studentRepository.findById(studentId)).thenReturn(Optional.of(studentEntity));
        when(studentRepository.save(studentEntity)).thenReturn(studentEntity);
        when(studentMapper.toDTO(studentEntity)).thenReturn(studentDTO);

        // Act
        StudentDTO updatedStudent = studentUpdateService.updateStudent(studentId, studentDTO);

        // Assert
        assertEquals("New Name", updatedStudent.getName());
        assertEquals("New Department", updatedStudent.getDepartment());


        verify(studentRepository).findById(studentId);
        verify(studentRepository).save(studentEntity);
        verify(studentMapper).toDTO(studentEntity);
    }

    @Test
    void testUpdateStudent_NotFound() {
        // Arrange
        Long studentId = 1L;
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("New Name");
        studentDTO.setDepartment("New Department");


        when(studentRepository.findById(studentId)).thenReturn(Optional.empty());

        // Act & Assert
        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                studentUpdateService.updateStudent(studentId, studentDTO));

        assertEquals("Student not found", exception.getMessage());

        verify(studentRepository).findById(studentId);
    }
}
