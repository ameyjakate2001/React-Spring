package com.amey.jakate.controllers;

import com.amey.jakate.dto.ReviewDto;
import com.amey.jakate.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000", maxAge = 360000000)

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping("/{pockemonId}")
    public ResponseEntity<ReviewDto> createReview(@PathVariable(value = "pockemonId") int pockemonId, @RequestBody ReviewDto reviewDto){
        return new ResponseEntity<>(reviewService.createReview(pockemonId, reviewDto), HttpStatus.CREATED);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/get-pockemon/{pockemonId}")
    public List<ReviewDto> getReviewsByPockemonId(@PathVariable(value = "pockemonId") int pockemonId){
     return reviewService.getReviewsByPockemonId(pockemonId);
    }
    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> getReviewById(@PathVariable(value = "reviewId") int reviewId){
        return new ResponseEntity<>(reviewService.getReviewById(reviewId), HttpStatus.OK);
    }
    @CrossOrigin(origins = "http://localhost:3001")
    @PutMapping("/{reviewId}")
    public ResponseEntity<ReviewDto> updateReview(@PathVariable(value = "reviewId") int reviewId, @RequestBody ReviewDto reviewDto){
       return new ResponseEntity<ReviewDto>(reviewService.updateReview(reviewId, reviewDto), HttpStatus.OK);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteReviewById(@PathVariable(value = "reviewId") int reviewId){
         return new ResponseEntity<String>(reviewService.deleteReview(reviewId), HttpStatus.OK);
    }
}
