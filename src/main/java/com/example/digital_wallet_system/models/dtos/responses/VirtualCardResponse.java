package com.example.digital_wallet_system.models.dtos.responses;

import java.util.UUID;
import java.time.LocalDate;
import java.math.BigDecimal;

public record VirtualCardResponse(
        UUID id,
        String cvv,
        Boolean isActive,
        String cardNumber,
        LocalDate expiryDate,
        BigDecimal limitAmount
) {
}
