package com.example.digital_wallet_system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digital_wallet_system.models.entities.User;
import com.example.digital_wallet_system.models.entities.Wallet;

import java.util.UUID;
import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, UUID> {
    boolean existsByUser(User user);
    Optional<Wallet> findByUser(User user);
    Optional<Wallet> findByUserPhone(String s);
    Optional<Wallet> findByUserEmail(String email);
}
