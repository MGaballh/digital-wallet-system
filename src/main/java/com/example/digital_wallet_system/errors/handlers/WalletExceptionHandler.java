package com.example.digital_wallet_system.errors.handlers;

import com.example.digital_wallet_system.errors.exceptions.wallet.WalletException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class WalletExceptionHandler {

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<Map<String,Object>> handleWalletException(WalletException e){
        Map<String,Object> response = new HashMap<>();

        response.put("Error",e.getExceptionTitle());
        response.put("Message",e.getMessage());

        return ResponseEntity.ok(response);
    }
}
