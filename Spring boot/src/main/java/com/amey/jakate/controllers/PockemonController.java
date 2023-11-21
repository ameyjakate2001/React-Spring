package com.amey.jakate.controllers;

import com.amey.jakate.dto.PockemonDto;
import com.amey.jakate.dto.PockemonResponse;
import com.amey.jakate.models.Pockemon;
import com.amey.jakate.models.UserEntity;
import com.amey.jakate.repository.UserRepository;
import com.amey.jakate.security.JWTGenerator;
import com.amey.jakate.service.PockemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 360000000)
@RestController
@RequestMapping("/api/pockemons")
public class PockemonController {
    private PockemonService pockemonService;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    public PockemonController(PockemonService pockemonService) {
        this.pockemonService = pockemonService;

    }
    @CrossOrigin(origins = "http://localhost:3001")
    @GetMapping("")
    public ResponseEntity<List<PockemonDto>> getAllPockemons(){
         return new ResponseEntity<>(pockemonService.getAllPockemons(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public PockemonDto getPockemon(@PathVariable int id){
        return pockemonService.getPockemonById(id);
    }

    @GetMapping("/user")
    public List<PockemonDto> getPockemonByUser(){
        return pockemonService.getPockemonByUser();
    }

    @PostMapping("")
    @ResponseStatus
    public ResponseEntity<PockemonDto> createPockemon(@RequestBody PockemonDto pockemonDto){
        return new ResponseEntity<>(pockemonService.createPockemon(pockemonDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PockemonDto> updatePockemon(@RequestBody PockemonDto pockemonDto, @PathVariable("id") int pockemonId){
       PockemonDto res = pockemonService.updatePockemon(pockemonDto, pockemonId);
        return new ResponseEntity<PockemonDto>(res ,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
        public ResponseEntity<String> deletePockemon(@PathVariable("id") int pockemonId){
            pockemonService.deletePockemon(pockemonId);
             return ResponseEntity.ok("pockemon deleted successfully");
        }

}




