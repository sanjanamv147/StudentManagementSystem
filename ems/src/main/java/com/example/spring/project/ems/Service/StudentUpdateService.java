package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentUpdateService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;

    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        student.setName(studentDTO.getName());
        student.setEmail(studentDTO.getEmail());
        student.setDepartment(studentDTO.getDepartment());

        return studentMapper.toDTO(studentRepository.save(student));
    }
}
