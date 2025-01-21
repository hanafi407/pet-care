package com.hanafi.petcare.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class AppointmentRequest {

    private LocalDate date;

    private LocalTime time;

    private String reason;


}

