package com.example.digital_wallet_system.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.digital_wallet_system.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteUser(@PathVariable final UUID id) {
        return ResponseEntity.ok(userService.deleteUser(id));
    }
}