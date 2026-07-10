package com.example.digital_wallet_system.services;

public interface TokenBlackListService {
    void blackListToken(String token);
    boolean isBlackListed(String token);
}
