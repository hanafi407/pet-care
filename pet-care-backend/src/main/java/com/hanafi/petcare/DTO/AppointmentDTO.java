package com.hanafi.petcare.DTO;

import com.hanafi.petcare.enums.AppointmentStatus;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentDTO {

    private long id;
    private LocalDate date;
    private LocalTime time;
    private String appointmentNo;
    private String reason;
    private LocalDate createAt;
    private AppointmentStatus status;
    private Long patientId;
    private Long veterinarianId;
}
