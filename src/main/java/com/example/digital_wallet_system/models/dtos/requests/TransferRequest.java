package com.example.digital_wallet_system.models.dtos.requests;

import java.math.BigDecimal;

public record TransferRequest(String receiverPhone, BigDecimal amount) {
}
