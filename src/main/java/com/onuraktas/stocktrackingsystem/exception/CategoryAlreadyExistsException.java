package com.onuraktas.stocktrackingsystem.exception;

public class CategoryAlreadyExistsException extends RuntimeException{

    private final String message;

    public CategoryAlreadyExistsException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
