package com.example.digital_wallet_system.services;

import com.example.digital_wallet_system.models.dtos.requests.LoginRequest;
import com.example.digital_wallet_system.models.dtos.responses.AuthResponse;
import com.example.digital_wallet_system.models.dtos.requests.RegisterRequest;

public interface AuthService {
    AuthResponse register(final RegisterRequest registerRequest);
    AuthResponse authenticate(final LoginRequest loginRequest);
    String logout(final String authHeader);
}
