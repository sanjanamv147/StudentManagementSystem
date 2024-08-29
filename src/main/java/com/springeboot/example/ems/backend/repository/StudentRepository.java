package com.springeboot.example.ems.backend.repository;

import com.springeboot.example.ems.backend.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
