package com.example.digital_wallet_system.services;

import com.example.digital_wallet_system.models.dtos.requests.AmountRequest;
import com.example.digital_wallet_system.models.dtos.requests.TransferRequest;

import java.math.BigDecimal;

public interface WalletService {
    void createWallet(String email);
    void transferMoney(String senderEmail, TransferRequest transferRequest);
    void deposit(String email, AmountRequest amountRequest);
    void withdraw(String email, AmountRequest amountRequest);
}
