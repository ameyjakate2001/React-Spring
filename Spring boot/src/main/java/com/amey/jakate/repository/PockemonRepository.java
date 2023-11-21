package com.amey.jakate.repository;

import com.amey.jakate.dto.PockemonDto;
import java.util.List;
import com.amey.jakate.models.Pockemon;
import com.amey.jakate.models.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PockemonRepository extends JpaRepository<Pockemon, Integer> {
    List<Pockemon> findByUserId(int id);
}
