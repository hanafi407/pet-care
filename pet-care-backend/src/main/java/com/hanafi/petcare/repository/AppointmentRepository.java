package com.hanafi.petcare.repository;

import com.hanafi.petcare.enums.AppointmentStatus;
import com.hanafi.petcare.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByAppointmentNo(String no);
    boolean existsByVeterinarianIdAndPatientIdAndStatus(long veterinarian_id, long patient_id, AppointmentStatus status);
}
