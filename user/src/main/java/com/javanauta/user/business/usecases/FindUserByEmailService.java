package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindUserByEmailService {

    private final UserRepository userRepository;
    private final UserConverter userConverter;

    @Transactional(readOnly = true)
    public UserResponseDTO execute(String email) {
        User user =  userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email not founded " + email)
        );
        return userConverter.toResponse(user);
    }
}
