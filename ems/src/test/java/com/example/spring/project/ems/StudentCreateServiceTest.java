package com.example.spring.project.ems;



import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import com.example.spring.project.ems.Service.StudentCreateService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

class StudentCreateServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentCreateService studentCreateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateStudent() {
        // Arrange
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setName("John Doe");
        studentDTO.setDepartment("HR");


        Student studentEntity = new Student();
        studentEntity.setName("John Doe");
        studentEntity.setDepartment("HR");


        Student savedStudent = new Student();
        savedStudent.setId(1L);
        savedStudent.setName("John Doe");
        savedStudent.setDepartment("HR");


        StudentDTO savedStudentDTO = new StudentDTO();
        savedStudentDTO.setId(1L);
        savedStudentDTO.setName("John Doe");
        savedStudentDTO.setDepartment("HR");

        when(studentMapper.toEntity(studentDTO)).thenReturn(studentEntity);
        when(studentRepository.save(studentEntity)).thenReturn(savedStudent);
        when(studentMapper.toDTO(savedStudent)).thenReturn(savedStudentDTO);

        // Act
        StudentDTO result = studentCreateService.createStudent(studentDTO);

        // Assert
        assertEquals(savedStudentDTO.getId(), result.getId());
        assertEquals(savedStudentDTO.getName(), result.getName());
        assertEquals(savedStudentDTO.getDepartment(), result.getDepartment());


        verify(studentMapper).toEntity(studentDTO);
        verify(studentRepository).save(studentEntity);
        verify(studentMapper).toDTO(savedStudent);
    }
}

