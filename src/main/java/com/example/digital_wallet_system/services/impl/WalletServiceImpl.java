package com.example.digital_wallet_system.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.digital_wallet_system.models.entities.User;
import com.example.digital_wallet_system.models.entities.Wallet;
import org.springframework.transaction.annotation.Transactional;
import com.example.digital_wallet_system.services.WalletService;
import com.example.digital_wallet_system.models.enums.WalletStatus;
import com.example.digital_wallet_system.services.TransactionService;
import com.example.digital_wallet_system.repositories.UserRepository;
import com.example.digital_wallet_system.models.enums.TransactionType;
import com.example.digital_wallet_system.repositories.WalletRepository;
import com.example.digital_wallet_system.models.dtos.requests.AmountRequest;
import com.example.digital_wallet_system.models.dtos.requests.TransferRequest;
import com.example.digital_wallet_system.errors.exceptions.wallet.WalletException;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.ResourceNotFoundException;

import static com.example.digital_wallet_system.utiles.mappers.WalletMapper.toWallet;
import static com.example.digital_wallet_system.utiles.mappers.Validation.validateAmount;

@Service
@RequiredArgsConstructor
public class WalletServiceImpl implements WalletService {

    private final UserRepository userRepository;
    private final WalletRepository walletRepository;
    private final TransactionService transactionService;

    @Override
    @Transactional
    public void createWallet(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Account Not Found"));

        if (walletRepository.existsByUser(user)) {
            throw new WalletException("User already has a wallet.", "Wallet Duplication");
        }

        Wallet wallet = toWallet(user);
        walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public void deposit(String email, AmountRequest amountRequest) {
        validateAmount(amountRequest.amount());

        Wallet wallet = getActiveWalletByEmail(email);

        wallet.setBalance(wallet.getBalance().add(amountRequest.amount()));
        walletRepository.save(wallet);

        transactionService.saveTransaction(wallet, TransactionType.DEPOSIT, amountRequest.amount(), "Wallet deposit via payment gateway");
    }

    @Override
    @Transactional
    public void withdraw(String email, AmountRequest amountRequest) {
        validateAmount(amountRequest.amount());

        Wallet wallet = getActiveWalletByEmail(email);

        if (wallet.getBalance().compareTo(amountRequest.amount()) < 0) {
            throw new WalletException("Your Balance Not Enough", "Operation Failed");
        }

        wallet.setBalance(wallet.getBalance().subtract(amountRequest.amount()));
        walletRepository.save(wallet);

        transactionService.saveTransaction(wallet, TransactionType.WITHDRAW, amountRequest.amount(), "Cash withdrawal completed");
    }

    @Override
    @Transactional
    public void transferMoney(String senderEmail, TransferRequest transferRequest) {
        validateAmount(transferRequest.amount());

        Wallet senderWallet = getActiveWalletByEmail(senderEmail);

        Wallet receiverWallet = walletRepository.findByUserPhone(transferRequest.receiverPhone())
                .orElseThrow(() -> new WalletException("Receiver wallet does not exist", "Wallet Not Found"));

        if (receiverWallet.getStatus() != WalletStatus.ACTIVE) {
            throw new WalletException("Receiver wallet is not active", "Wallet Status error");
        }

        if (senderWallet.getId().equals(receiverWallet.getId())) {
            throw new WalletException("Can not Send To Your Self", "Invalid Operation");
        }

        if (senderWallet.getBalance().compareTo(transferRequest.amount()) < 0) {
            throw new WalletException("Your Balance Not Enough", "Operation Failed");
        }

        senderWallet.setBalance(senderWallet.getBalance().subtract(transferRequest.amount()));
        receiverWallet.setBalance(receiverWallet.getBalance().add(transferRequest.amount()));

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);

        String senderDescription = "Transfer out to phone: " + transferRequest.receiverPhone();
        String receiverDescription = "Transfer in from phone: " + senderWallet.getUser().getPhone();

        transactionService.saveTransaction(senderWallet, TransactionType.TRANSFER, transferRequest.amount(), senderDescription);
        transactionService.saveTransaction(receiverWallet, TransactionType.TRANSFER, transferRequest.amount(), receiverDescription);
    }

    private Wallet getActiveWalletByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User Account not found -_-"));

        Wallet wallet = walletRepository.findByUser(user)
                .orElseThrow(() -> new ResourceNotFoundException("User Wallet not found -_-"));

        if (wallet.getStatus() != WalletStatus.ACTIVE) {
            throw new WalletException("Wallet not active", "Wallet Status error");
        }

        return wallet;
    }
}