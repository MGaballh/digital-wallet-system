package com.example.digital_wallet_system.repositories;

import com.example.digital_wallet_system.models.entities.Wallet;
import org.springframework.stereotype.Repository;
import com.example.digital_wallet_system.models.entities.VirtualCard;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;
import java.util.List;
import java.util.Optional;

@Repository
public interface VirtualCardRepository extends JpaRepository<VirtualCard, UUID> {
    Optional<VirtualCard> findByCardNumber(String cardNumber);
    List<VirtualCard> findByWalletId(Wallet wallet);
}
