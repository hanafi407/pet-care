package com.hanafi.petcare.repository;

import com.hanafi.petcare.model.Review;
import com.hanafi.petcare.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {
    Page<Review> findByVeterinarian(User veterinarian, Pageable pageable);
    Optional<Review> findByVeterinarianIdAndPatientId(long veterinarian_id, long patient_id);
}
