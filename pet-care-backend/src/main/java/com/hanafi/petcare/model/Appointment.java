package com.hanafi.petcare.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hanafi.petcare.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Entity
@Data
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private LocalDate date;
    private LocalTime time;
    private String appointmentNo;
    private String reason;

    @CreationTimestamp
    private LocalDate createAt;

    @Enumerated(EnumType.STRING)
    private AppointmentStatus status ;

//    @JoinColumn(name = "patient_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User patient;

//    @JoinColumn(name = "veterinarian_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private User veterinarian;

    @OneToMany(mappedBy = "appointment",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Pet> pets;


}
