package com.springeboot.example.ems.backend.service;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Data
public  class StudentRetrievalService {

    private StudentRepository studentRepository;

    public StudentDto getStudentById(Long studentId) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Student does not exist with the given id " + studentId));

        return StudentDto.mapToStudentDto(student);
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentDto::mapToStudentDto)
                .toList();
    }

}