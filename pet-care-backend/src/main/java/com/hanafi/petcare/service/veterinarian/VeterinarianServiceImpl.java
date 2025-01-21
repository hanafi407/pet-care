package com.hanafi.petcare.service.veterinarian;

import com.hanafi.petcare.model.User;
import com.hanafi.petcare.model.Veterinarian;
import com.hanafi.petcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarianServiceImpl implements VeterinarianService{
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

}
