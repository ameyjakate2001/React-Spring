package com.amey.jakate.controllers;

import com.amey.jakate.dto.AuthResponseDto;
import com.amey.jakate.dto.LoginDto;
import com.amey.jakate.dto.RegisterDto;
import com.amey.jakate.models.UserEntity;
import com.amey.jakate.repository.UserRepository;
import com.amey.jakate.security.JWTGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 360000000)

@RestController
@RequestMapping("api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository,PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDto loginDto){

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            return new ResponseEntity<>(new AuthResponseDto(token, null, authentication.getName(), authentication.getAuthorities().toString()), HttpStatus.OK);
        } catch(AuthenticationException e){
            System.out.println("error");
            return new ResponseEntity<>(new AuthResponseDto(null, "Invalid Credentials", null , null), HttpStatus.UNAUTHORIZED);
        }
    }


    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
        System.out.println(registerDto.getUsername());
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            return new ResponseEntity<>("username is taken", HttpStatus.BAD_REQUEST);
        }
            UserEntity user =new UserEntity();
            user.setUsername(registerDto.getUsername());
            user.setPassword(passwordEncoder.encode(registerDto.getPassword()));
            user.setName(registerDto.getName());
            user.setAdmin(false);
            userRepository.save(user);
            return new ResponseEntity<>("user Registered Success", HttpStatus.OK);
        }

        @GetMapping("user")
    public ResponseEntity<AuthResponseDto> checkUser(){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            return new ResponseEntity<>(new AuthResponseDto(null, null, authentication.getName(), authentication.getAuthorities().toString()), HttpStatus.OK);
        }
}
