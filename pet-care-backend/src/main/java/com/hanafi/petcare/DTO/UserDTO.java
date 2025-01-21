package com.hanafi.petcare.DTO;

import jakarta.persistence.Transient;
import lombok.Data;

@Data
public class UserDTO {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String userType;
    private boolean enabled;
    private String specialization;
}
