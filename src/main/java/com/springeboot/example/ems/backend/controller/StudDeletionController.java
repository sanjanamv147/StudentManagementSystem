package com.springeboot.example.ems.backend.controller;

import com.springeboot.example.ems.backend.service.StudentDeletionService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@AllArgsConstructor
@RequestMapping("api/students/delete")
public class StudDeletionController {
    private final StudentDeletionService studentDeletionService;
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable("id") Long studentId){
        try {
            studentDeletionService.deleteStudent(studentId);
            return ResponseEntity.ok("Student deleted successfully");
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Student not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while deleting the student");
        }
    }
}
