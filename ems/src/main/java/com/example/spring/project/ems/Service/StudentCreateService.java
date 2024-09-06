package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCreateService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    @Transactional
    public StudentDTO createStudent(StudentDTO studentDTO, String role) {
        if (!role.startsWith("ROLE_ADMIN")) {
            throw new UnauthorizedAccessException("Only ADMIN users can create students.");
        }
        Student student = studentMapper.toEntity(studentDTO);
        Student createdStudent = studentRepository.save(student);
        return studentMapper.toDTO(createdStudent);
    }
}
