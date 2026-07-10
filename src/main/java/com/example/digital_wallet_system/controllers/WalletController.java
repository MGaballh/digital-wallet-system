package com.example.digital_wallet_system.controllers;

import com.example.digital_wallet_system.models.dtos.requests.AmountRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.digital_wallet_system.services.WalletService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.digital_wallet_system.models.dtos.requests.TransferRequest;
import com.example.digital_wallet_system.models.dtos.responses.TransferResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/wallets")

public class WalletController {

    private final WalletService walletService;

    @PostMapping("/create")
    public ResponseEntity<String> createWallet(@AuthenticationPrincipal final UserDetails userDetails){
        walletService.createWallet(userDetails.getUsername());
        return ResponseEntity.ok("Wallet Created Successfully -_-");
    }

    @PostMapping("/transfer")
    public ResponseEntity<TransferResponse> transferMoney(
            @RequestBody final TransferRequest transferRequest,
            @AuthenticationPrincipal final UserDetails userDetails ){

        walletService.transferMoney(userDetails.getUsername(),transferRequest);
        return ResponseEntity.ok(new TransferResponse(transferRequest.amount(),"Successful Operation -_-"));
    }

    @PostMapping("/deposit")
    public ResponseEntity<String> deposit(@RequestBody final AmountRequest amountRequest,
                                          @AuthenticationPrincipal final UserDetails userDetails){
        walletService.deposit(userDetails.getUsername(), amountRequest);
        return ResponseEntity.ok("The Money Added To Wallet Successfully -_-");
    }

    @PostMapping("/widthraw")
    public ResponseEntity<String> widthraw(@RequestBody final AmountRequest amountRequest,
                                           @AuthenticationPrincipal final UserDetails userDetails){
        walletService.withdraw(userDetails.getUsername(), amountRequest);
        return ResponseEntity.ok("The Money Widthraw Successfully -_-");
    }
}
