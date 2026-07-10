package com.example.digital_wallet_system.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.digital_wallet_system.services.AuthService;
import com.example.digital_wallet_system.models.dtos.requests.LoginRequest;
import com.example.digital_wallet_system.models.dtos.responses.AuthResponse;
import com.example.digital_wallet_system.models.dtos.requests.RegisterRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody final RegisterRequest registerRequest){
        return ResponseEntity.ok(authService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> logIn(@Valid @RequestBody final LoginRequest loginRequest){
        return ResponseEntity.ok(authService.authenticate(loginRequest));
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Authorization") final String authHeader){
        return ResponseEntity.ok(authService.logout(authHeader));
    }
}
