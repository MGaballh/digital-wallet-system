package com.example.digital_wallet_system.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.digital_wallet_system.services.TransactionService;
import com.example.digital_wallet_system.models.dtos.responses.TransactionResponse;

import java.util.List;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    @GetMapping("/history")
    public ResponseEntity<List<TransactionResponse>> getMyHistory(Principal principal) {

        List<TransactionResponse> history = transactionService.getWalletHistory(principal.getName());
        return ResponseEntity.ok(history);
    }
}
