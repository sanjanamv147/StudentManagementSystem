package com.springeboot.example.ems.backend.dto;

import com.springeboot.example.ems.backend.entity.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class StudentDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;

    public static StudentDto mapToStudentDto(Student student){
        return new StudentDto(
                student.getId(),
                student.getFirstName(),
                student.getLastName(),
                student.getEmail()
        );
    }
}
