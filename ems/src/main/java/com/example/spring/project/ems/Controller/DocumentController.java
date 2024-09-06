package com.example.spring.project.ems.Controller;

import com.example.spring.project.ems.DTO.DocumentDTO;
import com.example.spring.project.ems.Service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    private static final String DOCUMENTS_DIR = "/home/sanjanamv/san"; // Path where documents are stored

    @PostMapping("/upload")
    public ResponseEntity<DocumentDTO> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("studentId") Long studentId) {
        String fileName = file.getOriginalFilename();
        Path filePath = Paths.get(DOCUMENTS_DIR, fileName);

        try {
            if (Files.exists(filePath)) {
                String newFileName = System.currentTimeMillis() + "_" + fileName;
                filePath = Paths.get(DOCUMENTS_DIR, newFileName);
                fileName = newFileName;
            }

            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            String url = "/documents/download/" + fileName; // URL for downloading the file

            DocumentDTO documentDTO = new DocumentDTO(null, fileName, url, studentId);

            DocumentDTO savedDocument = documentService.saveDocument(documentDTO);

            return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);

        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<Resource> downloadDocument(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(DOCUMENTS_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<DocumentDTO>> getDocumentsByStudentId(@PathVariable Long studentId) {
        List<DocumentDTO> documents = documentService.getDocumentsByStudentId(studentId);
        if (documents.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }
}











//package com.example.spring.project.ems.Controller;
//
//import com.example.spring.project.ems.DTO.DocumentDTO;
//import com.example.spring.project.ems.Service.DocumentService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.*;
//import java.util.List;
//
//@RestController
//@RequestMapping("/documents")
//public class DocumentController {
//
//    @Autowired
//    private DocumentService documentService;
//
//    private static final String DOCUMENTS_DIR = "/home/sanjanamv/san"; // Path where documents are stored
//
//    @PostMapping("/upload")
//    public ResponseEntity<DocumentDTO> uploadDocument(@RequestParam("file") MultipartFile file, @RequestParam("studentId") Long studentId) {
//        // Generate file name and file path
//        String fileName = file.getOriginalFilename();
//        Path filePath = Paths.get(DOCUMENTS_DIR, fileName);
//
//        try {
//            // Check if file already exists, rename if necessary
//            if (Files.exists(filePath)) {
//                String newFileName = System.currentTimeMillis() + "_" + fileName;
//                filePath = Paths.get(DOCUMENTS_DIR, newFileName);
//                fileName = newFileName;
//            }
//
//            // Save the file to the specified directory
//            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
//
//            // Generate the URL for the saved file
//            String url = "/home/sanjanamv/san/" + fileName; // Generate URL based on controller endpoint
//
//            // Create DocumentDTO object
//            DocumentDTO documentDTO = new DocumentDTO(null, fileName, url, studentId);
//
//            // Save document details in the database
//            DocumentDTO savedDocument = documentService.saveDocument(documentDTO);
//
//            // Return the saved document along with HTTP status
//            return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    @GetMapping("/download/{fileName}")
//    public ResponseEntity<Resource> downloadDocument(@PathVariable String fileName) {
//        try {
//            Path filePath = Paths.get(DOCUMENTS_DIR).resolve(fileName).normalize();
//            Resource resource = new UrlResource(filePath.toUri());
//
//            if (!resource.exists()) {
//                return ResponseEntity.notFound().build();
//            }
//
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
//                    .body(resource);
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
//        }
//    }
//
//    @GetMapping("/student/{studentId}")
//    public ResponseEntity<List<DocumentDTO>> getDocumentsByStudentId(@PathVariable Long studentId) {
//        List<DocumentDTO> documents = documentService.getDocumentsByStudentId(studentId);
//        if (documents.isEmpty()) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        return new ResponseEntity<>(documents, HttpStatus.OK);
//    }
//}
