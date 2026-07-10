package com.example.digital_wallet_system.services.impl;

import lombok.extern.slf4j.Slf4j;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import com.example.digital_wallet_system.services.AuthService;
import com.example.digital_wallet_system.models.entities.User;
import org.springframework.transaction.annotation.Transactional;
import com.example.digital_wallet_system.security.jwt.JwtService;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.digital_wallet_system.security.user.UserPrincipal;
import com.example.digital_wallet_system.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.digital_wallet_system.services.TokenBlackListService;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.digital_wallet_system.models.dtos.requests.LoginRequest;
import org.springframework.security.authentication.BadCredentialsException;
import com.example.digital_wallet_system.models.dtos.responses.AuthResponse;
import com.example.digital_wallet_system.models.dtos.requests.RegisterRequest;
import com.example.digital_wallet_system.errors.exceptions.user.UnauthorizedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.DuplicateResourceException;

import static com.example.digital_wallet_system.utiles.mappers.UserMapper.toUser;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final TokenBlackListService tokenBlackListService;

    @Override
    @Transactional
    public AuthResponse register(final RegisterRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException("Email is already registered! -_-");
        }

        if (userRepository.existsByPhone(request.phone())) {
            throw new DuplicateResourceException("Phone is already registered! -_-");
        }

            User user = toUser(request,passwordEncoder);
        userRepository.save(user);

        log.info("User Saved In DB Successfully -_-");

        UserPrincipal userPrincipal = new UserPrincipal(user);
        final String jwtToken = jwtService.generateToken(userPrincipal);

        return new AuthResponse(jwtToken);
    }

    @Override
    public AuthResponse authenticate(final LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.email(),
                            loginRequest.password()
                    )
            );

            log.info("User Authenticated Successfully -_-");

            UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userPrincipal);

            return new AuthResponse(jwtToken);

        } catch (BadCredentialsException e) {
            throw new UnauthorizedException("Incorrect email or password! -_-");
        }
    }

    @Override
    public String logout(String authHeader) {
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new UnauthorizedException("Invalid Authorization header -_-");
        }

        String jwt = authHeader.substring(7);

        tokenBlackListService.blackListToken(jwt);
        SecurityContextHolder.clearContext();

        log.info("User logged out and token blacklisted successfully -_-");
        return "logOut Successfully -_-";
    }
}
