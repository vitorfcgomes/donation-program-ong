package com.doacaoong.donation_service.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)// a exceção em si
    @ResponseStatus(HttpStatus.NOT_FOUND)//o tipo da exception http que irá retornar
    public Map<String, Object> handleResourceNotFound(ResourceNotFoundException exception){
        return Map.of(
                "status", 404,
                "message", exception.getMessage(),
                "timestamp", Instant.now()
        );

    }
}

