package com.example.digital_wallet_system.security.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.digital_wallet_system.models.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import com.example.digital_wallet_system.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository
                .findByEmailAndIsDeletedFalse(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException(
                                "User not found with email: " + email + "-_-"
                        )
                );

        return new UserPrincipal(user);
    }
}