package com.springeboot.example.ems.backend.service;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import com.springeboot.example.ems.backend.repository.StudentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data

public  class StudentCreationService  {

    private StudentRepository studentRepository;

    public StudentDto createStudent(StudentDto studentDto) throws BadRequestException {
        Student student= Student.mapToStudent(studentDto);
        if(!studentDto.getEmail().contains("@"))
        {
            throw new BadRequestException("Invalid email");
        }
        Student savedStudent = studentRepository.save(student);

        return StudentDto.mapToStudentDto(savedStudent);
    }
}

