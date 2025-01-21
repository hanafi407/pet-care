package com.hanafi.petcare.request;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
//@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "userType")
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = VeterinarianRequest.class, name = "VET"),
//        @JsonSubTypes.Type(value = PatientRequest.class, name = "PATIENT")
//})
public class RegistrationRequest {
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String password;
    private String userType;
    private boolean enabled;
    private MultipartFile photo;
    private String specialization;
    private String medicalHistory;
}

