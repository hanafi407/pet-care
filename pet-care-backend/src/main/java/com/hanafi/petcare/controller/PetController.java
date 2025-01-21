package com.hanafi.petcare.controller;

import com.hanafi.petcare.DTO.pet.PetDTO;
import com.hanafi.petcare.DTO.pet.PetRequestDTO;
import com.hanafi.petcare.response.ApiResponse;
import com.hanafi.petcare.service.pet.PetService;
import com.hanafi.petcare.utils.FeedBack;
import com.hanafi.petcare.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.PETS)
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllPet(){
        List<PetDTO> pets = petService.getAllPet();
        return ResponseEntity.ok(new ApiResponse(FeedBack.FIND_ALL,pets));
    }

    @PostMapping
    public ResponseEntity<ApiResponse> createPet(@RequestBody PetRequestDTO pet){
        PetDTO petDTO = petService.savePet(pet);
        return ResponseEntity.ok(new ApiResponse(FeedBack.CREATED,petDTO));
    }
    @PutMapping(UrlMapping.PET_ID)
    public ResponseEntity<ApiResponse> updatePet(@RequestBody PetDTO pet,@PathVariable Long petId){
        PetDTO petDTO = petService.updatePet(pet,petId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.UPDATED,petDTO));
    }
    @GetMapping(UrlMapping.PET_ID)
    public ResponseEntity<ApiResponse> getPetById(@PathVariable Long petId){
        PetDTO petDTO = petService.getPetById(petId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.FOUND,petDTO));
    }
    @DeleteMapping(UrlMapping.PET_ID)
    public ResponseEntity<ApiResponse> deletePetById(@PathVariable Long petId){
        petService.deletePet(petId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.DELETED,null));
    }
}
