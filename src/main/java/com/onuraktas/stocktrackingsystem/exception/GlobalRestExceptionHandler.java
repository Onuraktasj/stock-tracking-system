package com.onuraktas.stocktrackingsystem.exception;

import com.onuraktas.stocktrackingsystem.dto.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class GlobalRestExceptionHandler {

    @ExceptionHandler({
            AppUserNotFoundException.class,
            ProductNotFoundException.class,
            SupplierNotFoundException.class,
            CategoryNotFoundException.class,
    })
    public ResponseEntity<ErrorResponse> notFoundException(final Exception exception){
        var customRestError = ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
        return responseEntity(HttpStatus.NOT_FOUND, customRestError);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> categoryAlreadyExistsException(final CategoryAlreadyExistsException exception){
        var customRestError = ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
        return responseEntity(HttpStatus.NOT_ACCEPTABLE, customRestError);
    }

    @ExceptionHandler(ProductBadRequestException.class)
    public ResponseEntity<ErrorResponse> badRequestException(final ProductBadRequestException exception){
        var customRestError = ErrorResponse.builder()
                .message(exception.getMessage())
                .build();
        return responseEntity(HttpStatus.BAD_REQUEST, customRestError);
    }

    private static ResponseEntity<ErrorResponse> responseEntity(HttpStatus httpStatus, ErrorResponse errorResponse){
        return ResponseEntity.status(httpStatus.value())
                .body(errorResponse);
    }

}
