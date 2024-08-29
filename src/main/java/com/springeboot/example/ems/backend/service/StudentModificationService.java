package com.springeboot.example.ems.backend.service;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.exception.ResourceNotFoundException;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data

public  class StudentModificationService  {

    private StudentRepository studentRepository;

    public StudentDto updateStudent(Long studentId, StudentDto updatedStudent) {
        Student student= studentRepository.findById(studentId).orElseThrow(
                ()->new ResourceNotFoundException("Student does not exists "+studentId)
        );
        student.setFirstName(updatedStudent.getFirstName());
        student.setLastName(updatedStudent.getLastName());
        student.setEmail(updatedStudent.getEmail());

        Student updatedStudentObj=studentRepository.save(student);
        return StudentDto.mapToStudentDto(updatedStudentObj);
    }
}
