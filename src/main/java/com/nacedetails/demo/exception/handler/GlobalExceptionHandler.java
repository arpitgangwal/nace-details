package com.nacedetails.demo.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFound (Exception ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),"Nace not found"), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Throwable.class)
    ResponseEntity<ErrorResponse> handleException (Exception ex){
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),"Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
