package com.onuraktas.stocktrackingsystem.exception;

public class ProductNotFoundException extends RuntimeException{

    private final String message;

    public ProductNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
