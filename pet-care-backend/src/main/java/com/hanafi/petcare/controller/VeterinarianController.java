package com.hanafi.petcare.controller;

import com.hanafi.petcare.service.veterinarian.VeterinarianService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/veterinarian")
public class VeterinarianController {
    private final VeterinarianService veterinarianService;

}
