package com.springeboot.example.ems.backend.service;

import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
@AllArgsConstructor
@Service
public  class StudentDeletionService  {

    private StudentRepository studentRepository;

    public void deleteStudent(Long studentId) {

        Student student= studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student does not exists "+studentId)
        );

        studentRepository.deleteById(studentId);

    }
}
