package com.example.spring.project.ems.Repositories;

import com.example.spring.project.ems.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}