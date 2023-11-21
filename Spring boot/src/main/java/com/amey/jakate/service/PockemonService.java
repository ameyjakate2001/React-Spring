package com.amey.jakate.service;

import com.amey.jakate.dto.PockemonDto;
import com.amey.jakate.dto.PockemonResponse;
import com.amey.jakate.models.Pockemon;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

public interface PockemonService {
    PockemonDto createPockemon(PockemonDto pockemonDto);
    List<PockemonDto> getAllPockemons();

    List<PockemonDto> getPockemonByUser();

    PockemonDto getPockemonById(int id);

    PockemonDto updatePockemon(PockemonDto pockemonDto ,int id);

    void deletePockemon(int id);
}
