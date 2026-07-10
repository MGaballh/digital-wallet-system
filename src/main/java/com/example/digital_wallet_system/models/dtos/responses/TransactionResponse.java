package com.example.digital_wallet_system.models.dtos.responses;

import java.util.UUID;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.example.digital_wallet_system.models.enums.TransactionType;

public record TransactionResponse(
        UUID id,
        BigDecimal amount,
        TransactionType transactionType,
        LocalDateTime timestamp,
        String description
) {}