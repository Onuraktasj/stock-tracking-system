package com.onuraktas.stocktrackingsystem.exception;

public class ProductBadRequestException extends RuntimeException {
    private String message;

    public ProductBadRequestException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
