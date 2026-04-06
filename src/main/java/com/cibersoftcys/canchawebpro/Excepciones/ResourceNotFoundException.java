package com.cibersoftcys.canchawebpro.Excepciones;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }

}
