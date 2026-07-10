package com.example.digital_wallet_system.utiles.mappers;

import com.example.digital_wallet_system.models.entities.VirtualCard;
import com.example.digital_wallet_system.models.entities.Wallet;

import java.math.BigDecimal;
import java.time.LocalDate;

public class VirtualCardMapper {
    private VirtualCardMapper(){
        throw new IllegalStateException("This is Utility class cannot be instantiated");
    }

    public static VirtualCard toVirtualCard(Wallet wallet, String cardNumber, String cvv, LocalDate expiryDate, BigDecimal limitAmount) {
        return VirtualCard.builder()
                .wallet(wallet)
                .cardNumber(cardNumber)
                .cvv(cvv)
                .expiryDate(expiryDate)
                .limitAmount(limitAmount)
                .build();
    }
}
