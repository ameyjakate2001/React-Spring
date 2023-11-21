package com.amey.jakate.repository;

import com.amey.jakate.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByPockemonId(int pockemonId);

}
