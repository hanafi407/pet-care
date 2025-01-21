package com.hanafi.petcare.service.review;

import com.hanafi.petcare.DTO.review.ReviewDTO;
import com.hanafi.petcare.DTO.review.ReviewRequestDTO;
import com.hanafi.petcare.model.Review;
import org.springframework.data.domain.Page;
import  org.springframework.data.domain.Pageable;
import java.util.List;

public interface ReviewService {
    List<Review> getAllReview();
    ReviewDTO addReview(ReviewRequestDTO requestDTO, Long veterinarianId, Long patientId);
    ReviewDTO updateReview(ReviewRequestDTO reviewRequest, Long reviewId);
    double getAverageVetReviews(Long vetId);

    Page<ReviewDTO> getAllReviewsByUserId(Long vetId, Pageable page);
}
