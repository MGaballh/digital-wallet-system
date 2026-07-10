package com.example.digital_wallet_system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.digital_wallet_system.models.entities.Wallet;
import com.example.digital_wallet_system.models.entities.Transaction;

import java.util.UUID;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> findByWalletOrderByCreatedAtDesc(Wallet wallet);
}
