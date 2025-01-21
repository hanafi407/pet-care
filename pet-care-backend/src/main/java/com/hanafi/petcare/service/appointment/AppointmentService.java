package com.hanafi.petcare.service.appointment;

import com.hanafi.petcare.DTO.AppointmentDTO;
import com.hanafi.petcare.model.Appointment;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.request.AppointmentRequest;

import java.util.List;

public interface AppointmentService {

    Appointment createAppointment(AppointmentRequest request, Long veterinarianId, Long patientId);

    AppointmentDTO updateAppointment(Long appointmentId, AppointmentRequest appointment);

    List<AppointmentDTO> getAllAppointment();

    Appointment getAppointmentById(Long id);

    void deleteAppointment(Long id);

    Appointment getAppointmentByNo(String appointmentNo);

    void addPatient(Appointment appointment, User patient);

    void addVeterinarian(Appointment appointment, User veterinarian);

    void generateAppointmentNo(Appointment appointment);
}
