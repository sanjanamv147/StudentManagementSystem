package com.springeboot.example.ems.backend.controller;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentRetrievalService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/students")
public class StudRetrievalController {

    private final StudentRetrievalService studentRetrievalService;

    @GetMapping("{id}")
    public ResponseEntity<?> getStudentById(@PathVariable("id") Long studentId){
        try {
            StudentDto studentDto = studentRetrievalService.getStudentById(studentId);
            if (studentDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            return ResponseEntity.ok(studentDto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the student");
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllStudents(){
        try {
            List<StudentDto> students = studentRetrievalService.getAllStudents();
            if (students.isEmpty()) {
                return ResponseEntity.noContent().build(); // 204 No Content
            }
            return ResponseEntity.ok(students);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while fetching the students");
        }
    }
}
