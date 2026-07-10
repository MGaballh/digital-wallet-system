package com.example.digital_wallet_system.services.impl;

import org.springframework.stereotype.Service;
import com.example.digital_wallet_system.services.TokenBlackListService;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlackListServiceImpl implements TokenBlackListService {

    private final Map<String,Boolean> blackList = new ConcurrentHashMap<>();


    @Override
    public void blackListToken(String token) {
        blackList.put(token,true);
    }

    @Override
    public boolean isBlackListed(String token) {
        return blackList.containsKey(token);
    }
}
