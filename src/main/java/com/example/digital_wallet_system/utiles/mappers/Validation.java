package com.example.digital_wallet_system.utiles.mappers;

import com.example.digital_wallet_system.errors.exceptions.wallet.WalletException;

import java.math.BigDecimal;

public final class Validation {
    private Validation(){
        throw new IllegalStateException("This is Utility class cannot be instantiated");
    }

    public static void validateAmount(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new WalletException("The amount should be greater than zero", "Amount Of money error");
        }
    }

}
