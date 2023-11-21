package com.amey.jakate.service.impl;

import com.amey.jakate.Exception.PockemonNotFoundException;
import com.amey.jakate.Exception.ReviewNotFoundException;
import com.amey.jakate.dto.ReviewDto;
import com.amey.jakate.models.Pockemon;
import com.amey.jakate.models.Review;
import com.amey.jakate.models.UserEntity;
import com.amey.jakate.repository.PockemonRepository;
import com.amey.jakate.repository.ReviewRepository;
import com.amey.jakate.repository.UserRepository;
import com.amey.jakate.security.AuthUtil;
import com.amey.jakate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewRepository reviewRepository;
    private PockemonRepository pockemonRepository;
    private UserRepository userRepository;
    @Autowired
    private AuthUtil auth;

    @Autowired
    public ReviewServiceImpl(ReviewRepository reviewRepository, PockemonRepository pokemonRepository,UserRepository userRepository) {
        this.reviewRepository = reviewRepository;
        this.pockemonRepository = pokemonRepository;
         this.userRepository =userRepository;
    }


    public ReviewDto createReview( int pockemonId,ReviewDto reviewDto){
          Review review = mapToEntity(reviewDto);
          UserEntity user = userRepository.findByUsername(auth.getCurrentUsername()).orElseThrow(() -> new UsernameNotFoundException("user not found"));
          Pockemon pockemon = pockemonRepository.findById(pockemonId).orElseThrow(()->new PockemonNotFoundException("Pockemon not found by Id"));
          review.setUser(user);
        review.setCreatedOn(java.time.LocalDate.now());
          review.setPockemon(pockemon);
          Review newReview =reviewRepository.save(review);
          return mapToDto(newReview);
    }

    public List<ReviewDto> getReviewsByPockemonId(int id){
        pockemonRepository.findById(id).orElseThrow(()->new PockemonNotFoundException("No pockemon with this id"));
        List<Review> reviews = reviewRepository.findByPockemonId(id);
        return reviews.stream().map(review-> mapToDto(review)).collect(Collectors.toList());
    }

    public ReviewDto getReviewById(int id){
        Review review = reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review could not be found"));
        System.out.println(review.getPockemon());;
        return mapToDto(review);
    }

    public ReviewDto updateReview(int id, ReviewDto reviewDto){
       Review review = reviewRepository.findById(id).orElseThrow(()-> new ReviewNotFoundException("Review Not Found"));
       review.setDescription(reviewDto.getDescription());
       review.setStars(reviewDto.getStars());
       return mapToDto(reviewRepository.save(review));
    }
    public String deleteReview(int id){
        reviewRepository.findById(id).orElseThrow(() -> new ReviewNotFoundException("Review not found"));
       reviewRepository.deleteById(id);
       return "Review Deleted Successfully";
    }

    private ReviewDto mapToDto(Review review){
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setDescription(review.getDescription());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }
    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setDescription(reviewDto.getDescription());
        review.setStars(reviewDto.getStars());
        return review;
    }

}
