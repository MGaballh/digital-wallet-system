package com.example.digital_wallet_system.errors.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.example.digital_wallet_system.errors.dtos.ErrorResponseDto;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.ResourceNotFoundException;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.DuplicateResourceException;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponseDto> handDuplicateResourceException (DuplicateResourceException e){
        return  ResponseEntity.ok(new ErrorResponseDto(
                HttpStatus.CONFLICT.value(),
                "Duplicate Resource",
                e.getMessage(),
                LocalDateTime.now()));
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handResourceNotFoundException (ResourceNotFoundException e){
        return  ResponseEntity.ok(new ErrorResponseDto(
                HttpStatus.NOT_FOUND.value(),
                "Resource NotFound",
                e.getMessage(),
                LocalDateTime.now()));
    }
}
