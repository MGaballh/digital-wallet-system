package com.example.digital_wallet_system.utiles.mappers;

import jakarta.validation.constraints.NotNull;
import com.example.digital_wallet_system.models.entities.User;
import com.example.digital_wallet_system.models.entities.Wallet;
import com.example.digital_wallet_system.models.enums.WalletStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public final class WalletMapper {
    private WalletMapper(){
        throw new IllegalStateException("This is Utility class cannot be instantiated");
    }

    public static Wallet toWallet(@NotNull final User user){
            Wallet wallet = Wallet.builder()
                    .user(user)
                    .balance(BigDecimal.ZERO)
                    .status(WalletStatus.ACTIVE)
                    .createdAt(LocalDateTime.now())
                    .build();
            return wallet;
    }
}
