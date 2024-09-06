package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import com.example.spring.project.ems.Service.StudentGetAllService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class StudentGetAllServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentGetAllService studentGetAllService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllStudents() {
        // Arrange
        Student studentEntity1 = new Student();
        studentEntity1.setId(1L);
        studentEntity1.setName("John Doe");

        Student studentEntity2 = new Student();
        studentEntity2.setId(2L);
        studentEntity2.setName("Jane Doe");

        StudentDTO studentDTO1 = new StudentDTO();
        studentDTO1.setId(1L);
        studentDTO1.setName("John Doe");

        StudentDTO studentDTO2 = new StudentDTO();
        studentDTO2.setId(2L);
        studentDTO2.setName("Jane Doe");

        when(studentRepository.findAll()).thenReturn(List.of(studentEntity1, studentEntity2));
        when(studentMapper.toDTO(studentEntity1)).thenReturn(studentDTO1);
        when(studentMapper.toDTO(studentEntity2)).thenReturn(studentDTO2);

        // Act
        List<StudentDTO> result = studentGetAllService.getAllStudents();

        // Assert
        assertEquals(2, result.size());
        assertEquals("John Doe", result.get(0).getName());
        assertEquals("Jane Doe", result.get(1).getName());

        verify(studentRepository).findAll();
        verify(studentMapper).toDTO(studentEntity1);
        verify(studentMapper).toDTO(studentEntity2);
    }

    @Test
    void testGetStudentById() {
        // Arrange
        Long studentId = 1L;
        Student studentEntity = new Student();
        studentEntity.setId(studentId);
        studentEntity.setName("John Doe");

        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(studentId);
        studentDTO.setName("John Doe");

        when(studentRepository.findById(studentId)).thenReturn(Optional.of(studentEntity));
        when(studentMapper.toDTO(studentEntity)).thenReturn(studentDTO);

        // Act
        Optional<StudentDTO> result = studentGetAllService.getStudentById(studentId);

        // Assert
        assertTrue(result.isPresent());
    }
}

