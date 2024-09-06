package com.example.spring.project.ems.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.spring.project.ems.Entity.Document;

import java.util.List;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    List<Document> findByStudentId(Long studentId);
}
