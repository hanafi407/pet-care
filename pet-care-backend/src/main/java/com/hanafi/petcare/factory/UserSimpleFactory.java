package com.hanafi.petcare.factory;

import com.hanafi.petcare.model.User;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserSimpleFactory implements UserFactory {
    private final AdminFactory adminFactory;
    private final PatientFactory patientFactory;
    private final VeterinarianFactory veterinarianFactory;

    @Override
    public User createUser(RegistrationRequest request) {


         switch (request.getUserType()) {
            case "VET" -> {
                return veterinarianFactory.createVeterinarian(request);
            }
            case "PATIENT" -> {
               return patientFactory.createPatient(request);
            }
            case "ADMIN" -> adminFactory.createAdmin(request);
            default ->
                    throw new IllegalArgumentException("Unknown user type: " + request.getUserType());
        };
        return null;
    }
}


