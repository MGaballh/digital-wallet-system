package com.example.digital_wallet_system.services.impl;

import com.example.digital_wallet_system.models.entities.Transaction;
import com.example.digital_wallet_system.repositories.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.digital_wallet_system.models.entities.Wallet;
import com.example.digital_wallet_system.models.enums.WalletStatus;
import com.example.digital_wallet_system.services.VirtualCardService;
import com.example.digital_wallet_system.models.entities.VirtualCard;
import com.example.digital_wallet_system.repositories.WalletRepository;
import com.example.digital_wallet_system.repositories.VirtualCardRepository;
import com.example.digital_wallet_system.models.dtos.requests.CardPaymentRequest;
import com.example.digital_wallet_system.models.dtos.requests.VirtualCardRequest;
import com.example.digital_wallet_system.errors.exceptions.wallet.WalletException;
import com.example.digital_wallet_system.models.dtos.responses.VirtualCardResponse;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.ResourceNotFoundException;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Random;
import java.time.LocalDate;
import java.util.UUID;

import static com.example.digital_wallet_system.utiles.mappers.Validation.validateAmount;
import static com.example.digital_wallet_system.utiles.mappers.VirtualCardMapper.toVirtualCard;

@Service
@RequiredArgsConstructor
public class VirtualCardServiceImpl implements VirtualCardService {

    private final Random random = new Random();
    private final WalletRepository walletRepository;
    private final VirtualCardRepository virtualCardRepository;
    private final TransactionRepository transactionRepository;

    @Override
    @Transactional
    public VirtualCardResponse issueCard(String email, VirtualCardRequest request) {

        validateAmount(request.amount());

        Wallet wallet = walletRepository.findByUserEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Wallet not found -_-"));

        if (wallet.getStatus() != WalletStatus.ACTIVE) {
            throw new WalletException("Wallet not active -_-", "Wallet Status error");
        }

        if (wallet.getBalance().compareTo(request.amount()) < 0) {
            throw new WalletException("Your Wallet balance is less than the requested card limit -_-", "Balance not enough");
        }

        String generatedCardNumber = generateRandomCardNumber();
        String generatedCvv = String.format("%03d", random.nextInt(1000));
        LocalDate expiryDate = LocalDate.now().plusMonths(1);

        VirtualCard virtualCard = toVirtualCard(wallet, generatedCardNumber, generatedCvv, expiryDate, request.amount());
        VirtualCard savedCard = virtualCardRepository.save(virtualCard);

        return new VirtualCardResponse(
                savedCard.getId(),
                savedCard.getCvv(),
                savedCard.getIsActive(),
                savedCard.getCardNumber(),
                savedCard.getExpiryDate(),
                savedCard.getLimitAmount()
        );
    }

    @Override
    @Transactional
    public String payUsingCard(CardPaymentRequest request) {
        validateAmount(request.amount());

        VirtualCard virtualCard = virtualCardRepository.findByCardNumber(request.cardNumber())
                .orElseThrow(() -> new ResourceNotFoundException("Card not found -_-"));

        if (!virtualCard.getIsActive() || virtualCard.getExpiryDate().isBefore(LocalDate.now())) {
            throw new WalletException("Card is inactive or expired! -_-", "Card Status Error");
        }

        if (!virtualCard.getCvv().equals(request.cvv())) {
            throw new WalletException("Invalid card details! -_-", "Security Error");
        }

        if (virtualCard.getLimitAmount().compareTo(request.amount()) < 0) {
            throw new WalletException("Transaction exceeds card limit!", "Limit Error");
        }

        Wallet wallet = virtualCard.getWallet();
        if (wallet.getBalance().compareTo(request.amount()) < 0) {
            throw new WalletException("Balance not enough in your main wallet!", "Balance Error");
        }

        virtualCard.setLimitAmount(virtualCard.getLimitAmount().subtract(request.amount()));
        wallet.setBalance(wallet.getBalance().subtract(request.amount()));

        if (virtualCard.getLimitAmount().compareTo(BigDecimal.ZERO) == 0) {
            virtualCard.setIsActive(false);
        }

        virtualCardRepository.save(virtualCard);
        walletRepository.save(wallet);

        Transaction transaction = Transaction.builder()
                .wallet(wallet)
                .amount(request.amount())
                .transactionReference(UUID.randomUUID().toString())
                .transactionType(com.example.digital_wallet_system.models.enums.TransactionType.PAYMENT)
                .description("Payment done using Virtual Card ending with: " + request.cardNumber().substring(12))
                .build();

        transactionRepository.save(transaction);
        return "Payment of " + request.amount() + " processed successfully!";
    }

    private String generateRandomCardNumber() {
        StringBuilder cardNumber = new StringBuilder("4");
        for (int i = 0; i < 15; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
}
