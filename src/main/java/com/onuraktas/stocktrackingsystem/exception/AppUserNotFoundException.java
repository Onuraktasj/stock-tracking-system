package com.onuraktas.stocktrackingsystem.exception;

public class AppUserNotFoundException extends RuntimeException {

    private final String message;

    public AppUserNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
