package com.example.digital_wallet_system.errors.exceptions.user;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);
    }
}
