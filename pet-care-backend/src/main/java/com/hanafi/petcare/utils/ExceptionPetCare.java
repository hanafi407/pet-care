package com.hanafi.petcare.utils;

import com.hanafi.petcare.response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ExceptionPetCare {
    public static ResponseEntity<ApiResponse> internalServerError(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
    }
}
