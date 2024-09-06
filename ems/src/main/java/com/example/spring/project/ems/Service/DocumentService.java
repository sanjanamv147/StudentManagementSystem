package com.example.spring.project.ems.Service;

import com.example.spring.project.ems.DTO.DocumentDTO;
import com.example.spring.project.ems.Entity.Document;
import com.example.spring.project.ems.Repositories.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public DocumentDTO saveDocument(DocumentDTO documentDTO) {
        Document document = new Document();
        document.setName(documentDTO.getName());
        document.setUrl(documentDTO.getUrl());
        document.setStudentId(documentDTO.getStudentId());
        Document savedDocument = documentRepository.save(document);
        return new DocumentDTO(savedDocument.getId(), savedDocument.getName(), savedDocument.getUrl(), savedDocument.getStudentId());
    }

    public List<DocumentDTO> getDocumentsByStudentId(Long studentId) {
        return documentRepository.findByStudentId(studentId).stream()
                .map(doc -> new DocumentDTO(doc.getId(), doc.getName(), doc.getUrl(), doc.getStudentId()))
                .collect(Collectors.toList());
    }

    public DocumentDTO getDocumentById(Long documentId) {
        return documentRepository.findById(documentId)
                .map(doc -> new DocumentDTO(doc.getId(), doc.getName(), doc.getUrl(), doc.getStudentId()))
                .orElse(null);
    }
}










//package com.example.spring.project.ems.Service;
//
//import com.example.spring.project.ems.DTO.DocumentDTO;
//import com.example.spring.project.ems.Entity.Document;
//import com.example.spring.project.ems.Repositories.DocumentRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class DocumentService {
//
//    @Autowired
//    private DocumentRepository documentRepository;
//
//    public DocumentDTO saveDocument(DocumentDTO documentDTO) {
//        Document document = new Document();
//        document.setName(documentDTO.getName());
//        document.setUrl(documentDTO.getUrl());
//        document.setStudentId(documentDTO.getStudentId());
//        Document savedDocument = documentRepository.save(document);
//        return new DocumentDTO(savedDocument.getId(), savedDocument.getName(), savedDocument.getUrl(), savedDocument.getStudentId());
//    }
//
//    public List<DocumentDTO> getDocumentsByStudentId(Long studentId) {
//        return documentRepository.findByStudentId(studentId).stream()
//                .map(doc -> new DocumentDTO(doc.getId(), doc.getName(), doc.getUrl(), doc.getStudentId()))
//                .collect(Collectors.toList());
//    }
//
//    public DocumentDTO getDocumentById(Long documentId) {
//        return documentRepository.findById(documentId)
//                .map(doc -> new DocumentDTO(doc.getId(), doc.getName(), doc.getUrl(), doc.getStudentId()))
//                .orElse(null);
//    }
//}
