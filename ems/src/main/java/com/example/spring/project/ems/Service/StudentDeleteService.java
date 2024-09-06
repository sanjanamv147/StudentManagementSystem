package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.Repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentDeleteService {

    @Autowired
    private StudentRepository employeeRepository;

    public void deleteStudent(Long id) {
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Student not found");
        }
    }
}
