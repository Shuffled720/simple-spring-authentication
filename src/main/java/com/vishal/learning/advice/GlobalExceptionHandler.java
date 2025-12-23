package com.vishal.learning.advice;

import com.vishal.learning.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception){
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiError> handleRuntimeException(RuntimeException exception){
        ApiError error = new ApiError(exception.getMessage(), HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(error,HttpStatus.FORBIDDEN);
    }



}
