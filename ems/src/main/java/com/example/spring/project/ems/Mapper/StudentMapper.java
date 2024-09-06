package com.example.spring.project.ems.Mapper;

import com.example.spring.project.ems.DTO.StudentDTO;
import com.example.spring.project.ems.Entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDTO toDTO(Student entity) {
        if (entity == null) {
            return null;
        }

        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setEmail(entity.getEmail());
        dto.setDepartment(entity.getDepartment());

        return dto;
    }

    public Student toEntity(StudentDTO dto) {
        if (dto == null) {
            return null;
        }

        Student entity = new Student();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setDepartment(dto.getDepartment());

        return entity;
    }
}