package com.hanafi.petcare.factory;

import com.hanafi.petcare.model.User;
import com.hanafi.petcare.request.RegistrationRequest;

public interface UserFactory {
    User createUser(RegistrationRequest request);
}
