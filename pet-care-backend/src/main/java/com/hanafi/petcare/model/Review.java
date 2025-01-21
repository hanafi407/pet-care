package com.hanafi.petcare.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String feedBack;
    private float ratings;

    @ManyToOne(fetch = FetchType.LAZY)
    private User veterinarian;

    @ManyToOne(fetch = FetchType.LAZY)
    private User patient;
}
