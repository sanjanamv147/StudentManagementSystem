package com.example.spring.project.ems.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor

public class DocumentDTO {
    private Long id;
    private String name;
    private String url;
    private Long studentId; // Include student ID for linking
}
