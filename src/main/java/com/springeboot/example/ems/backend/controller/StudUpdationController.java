package com.springeboot.example.ems.backend.controller;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentModificationService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/students/update")
public class StudUpdationController {
    private final StudentModificationService studentModificationService;

    @PutMapping("{id}")
    public ResponseEntity<?> updateStudent(@PathVariable("id") Long studentId, @RequestBody StudentDto updatedStudent){
        try {
            StudentDto studentDto = studentModificationService.updateStudent(studentId, updatedStudent);
            if (studentDto == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
            }
            return ResponseEntity.ok(studentDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Student data");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the student");
        }
    }
}
