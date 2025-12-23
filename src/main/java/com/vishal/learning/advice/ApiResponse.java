package com.vishal.learning.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;


@Data
public class ApiResponse <T>{
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponse(String message, T data, HttpStatus status) {
         this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }
}
