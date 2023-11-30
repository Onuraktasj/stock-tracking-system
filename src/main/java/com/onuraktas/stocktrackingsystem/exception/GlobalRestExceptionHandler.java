package com.onuraktas.stocktrackingsystem.exception;

import com.onuraktas.stocktrackingsystem.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ErrorResponse> productNotFoundException(final Exception exception){
        var customRestError = ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
        return responseEntity(HttpStatus.NOT_FOUND,customRestError);
    }

    private static ResponseEntity<ErrorResponse> responseEntity(HttpStatus httpStatus, ErrorResponse errorResponse){
        return ResponseEntity.status(httpStatus.value())
                .body(errorResponse);
    }

}