package com.springeboot.example.ems.backend.mapper;



import com.springeboot.example.ems.backend.dto.StudentDto;
import com.springeboot.example.ems.backend.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student employee) {
        if (employee == null) {
            return null;
        }
        return new StudentDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail()
        );
    }

    public Student toEntity(StudentDto employeeDto) {
        if (employeeDto == null) {
            return null;
        }
        return new Student(
                employeeDto.getId(),
                employeeDto.getFirstName(),
                employeeDto.getLastName(),
                employeeDto.getEmail()
        );
    }
}

