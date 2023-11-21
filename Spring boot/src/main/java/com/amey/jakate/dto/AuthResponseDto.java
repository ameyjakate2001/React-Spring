package com.amey.jakate.dto;

import org.springframework.beans.factory.annotation.Autowired;

public class AuthResponseDto {
    @Autowired
    private  String accessToken;

    @Autowired
    private String error;

    @Autowired
    private String email;
    @Autowired
    private String role;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AuthResponseDto(String accessToken, String error, String email, String role) {
        this.accessToken = accessToken;
        this.error=error;
        this.role = role;
        this.email = email;
    }
}
