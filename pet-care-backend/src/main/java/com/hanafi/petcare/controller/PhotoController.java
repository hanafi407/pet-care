package com.hanafi.petcare.controller;

import com.hanafi.petcare.exception.EmptyException;
import com.hanafi.petcare.exception.ResourceNotFoundException;
import com.hanafi.petcare.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/photo")
@RequiredArgsConstructor
public class PhotoController {
    private static final String UPLOAD_DIR = "src/main/resources/uploads/";

    @PostMapping("/upload")
    public ResponseEntity<ApiResponse> uploadPhoto(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                throw new EmptyException("Photo is empty");
            }

            Path filePath = Paths.get(UPLOAD_DIR, file.getOriginalFilename());
            Files.createDirectories(filePath.getParent());
            Files.write(filePath,file.getBytes());

            return ResponseEntity
                    .ok(new ApiResponse("File uploaded successfully: " + file.getOriginalFilename(),null));
        } catch (IOException e) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ApiResponse("Could not upload file: "+ e.getMessage(), null));
        }

    }
}
