package com.vishal.learning.advice;

import com.vishal.learning.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ApiError handleResourceNotFoundException(ResourceNotFoundException exception){
        return new ApiError(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(RuntimeException.class)
    public ApiError handleRuntimeException(RuntimeException exception){
        return new ApiError(exception.getMessage(), HttpStatus.FORBIDDEN);
    }



}
