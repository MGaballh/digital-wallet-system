package com.example.digital_wallet_system.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.digital_wallet_system.services.UserService;
import com.example.digital_wallet_system.models.entities.User;
import com.example.digital_wallet_system.repositories.UserRepository;
import com.example.digital_wallet_system.errors.exceptions.public_Exceptions.ResourceNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public String deleteUser(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User Not found -_-"));

        if(user.isDeleted()){
            return "User Already Deleted -_-";
        }

        user.setDeleted(true);
        userRepository.save(user);

        return "User Deleted Successfully -_- Soft Delete -_- ";
    }
}
