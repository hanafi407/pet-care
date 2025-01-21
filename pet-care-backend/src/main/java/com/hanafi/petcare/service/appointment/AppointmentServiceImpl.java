package com.hanafi.petcare.service.appointment;

import com.hanafi.petcare.DTO.AppointmentDTO;
import com.hanafi.petcare.DTO.EntityConverter;
import com.hanafi.petcare.enums.AppointmentStatus;
import com.hanafi.petcare.exception.ResourceNotFoundException;
import com.hanafi.petcare.model.Appointment;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.repository.AppointmentRepository;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.AppointmentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hanafi.petcare.utils.DateTimeUtils.dateNow;
import static com.hanafi.petcare.utils.DateTimeUtils.timeNow;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final EntityConverter<Appointment, AppointmentDTO> entityConverter;
    private final AppointmentRepository appointmentRepository;
    private final UserRepository userRepository;


    @Override
    public Appointment createAppointment(AppointmentRequest request, Long veterinarianId, Long patientId) {
        Optional<User> patient = userRepository.findById(patientId);
        Optional<User> veterinarian = userRepository.findById(veterinarianId);

        Appointment appointment = new Appointment();

        if (patient.isPresent() && veterinarian.isPresent()) {
            appointment.setDate(request.getDate());
            appointment.setTime(request.getTime().truncatedTo(ChronoUnit.MINUTES));
            appointment.setReason(request.getReason());

            appointment.setPatient(patient.get());
            appointment.setVeterinarian(veterinarian.get());

//            addPatient(appointment,patient.get());
//            addVeterinarian(appointment,veterinarian.get());

            generateAppointmentNo(appointment);

            appointment.setStatus(AppointmentStatus.WAITING_FOR_APPROVAL);
            return appointmentRepository.save(appointment);
        }
        throw new ResourceNotFoundException("Patient and Veterinarian is not found.");
    }

    @Override
    public AppointmentDTO updateAppointment(Long appointmentId, AppointmentRequest appointmentRequest) {
        Appointment appointment = getAppointmentById(appointmentId);
        if(!Objects.equals(appointment.getStatus(), AppointmentStatus.WAITING_FOR_APPROVAL)){
            throw new IllegalStateException("Sorry, this appointment is no longer updated.");
        }
        appointment.setDate(appointmentRequest.getDate());
        appointment.setTime(appointmentRequest.getTime());
        appointment.setReason(appointmentRequest.getReason());
        AppointmentDTO appointmentDTO = entityConverter.mapEntityToDto(appointment, AppointmentDTO.class);
         appointmentRepository.save(appointment);
         return appointmentDTO;
    }

    @Override
    public List<AppointmentDTO> getAllAppointment() {
        List<Appointment> appointments = appointmentRepository.findAll();
        return appointments.stream()
                .map(appointment -> entityConverter.mapEntityToDto(appointment, AppointmentDTO.class) )
                .collect(Collectors.toList())
                ;
    }

    @Override
    public Appointment getAppointmentById(Long appointmentId) {
        return  appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment is not found."));
    }

    @Override
    public void deleteAppointment(Long appointmentId) {
        appointmentRepository.delete(getAppointmentById(appointmentId));
    }

    @Override
    public Appointment getAppointmentByNo(String appointmentNo) {
        return appointmentRepository.findByAppointmentNo(appointmentNo)
                .orElseThrow(()-> new ResourceNotFoundException("Appointment is not found."));
    }

    @Override
    public void addPatient(Appointment appointment, User patient) {
//        appointment.setPatient(patient);
//        if (patient.getAppointments() == null) {
//            patient.setAppointments(new ArrayList<>());
//        }
//
//        patient.getAppointments().add(appointment);
    }

    @Override
    public void addVeterinarian(Appointment appointment, User veterinarian) {
//        appointment.setVeterinarian(veterinarian);
//        if (veterinarian.getAppointments() == null) {
//            veterinarian.setAppointments(new ArrayList<>());
//        }
//
//        veterinarian.getAppointments().add(appointment);
    }

    @Override
    public void generateAppointmentNo(Appointment appointment) {
        String datePart = appointment.getDate() != null ? dateNow() : "";
        String timePart = appointment.getTime() != null ? timeNow() :"";
        String numbers = datePart + timePart + appointment.getPatient().getId()+appointment.getVeterinarian().getId();
        appointment.setAppointmentNo(numbers);
    }
}
