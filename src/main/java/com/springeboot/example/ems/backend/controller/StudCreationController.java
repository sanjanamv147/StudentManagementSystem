package com.springeboot.example.ems.backend.controller;

import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.service.StudentCreationService;
import lombok.AllArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/students/create")
public class StudCreationController {
    @Autowired
    private final StudentCreationService studentCreationService;



    @PostMapping
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto) {
        try {
            StudentDto savedStud = studentCreationService.createStudent(studentDto);
            return new ResponseEntity<>(savedStud, HttpStatus.CREATED);
        } catch (BadRequestException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while creating the student");
        }
    }
}
