package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Mapper.StudentMapper;
import com.example.spring.project.ems.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Optional;

@Service
public class StudentGetByIdService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private StudentMapper studentMapper;


    public Optional<StudentDTO> getStudentById(Long id) {
        return studentRepository.findById(id).map(studentMapper::toDTO);
    }
}