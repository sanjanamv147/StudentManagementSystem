
package com.example.spring.project.ems.Controller;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/api/student")
public class FileUploadController {

    private static final String UPLOAD_DIR = "/home/sanjanamv/san"; // Adjust this path as necessary

    // Upload student document
    @PostMapping("/{id}/upload")
    public ResponseEntity<String> uploadDocument(@PathVariable("id") Long studentId,
                                                 @RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
        }

        File uploadDirectory = new File(UPLOAD_DIR);
        if (!uploadDirectory.exists()) {
            uploadDirectory.mkdirs(); // Create directory if it doesn't exist
        }

        try {
            // Save the file with a unique name or student ID prefix to avoid overwriting
            String filename = studentId + "_" + file.getOriginalFilename();
            File destinationFile = new File(uploadDirectory + File.separator + filename);
            file.transferTo(destinationFile);
            return ResponseEntity.ok("File uploaded successfully: " + filename);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
        }
    }

    // Serve the student document for download
    @GetMapping("/{id}/document")
    public ResponseEntity<Resource> downloadDocument(@PathVariable("id") Long studentId) {
        try {
            // Construct the file path based on student ID
            Path filePath = Paths.get(UPLOAD_DIR).resolve(studentId + "_document.pdf").normalize();  // Replace with actual filename logic
            File file = new File(filePath.toString());

            if (!file.exists()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }

            Resource resource = new FileSystemResource(file);
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getName() + "\"");

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}













//package com.example.spring.project.ems.Controller;
//
//
//
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/api/student")
//public class FileUploadController {
//
//    @PostMapping("/{id}/upload")
//    public ResponseEntity<String> uploadDocument(@PathVariable("id") Long studentId,
//                                                 @RequestParam("file") MultipartFile file) {
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
//        }
//
//        String uploadDir = "/home/sanjanamv/san"; // Adjust this path as necessary
//        File uploadDirectory = new File(uploadDir);
//        if (!uploadDirectory.exists()) {
//            uploadDirectory.mkdirs(); // Create directory if it doesn't exist
//        }
//
//        try {
//            File destinationFile = new File(uploadDir + File.separator + file.getOriginalFilename());
//            file.transferTo(destinationFile);
//            return ResponseEntity.ok("File uploaded successfully: " + destinationFile.getName());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed: " + e.getMessage());
//        }
//    }
//
//}







    // Other methods for handling student details...

//    @PostMapping("/{id}/upload")
//    public ResponseEntity<String> uploadDocument(@PathVariable("id") Long studentId,
//                                                 @RequestParam("file") MultipartFile file) {
//        // Validate the student ID and file
//        if (file.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
//        }
//
//        // Define the path where the file will be saved
//        String uploadDir = "/home/sanjanamv/san"; // Adjust this path as necessary
//        File uploadDirectory = new File(uploadDir);
//        if (!uploadDirectory.exists()) {
//            uploadDirectory.mkdirs(); // Create directory if it doesn't exist
//        }
//
//        // Save the file
//        try {
//            File destinationFile = new File(uploadDir + file.getOriginalFilename());
//            file.transferTo(destinationFile);
//            return ResponseEntity.ok("File uploaded successfully: " + destinationFile.getName());
//        } catch (IOException e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("File upload failed");
//        }
//    }
//}
