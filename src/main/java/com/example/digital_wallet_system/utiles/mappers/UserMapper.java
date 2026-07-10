package com.example.digital_wallet_system.utiles.mappers;

import com.example.digital_wallet_system.models.enums.UserRole;
import jakarta.validation.constraints.NotNull;
import com.example.digital_wallet_system.models.entities.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.digital_wallet_system.models.dtos.requests.RegisterRequest;

public final class UserMapper {
    private UserMapper(){
        throw new IllegalStateException("This is Utility class cannot be instantiated");
    }

    public static User toUser(@NotNull final RegisterRequest registerRequest, PasswordEncoder passwordEncoder){
        return User.builder()
                .fullName(registerRequest.fullName())
                .email(registerRequest.email())
                .phone(registerRequest.phone())
                .password(passwordEncoder.encode(registerRequest.password()))
                .role(registerRequest.role())
                .build();
    }
}
