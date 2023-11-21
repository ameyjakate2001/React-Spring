package com.amey.jakate.service;

import com.amey.jakate.dto.ReviewDto;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ReviewService {
    ReviewDto createReview(int pockemonId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByPockemonId(int id);

    ReviewDto getReviewById( int id);

    ReviewDto updateReview(int id, ReviewDto reviewDto);

    String deleteReview(int id);
}

