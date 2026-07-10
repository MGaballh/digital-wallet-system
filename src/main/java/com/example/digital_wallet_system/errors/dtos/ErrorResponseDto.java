package com.example.digital_wallet_system.errors.dtos;

import java.time.LocalDateTime;

public record ErrorResponseDto(
        int status,
        String error,
        String message,
        LocalDateTime timeStamp
){}