package com.onuraktas.stocktrackingsystem.exception;

public class CategoryBadRequestException extends RuntimeException {

    private final String message;

    public CategoryBadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
