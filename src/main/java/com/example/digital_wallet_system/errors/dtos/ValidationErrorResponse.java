package com.example.digital_wallet_system.errors.dtos;

import java.time.LocalDateTime;

public record ValidationErrorResponse<T>(
        int status,
        String error,
        String message,
        T body,
        LocalDateTime time) {
}
