package com.example.digital_wallet_system.services;

import com.example.digital_wallet_system.models.entities.Wallet;
import com.example.digital_wallet_system.models.enums.TransactionType;
import com.example.digital_wallet_system.models.dtos.responses.TransactionResponse;

import java.util.List;
import java.math.BigDecimal;

public interface TransactionService {
    void saveTransaction(Wallet wallet, TransactionType transactionType, BigDecimal amount, String description);
    List<TransactionResponse> getWalletHistory(String email);
}
