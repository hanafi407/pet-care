package com.hanafi.petcare.service.user;

import com.hanafi.petcare.DTO.EntityConverter;
import com.hanafi.petcare.DTO.UserDTO;
import com.hanafi.petcare.exception.EmptyException;
import com.hanafi.petcare.exception.FileUploadException;
import com.hanafi.petcare.exception.ResourceNotFoundException;
import com.hanafi.petcare.exception.UserAlreadyExist;
import com.hanafi.petcare.factory.UserFactory;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.RegistrationRequest;
import com.hanafi.petcare.request.UserUpdateRequest;
import com.hanafi.petcare.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.hanafi.petcare.utils.UrlMapping.DEFAULT_PHOTO;
import static com.hanafi.petcare.utils.UrlMapping.UPLOAD_DIR;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserFactory userFactory;
    private final EntityConverter<User,UserDTO> entityConverter;

    @Override
    public User register(RegistrationRequest request) {
        if(userRepository.existsByEmail(request.getEmail())){
            throw new UserAlreadyExist("Email is already in use.");
        }
        return userFactory.createUser(request);
    }

    @Override
    public User update(Long id, UserUpdateRequest request) {
        User user = findById(id);
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setGender(request.getGender());
        user.setPhoneNumber(request.getPhoneNumber());
        return userRepository.save(user);
    }

    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User is not found."));
    }

    @Override
    public void delete(Long id) {
        userRepository.findById(id).ifPresentOrElse(userRepository::delete,
                () -> {
                    throw new ResourceNotFoundException("User is not found.");
                }
        );
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user ->entityConverter.mapEntityToDto(user, UserDTO.class))
                .collect(Collectors.toList());
    }

    public void uploadPhoto(Long id, MultipartFile file){

        try {
            if (file.isEmpty()) {
                throw new EmptyException("File is empty");
            }

            // Ensure the upload directory exists
            Path uploadDir = Paths.get(UPLOAD_DIR);
            Files.createDirectories(uploadDir);

            // Save the file
            String fileName = id + "_" + file.getOriginalFilename();
            Path filePath = uploadDir.resolve(fileName);
            Files.write(filePath, file.getBytes());

            // Update the user's photoPath in the database
            Optional<User> optionalUser = userRepository.findById(id);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                user.setPhoto(filePath.toString());
                userRepository.save(user);

            } else {
                throw new ResourceNotFoundException("User is not found.");
            }
        } catch (IOException e) {
            throw new FileUploadException("Error saving photo.",e);
        }
    }
}
