package com.hanafi.petcare.service.pet;

import com.hanafi.petcare.DTO.pet.PetDTO;
import com.hanafi.petcare.DTO.pet.PetRequestDTO;

import java.util.List;

public interface PetService {
   List<PetDTO> getAllPet();
   PetDTO savePet(PetRequestDTO pets);

   PetDTO updatePet(PetDTO pet, Long id);

   PetDTO getPetById(Long id);
   void deletePet(Long id);


}
