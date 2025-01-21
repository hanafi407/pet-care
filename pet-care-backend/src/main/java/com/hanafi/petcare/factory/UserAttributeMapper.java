package com.hanafi.petcare.factory;

import com.hanafi.petcare.model.User;
import com.hanafi.petcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAttributeMapper {

    public void setCommonAttribute(RegistrationRequest source, User target) {
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        target.setEmail(source.getEmail());
        target.setPassword(source.getPassword());
        target.setGender(source.getGender());
        target.setPhoneNumber(source.getPhoneNumber());
        target.setUserType(source.getUserType());
        target.setEnabled(source.isEnabled());
//        target.setSpecialization(source.getSpecialization());

    }
}
