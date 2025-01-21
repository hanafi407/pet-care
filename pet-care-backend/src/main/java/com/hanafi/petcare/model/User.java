package com.hanafi.petcare.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static com.hanafi.petcare.utils.UrlMapping.DEFAULT_PHOTO;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String firstName;
    private String lastName;
    private String gender;
    private String phoneNumber;
    private String email;
    private String password;
    private String userType;
    private boolean enabled;
//    private String specialization;

    @CreationTimestamp
    private LocalDate createdAt;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> patientAppointments;

    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> veterinarianAppointments;

    private String photo = DEFAULT_PHOTO;

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> patientReviews;

    @OneToMany(mappedBy = "veterinarian", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Review> veterinarianReviews;





    //    @Transient
//    private String specialization;

//    @Transient
//    List<Appointment> appointments;
}
