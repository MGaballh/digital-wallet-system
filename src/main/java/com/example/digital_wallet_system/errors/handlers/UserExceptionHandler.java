package com.example.digital_wallet_system.errors.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.digital_wallet_system.errors.dtos.ErrorResponseDto;
import com.example.digital_wallet_system.errors.exceptions.user.UnauthorizedException;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ErrorResponseDto> handUnauthorizedException(UnauthorizedException e){
        return ResponseEntity.ok(new ErrorResponseDto(
                HttpStatus.UNAUTHORIZED.value(),
                "UnAuthorized Access",
                e.getMessage(),
                LocalDateTime.now()));
    }
}
