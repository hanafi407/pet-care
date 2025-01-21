package com.hanafi.petcare.controller;

import com.hanafi.petcare.DTO.EntityConverter;
import com.hanafi.petcare.DTO.UserDTO;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.repository.UserRepository;
import com.hanafi.petcare.request.RegistrationRequest;
import com.hanafi.petcare.request.UserUpdateRequest;
import com.hanafi.petcare.response.ApiResponse;
import com.hanafi.petcare.service.user.UserService;
import com.hanafi.petcare.utils.FeedBack;
import com.hanafi.petcare.utils.UrlMapping;
import org.springframework.core.io.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import static com.hanafi.petcare.utils.UrlMapping.UPLOAD_DIR;

@RestController
@RequestMapping(UrlMapping.USERS)
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final EntityConverter<User, UserDTO> entityConverter;


    @GetMapping(UrlMapping.ROOT)
    public ResponseEntity<ApiResponse> findAll() {
        List users = userService.findAll();
        return ResponseEntity.ok(new ApiResponse(FeedBack.FIND_ALL, users));
    }

    @GetMapping(UrlMapping.USER_ID)
    public ResponseEntity<ApiResponse> findById(@PathVariable Long userId) {

        User user = userService.findById(userId);
        UserDTO userDTO = entityConverter.mapEntityToDto(user, UserDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.FOUND, userDTO));

    }

    @PostMapping(UrlMapping.USER_REGISTER)
    public ResponseEntity<ApiResponse> register(@RequestBody RegistrationRequest request) {

        User theUser = userService.register(request);
        UserDTO userDTO = entityConverter.mapEntityToDto(theUser, UserDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.REGISTERED, userDTO));
    }

    @PutMapping(UrlMapping.USER_UPDATE)
    public ResponseEntity<ApiResponse> update(@PathVariable Long userId, @RequestBody UserUpdateRequest request) {
        User updateUser = userService.update(userId, request);
        UserDTO userDTO = entityConverter.mapEntityToDto(updateUser, UserDTO.class);
        return ResponseEntity.ok(new ApiResponse(FeedBack.UPDATED, userDTO));

    }

    @DeleteMapping(UrlMapping.DELETE)
    public ResponseEntity<ApiResponse> delete(@PathVariable Long userId) {
        userService.delete(userId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.DELETED, null));

    }

    @PostMapping("/{id}/uploadPhoto")
    public ResponseEntity<ApiResponse> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        userService.uploadPhoto(id,file);
        return ResponseEntity.ok(new ApiResponse("Success uploading photo", null));

    }

    @GetMapping("/{id}/photo")
    public ResponseEntity<Resource> getPhoto(@PathVariable Long id)  {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            String photoPath = user.getPhoto();
            if (photoPath == null || photoPath.isEmpty()) {
                photoPath = "src/main/resources/uploads/default/default-avatar.jpg";
            }

            Path filePath = Paths.get(photoPath);
            try {
                Resource resource = new UrlResource(filePath.toUri());
                return ResponseEntity.ok()
                        .contentType(MediaType.IMAGE_JPEG) // Adjust based on the photo type
                        .body(resource);
            } catch (MalformedURLException e) {
                throw new RuntimeException("Error loading photo", e);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
