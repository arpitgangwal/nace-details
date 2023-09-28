package com.nacedetails.demo.exception.handler;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.StaleObjectStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    ResponseEntity<ErrorResponse> handleNotFound (Exception ex){
        logger.error("Nace not found,{}",ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),"Nace not found"), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(StaleObjectStateException.class)
    ResponseEntity<ErrorResponse> handleExceptionTransactionRollback (Exception ex){
        logger.error("Error occurred,{}",ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),"Operation rollback because entity you are updating might got changed"), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(Throwable.class)
    ResponseEntity<ErrorResponse> handleException (Exception ex){
        logger.error("Error occurred,{}",ex);
        return new ResponseEntity<>(new ErrorResponse(ex.getMessage(),"Internal Server Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
