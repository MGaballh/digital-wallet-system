package com.example.digital_wallet_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class DigitalWalletSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalWalletSystemApplication.class, args);
	}
}
