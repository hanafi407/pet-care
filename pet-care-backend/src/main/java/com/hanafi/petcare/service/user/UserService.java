package com.hanafi.petcare.service.user;

import com.hanafi.petcare.DTO.UserDTO;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.request.RegistrationRequest;
import com.hanafi.petcare.request.UserUpdateRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


public interface UserService<T extends User> {
    User register(RegistrationRequest request);
    User update(Long id, UserUpdateRequest request);
    T findById(Long id);
    void delete(Long id);

    List<UserDTO> findAll();
     void uploadPhoto(Long id, MultipartFile file);
}
