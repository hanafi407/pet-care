package com.hanafi.petcare.request;

import lombok.Data;

@Data
public class AdminRequest extends RegistrationRequest {
    private String role;
}
