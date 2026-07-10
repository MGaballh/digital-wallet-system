package com.example.digital_wallet_system.errors.exceptions.wallet;

public class WalletException extends RuntimeException {

    private final String exceptionTitle;

    public WalletException(String message, String exceptionTitle) {
        super(message);
        this.exceptionTitle = exceptionTitle;
    }

    public String getExceptionTitle(){
        return this.exceptionTitle;
    }
}
