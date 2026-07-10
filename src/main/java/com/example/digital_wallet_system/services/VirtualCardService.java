package com.example.digital_wallet_system.services;

import com.example.digital_wallet_system.models.dtos.requests.CardPaymentRequest;
import com.example.digital_wallet_system.models.dtos.requests.VirtualCardRequest;
import com.example.digital_wallet_system.models.dtos.responses.VirtualCardResponse;

public interface VirtualCardService {
    VirtualCardResponse issueCard(String email, VirtualCardRequest request);
    String payUsingCard(CardPaymentRequest request);
}
