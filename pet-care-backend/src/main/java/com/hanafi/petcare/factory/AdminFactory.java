package com.hanafi.petcare.factory;

import com.hanafi.petcare.model.Admin;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.RegistrationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminFactory {
   private final UserRepository userRepository;
   private final UserAttributeMapper userAttributeMapper;
    public Admin createAdmin(RegistrationRequest request)
    {
        Admin admin = new Admin();
        userAttributeMapper.setCommonAttribute(request,admin);

        return userRepository.save(admin);
    }
}
