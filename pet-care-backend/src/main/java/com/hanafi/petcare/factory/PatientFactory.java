package com.hanafi.petcare.factory;

import com.hanafi.petcare.model.Patient;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PatientFactory {
    private final UserRepository userRepository;
    private final UserAttributeMapper userAttributeMapper;

    public Patient createPatient(RegistrationRequest request) {
        Patient patient = new Patient();
        userAttributeMapper.setCommonAttribute(request, patient);
        patient.setMedicalHistory(request.getMedicalHistory());
        return userRepository.save(patient);
    }
}
