package com.hanafi.petcare.service.review;

import com.hanafi.petcare.DTO.review.ReviewDTO;
import com.hanafi.petcare.DTO.review.ReviewRequestDTO;
import com.hanafi.petcare.enums.AppointmentStatus;
import com.hanafi.petcare.exception.EmptyException;
import com.hanafi.petcare.exception.ResourceNotFoundException;
import com.hanafi.petcare.exception.UserAlreadyExist;
import com.hanafi.petcare.model.Review;
import com.hanafi.petcare.model.User;
import com.hanafi.petcare.repository.AppointmentRepository;
import com.hanafi.petcare.repository.ReviewRepository;
import com.hanafi.petcare.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import  org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final UserRepository userRepository;
    private final ReviewRepository reviewRepository;
    private final AppointmentRepository appointmentRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<Review> getAllReview() {
        return reviewRepository.findAll();
    }

    @Override
    public ReviewDTO addReview(ReviewRequestDTO requestDTO, Long veterinarianId, Long patientId) {
        Optional<User> vet = userRepository.findById(veterinarianId);
        Optional<User> patient = userRepository.findById(patientId);
        if(vet.isEmpty() && patient.isEmpty()){
            throw new EmptyException("Veterinarian or patient is empty.");
        }

        Optional<Review> reviewByVeterinarianIdAndPatientId = reviewRepository.findByVeterinarianIdAndPatientId(veterinarianId, patientId);
        if(reviewByVeterinarianIdAndPatientId.isPresent()){
            throw new UserAlreadyExist("You have already review this veterinarian.");
        }

        boolean isAppointmentComplete = appointmentRepository.existsByVeterinarianIdAndPatientIdAndStatus(veterinarianId, patientId, AppointmentStatus.COMPLETED);

        if(!isAppointmentComplete){
            throw new IllegalStateException("Sorry, only patient with completed appointment with this veterinarian can leave a review");
        }

        if(veterinarianId.equals(patientId)){
            throw new IllegalArgumentException("Veterinarian can not reviews them self.");
        }
        Review review = new Review();
        review.setFeedBack(requestDTO.getFeedBack());
        review.setRatings(requestDTO.getRatings());
        review.setVeterinarian(vet.get());
        review.setPatient(patient.get());
        Review savedReview = reviewRepository.save(review);

        return modelMapper.map(savedReview,ReviewDTO.class);
    }

    @Override
    public ReviewDTO updateReview(ReviewRequestDTO reviewRequest, Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("User is not found."));
        review.setRatings(reviewRequest.getRatings());
        review.setFeedBack(reviewRequest.getFeedBack());
        Review savedReview = reviewRepository.save(review);
       return modelMapper.map(savedReview,ReviewDTO.class);
    }

    @Override
    public double getAverageVetReviews(Long vetId) {
        List<Review> reviews = reviewRepository.findAll();
        return reviews.stream().mapToDouble(Review::getRatings).average().orElse(0.0);
    }

    @Override
    public Page<ReviewDTO> getAllReviewsByUserId(Long vetId, Pageable page) {
        User veterinarian = userRepository.findById(vetId).orElseThrow(() -> new ResourceNotFoundException("User is not found"));
       Page<Review> reviews= reviewRepository.findByVeterinarian(veterinarian, page);
       return reviews.map(review -> modelMapper.map(review,ReviewDTO.class));

    }
}
