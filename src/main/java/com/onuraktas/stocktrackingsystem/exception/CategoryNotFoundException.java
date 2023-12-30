package com.onuraktas.stocktrackingsystem.exception;

public class CategoryNotFoundException extends RuntimeException {

    private final String message;
    public CategoryNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
