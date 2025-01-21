package com.hanafi.petcare.model;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
//@PrimaryKeyJoinColumn(name = "patient_id")
public class Patient extends User {
//    private long id;
    private String medicalHistory;
}
