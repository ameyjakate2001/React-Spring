package com.amey.jakate.service.impl;

import com.amey.jakate.Exception.PockemonNotFoundException;
import com.amey.jakate.dto.PockemonDto;
import com.amey.jakate.models.Pockemon;
import com.amey.jakate.models.UserEntity;
import com.amey.jakate.repository.PockemonRepository;
import com.amey.jakate.repository.UserRepository;
import com.amey.jakate.security.AuthUtil;
import com.amey.jakate.service.PockemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PockemonServiceImpl implements PockemonService {
    @Autowired
    private PockemonRepository pockemonRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthUtil auth;


    public PockemonServiceImpl(PockemonRepository pockemonRepository, UserRepository userRepository) {
        this.pockemonRepository = pockemonRepository;
        this.userRepository= userRepository;
    }

    @Override
   public PockemonDto createPockemon(PockemonDto pockemonDto){
        Pockemon pockemon = new Pockemon();
        UserEntity user = userRepository.findByUsername(auth.getCurrentUsername()).orElseThrow(()->new PockemonNotFoundException("user not found"));
        pockemon.setName(pockemonDto.getName());
        pockemon.setType(pockemonDto.getType());
        pockemon.setCreatedOn(java.time.LocalDate.now());
        pockemon.setUser(user);
        Pockemon newPockemon = pockemonRepository.save(pockemon);
        PockemonDto pockmonto = new PockemonDto();
        pockemonDto.setId(newPockemon.getId());
        pockemonDto.setName(newPockemon.getName());
        pockemonDto.setType(newPockemon.getType());
        return pockemonDto;
    }


    @Override
    public List<PockemonDto> getAllPockemons(){
        return pockemonRepository.findAll().stream().map(pock-> mapToDto(pock)).collect(Collectors.toList());

    }

    @Override
    public PockemonDto getPockemonById(int id) {
        Pockemon pockemon = pockemonRepository.findById(id).orElseThrow(() -> new PockemonNotFoundException("pockemon could not be found"));
       return mapToDto(pockemon);

    }

    public List<PockemonDto> getPockemonByUser(){
        String username = auth.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        List<Pockemon> pockemons = pockemonRepository.findByUserId(user.getId());
        return pockemons.stream().map(pock-> mapToDto(pock)).collect(Collectors.toList());
    }


    public PockemonDto updatePockemon(PockemonDto pockemonDto, int id){
        Pockemon pockemon = pockemonRepository.findById(id).orElseThrow(() -> new PockemonNotFoundException("Pockemon Not found by Id"));
        pockemon.setType(pockemonDto.getType());
        pockemon.setName(pockemonDto.getName());
        Pockemon updatedPockemon = pockemonRepository.save(pockemon);
        return mapToDto(updatedPockemon);
    }

    public void deletePockemon(int id){
        UserEntity user = userRepository.findByUsername(auth.getCurrentUsername()).orElseThrow(()-> new UsernameNotFoundException("user not found"));
        Pockemon pockemon = pockemonRepository.findById(id).orElseThrow(() ->new PockemonNotFoundException("Pockemon not found") );
        if(user.getId() == pockemon.getUser().getId()){
            pockemonRepository.delete(pockemon);
        }

    }

    private PockemonDto mapToDto(Pockemon pockemon){
        PockemonDto pockemonDto = new PockemonDto();
                pockemonDto.setId(pockemon.getId());
                pockemonDto.setType(pockemon.getType());
                pockemonDto.setName(pockemon.getName());
                return pockemonDto;
    }
    private Pockemon mapToEntity(PockemonDto pockemonDto){
        Pockemon pockemon = new Pockemon();
        pockemon.setId(pockemonDto.getId());
        pockemon.setType(pockemonDto.getType());
        pockemon.setName(pockemonDto.getName());
        return pockemon;
    }
}
