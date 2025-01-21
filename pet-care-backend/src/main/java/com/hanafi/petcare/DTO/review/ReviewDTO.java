package com.hanafi.petcare.DTO.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDTO {
    private Long id;
    private String feedBack;
    private float ratings;
    private Long veterinarianId;
    private Long patientId;
}
