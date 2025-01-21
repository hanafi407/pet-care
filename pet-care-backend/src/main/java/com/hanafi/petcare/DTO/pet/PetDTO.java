package com.hanafi.petcare.DTO.pet;

import lombok.Data;

@Data
public class PetDTO {
    private long id;
    private String name;
    private String species;
    private String breed;
    private String color;
    private int age;
    private Long appointmentId;
}
