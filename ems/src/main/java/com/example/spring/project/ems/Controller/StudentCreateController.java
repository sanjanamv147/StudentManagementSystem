package com.example.spring.project.ems.Controller;

import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Service.StudentCreateService;
import com.example.spring.project.ems.Service.UnauthorizedAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/student")
public class StudentCreateController {

    @Autowired
    private StudentCreateService studentService;

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(
            @RequestBody StudentDTO studentDTO,
            @AuthenticationPrincipal UserDetails userDetails) {
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse("ROLE_USER"); // Default to USER if role is not found

        try {
            return ResponseEntity.ok(studentService.createStudent(studentDTO, role));
        } catch (UnauthorizedAccessException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
        }
    }
}
