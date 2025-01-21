package com.hanafi.petcare.controller;

import com.hanafi.petcare.DTO.review.ReviewDTO;
import com.hanafi.petcare.DTO.review.ReviewRequestDTO;
import com.hanafi.petcare.model.Review;
import com.hanafi.petcare.response.ApiResponse;
import com.hanafi.petcare.service.review.ReviewService;
import com.hanafi.petcare.utils.FeedBack;
import com.hanafi.petcare.utils.UrlMapping;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UrlMapping.REVIEWS)
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<ApiResponse> getAllReview(){
        List<Review> reviews = reviewService.getAllReview();
        List<ReviewDTO> reviewDTOS = reviews.stream().map(review -> modelMapper.map(review, ReviewDTO.class)).toList();
        return ResponseEntity.ok(new ApiResponse(FeedBack.FIND_ALL,reviewDTOS));
    }

    @PostMapping("veterinarianId/{veterinarianId}/patientId/{patientId}")
    public ResponseEntity<ApiResponse> addReviews(
            @RequestBody ReviewRequestDTO requestDTO,
            @PathVariable Long veterinarianId,
            @PathVariable Long patientId) {
        ReviewDTO reviewDTO = reviewService.addReview(requestDTO,veterinarianId,patientId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.CREATED, reviewDTO));
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<ApiResponse> updateReview(@RequestBody ReviewRequestDTO reviewRequest,@PathVariable Long reviewId ){
        ReviewDTO reviewDTO = reviewService.updateReview(reviewRequest, reviewId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.UPDATED,reviewDTO));
    }

    @GetMapping("/average/{vetId}")
    public ResponseEntity<ApiResponse> getAverageRatings(@PathVariable Long vetId){
        double averageVetReviews = reviewService.getAverageVetReviews(vetId);
        return ResponseEntity.ok(new ApiResponse(FeedBack.FOUND,averageVetReviews));
    }

    @GetMapping("/{vetId}")
    public Page<ReviewDTO> getReviewByVeterinarian(@PathVariable Long vetId, Pageable pageable){
        return reviewService.getAllReviewsByUserId(vetId,pageable);
    }
}
