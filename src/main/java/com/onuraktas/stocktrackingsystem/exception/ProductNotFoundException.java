package com.onuraktas.stocktrackingsystem.exception;

public class ProductNotFoundException extends RuntimeException{

    private String message;

    public ProductNotFoundException(String message){
        super(message);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
