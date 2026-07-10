package com.example.digital_wallet_system.services.impl;

import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.ResourceNotFoundException;
import com.example.digital_wallet_system.models.dtos.responses.TransactionResponse;
import com.example.digital_wallet_system.repositories.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.digital_wallet_system.models.entities.Wallet;
import com.example.digital_wallet_system.services.TransactionService;
import com.example.digital_wallet_system.models.entities.Transaction;
import com.example.digital_wallet_system.models.enums.TransactionType;
import com.example.digital_wallet_system.models.enums.TransactionStatus;
import com.example.digital_wallet_system.repositories.TransactionRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.math.BigDecimal;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final WalletRepository walletRepository;
    private final TransactionRepository transactionRepository;

    @Override
    public void saveTransaction(Wallet wallet, TransactionType transactionType, BigDecimal amount, String description) {

            Transaction transaction = Transaction.builder()
                    .amount(amount)
                    .wallet(wallet)
                    .transactionType(transactionType)
                    .status(TransactionStatus.SUCCESS)
                    .description(description)
                    .transactionReference(UUID.randomUUID().toString())
                    .build();
            transactionRepository.save(transaction);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TransactionResponse> getWalletHistory(String email) {

        Wallet wallet = walletRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found for this user"));

        return transactionRepository.findByWalletOrderByCreatedAtDesc(wallet)
                .stream()
                .map(tx -> new TransactionResponse(
                        tx.getId(),
                        tx.getAmount(),
                        tx.getTransactionType(),
                        tx.getCreatedAt(),
                        tx.getDescription()
                ))
                .collect(toList());
    }

}
