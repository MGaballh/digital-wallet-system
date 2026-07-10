package com.example.digital_wallet_system.errors.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.example.digital_wallet_system.errors.dtos.ValidationErrorResponse;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.HashMap;

@RestControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse<Map<String,String>>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException e){

        Map<String,String> errosMap = new HashMap<>();

        e.getBindingResult().getFieldErrors().forEach(
                (error) -> {
                    String fieldName = error.getField();
                    String errorMessage = error.getDefaultMessage();
                    errosMap.put(fieldName,errorMessage);
                }
        );

        ValidationErrorResponse<Map<String,String>> validationErrorResponse  = new ValidationErrorResponse<>(
                HttpStatus.BAD_REQUEST.value(),
                "Validation Errors",
                "You Should Fix This Erros",
                errosMap,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(validationErrorResponse);
    }
}
