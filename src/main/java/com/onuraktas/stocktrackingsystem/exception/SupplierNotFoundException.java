package com.onuraktas.stocktrackingsystem.exception;

public class SupplierNotFoundException extends RuntimeException {

    private String message;

    public SupplierNotFoundException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
