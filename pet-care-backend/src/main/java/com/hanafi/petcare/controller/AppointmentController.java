package com.hanafi.petcare.controller;

import com.hanafi.petcare.DTO.AppointmentDTO;
import com.hanafi.petcare.DTO.EntityConverter;
import com.hanafi.petcare.model.Appointment;
import com.hanafi.petcare.request.AppointmentRequest;
import com.hanafi.petcare.response.ApiResponse;
import com.hanafi.petcare.service.appointment.AppointmentService;
import com.hanafi.petcare.utils.FeedBack;
import com.hanafi.petcare.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.APPOINTMENTS)
@RequiredArgsConstructor
public class AppointmentController {
    private final AppointmentService appointmentService;
    private final EntityConverter<Appointment, AppointmentDTO> entityConverter;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllAppointment() {
        List<AppointmentDTO> appointments = appointmentService.getAllAppointment();
        return ResponseEntity.ok(new ApiResponse(FeedBack.FIND_ALL, appointments));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createAppointment(
            @RequestBody AppointmentRequest appointmentRequest,
            @RequestParam Long veterinarianId,
            @RequestParam Long patientId
    ) {
        Appointment appointment = appointmentService.createAppointment(appointmentRequest, veterinarianId, patientId);
        AppointmentDTO appointmentDTO = entityConverter.mapEntityToDto(appointment, AppointmentDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.CREATED, appointmentDTO));
    }

    @GetMapping(UrlMapping.APPOINTMENT_ID)
    public ResponseEntity<ApiResponse> getAppointmentById(@PathVariable long appointmentId) {
        Appointment appointment = appointmentService.getAppointmentById(appointmentId);
        AppointmentDTO appointmentDTO = entityConverter.mapEntityToDto(appointment, AppointmentDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.FOUND, appointmentDTO));
    }

    @GetMapping(UrlMapping.APPOINTMENT_NO)
    public ResponseEntity<ApiResponse> getAppointmentByNo(@PathVariable String appointmentNo) {
        Appointment appointment = appointmentService.getAppointmentByNo(appointmentNo);
        AppointmentDTO appointmentDTO = entityConverter.mapEntityToDto(appointment, AppointmentDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.FOUND, appointmentDTO));
    }

    @DeleteMapping(UrlMapping.APPOINTMENT_ID)
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long appointmentId) {
        appointmentService.deleteAppointment(appointmentId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.DELETED, null));
    }

    @PutMapping(UrlMapping.APPOINTMENT_ID)
    public ResponseEntity<ApiResponse> updateAppointment(
            @PathVariable long appointmentId,
            @RequestBody AppointmentRequest appointmentRequest) {
        AppointmentDTO appointmentDTO = appointmentService.updateAppointment(appointmentId, appointmentRequest);
        return ResponseEntity.ok(new ApiResponse(FeedBack.UPDATED, appointmentDTO));

    }

}
