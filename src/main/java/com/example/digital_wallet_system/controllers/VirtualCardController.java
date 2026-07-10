package com.example.digital_wallet_system.controllers;

import lombok.RequiredArgsConstructor;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.digital_wallet_system.services.VirtualCardService;
import com.example.digital_wallet_system.models.dtos.requests.VirtualCardRequest;
import com.example.digital_wallet_system.models.dtos.requests.CardPaymentRequest;
import com.example.digital_wallet_system.models.dtos.responses.VirtualCardResponse;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/virtual-cards")
public class VirtualCardController {

    private final VirtualCardService virtualCardService;

    @PostMapping
    public ResponseEntity<VirtualCardResponse> issueVirtualCard(@NotNull final Principal principal,
                                                                @RequestBody final VirtualCardRequest request) {
        VirtualCardResponse response = virtualCardService.issueCard(principal.getName(), request);
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/pay")
    public ResponseEntity<String> payWithCard(@RequestBody CardPaymentRequest request) {
        String responseMessage = virtualCardService.payUsingCard(request);
        return ResponseEntity.ok().body(responseMessage);
    }
}
