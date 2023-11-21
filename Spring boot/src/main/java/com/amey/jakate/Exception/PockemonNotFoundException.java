package com.amey.jakate.Exception;

public class PockemonNotFoundException extends RuntimeException{
private static final long serialVersionUID = 1;
    public PockemonNotFoundException(String message) {
        super(message);
    }
}
