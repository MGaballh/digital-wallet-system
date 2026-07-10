package com.example.digital_wallet_system.models.dtos.requests;

import com.example.digital_wallet_system.models.enums.UserRole;
import jakarta.validation.constraints.*;

public record RegisterRequest(
        @NotBlank(message = "fullName should take value")
        @Size(min = 5, max = 100, message = "full name size should be between(5,100)")
        String fullName,

        @NotBlank(message = "this field is required")
        @Email(message = "This email not valid")
        @Size(max = 150, message = "email size should be between(13,150)")
        String email,

        @NotBlank(message = "this field is required")
        @Pattern(regexp = "^01[0125]\\d{8}$", message = "Invalid Egyptian Phone Number")
        String phone,

        @NotBlank(message = "password is required field")
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,255}$",
                message = "password should contain numbers and characters and special char such #"
        )
        String password,

        @NotNull(message = "you should select the role of user")
        UserRole role
) {}