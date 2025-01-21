package com.hanafi.petcare.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import com.hanafi.petcare.response.ApiResponse;

import java.io.IOException;


@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(EmptyException.class)
    public ResponseEntity<ApiResponse> handleEmptyException(EmptyException e) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST).body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse> handleGeneralException(Exception e) {

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ApiResponse(e.getMessage(), null));
    }


    // Handle ResourceNotFoundException specifically
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> handleResourceNotFoundException(ResourceNotFoundException e) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ApiResponse> handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ApiResponse(e.getMessage(), null));
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<String> handleFileUploadException(FileUploadException ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error: " + ex.getMessage());
    }

    // Handle UserAlreadyExist exception
    @ExceptionHandler(UserAlreadyExist.class)
    public ResponseEntity<ApiResponse> handleUserAlreadyExistException(UserAlreadyExist e) {
        return ResponseEntity
                .status(HttpStatus.CONFLICT)
                .body(new ApiResponse(e.getMessage(), null));
    }

    // You can add more custom exception handlers as needed
}

