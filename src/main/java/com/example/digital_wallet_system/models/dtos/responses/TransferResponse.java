package com.example.digital_wallet_system.models.dtos.responses;

import java.math.BigDecimal;

public record TransferResponse(
        BigDecimal amount,
        String message
) {
}
