package com.javanauta.user.business.usecases;

import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class DeleteUserByEmailService {

    private final UserRepository userRepository;

    @Transactional
    public void execute(String email) {
        if (!userRepository.existsByEmail(email)) {
            throw new ResourceNotFoundException("User with email " + email + " does not exist");
        }
        userRepository.deleteByEmail(email);
    }
}
