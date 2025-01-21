package com.hanafi.petcare.service.pet;

import com.hanafi.petcare.DTO.EntityConverter;
import com.hanafi.petcare.DTO.pet.PetDTO;
import com.hanafi.petcare.DTO.pet.PetRequestDTO;
import com.hanafi.petcare.exception.ResourceNotFoundException;
import com.hanafi.petcare.model.Appointment;
import com.hanafi.petcare.model.Pet;
import com.hanafi.petcare.repository.AppointmentRepository;
import com.hanafi.petcare.repository.PetRepository;
import com.hanafi.petcare.service.appointment.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService {
    private final PetRepository petRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PetDTO> getAllPet() {
        List<Pet> pets= petRepository.findAll();
        return pets.stream()
                .map((pet) -> modelMapper.map(pet, PetDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PetDTO savePet(PetRequestDTO petDTOs) {
        Optional<Appointment> appointment = appointmentRepository.findById(petDTOs.getAppointmentId());
        if(appointment.isEmpty()){
            throw new ResourceNotFoundException("Appointment is not exist");
        }
        Pet pet = new Pet();
        pet.setName(petDTOs.getName());
        pet.setSpecies(petDTOs.getSpecies());
        pet.setBreed(petDTOs.getBreed());
        pet.setColor(petDTOs.getColor());
        pet.setAge(petDTOs.getAge());

        pet.setAppointment(appointment.get());
        Pet savedPet = petRepository.save(pet);
        return modelMapper.map(savedPet,PetDTO.class);
    }

    @Override
    public PetDTO updatePet( PetDTO pet, Long id) {

        PetDTO petDTO = getPetById(id);
        petDTO.setName(pet.getName());
        petDTO.setAge(pet.getAge());
        petDTO.setColor(pet.getColor());
        petDTO.setBreed(pet.getBreed());
        petDTO.setSpecies(pet.getSpecies());

        Pet savedPet = petRepository.save(modelMapper.map(petDTO, Pet.class));
        return modelMapper.map(savedPet,PetDTO.class);
    }

    @Override
    public PetDTO getPetById(Long id) {
        Pet pet= petRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Pet is not found."));
        return modelMapper.map(pet,PetDTO.class);
    }

    @Override
    public void deletePet(Long id) {
        petRepository.findById(id).ifPresentOrElse(petRepository::delete,()->{
            throw new ResourceNotFoundException("Pet is not found");
        });
    }
}
