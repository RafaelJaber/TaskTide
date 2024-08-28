package com.javanauta.user.business;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.exceptions.ConflictException;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;

    public UserResponseDTO findUserByEmail(String email) {
        User user =  userRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email not founded " + email)
        );
        return userConverter.toResponse(user);
    }

    public UserResponseDTO save(UserRequestDTO dto) {
        try {
            this.existsByEmail(dto.getEmail());

            User user = userConverter.toEntity(dto);
            user.setPassword(passwordEncoder.encode(dto.getPassword()));

            User inserted = this.userRepository.save(user);
            return this.userConverter.toResponse(inserted);
        } catch (ConflictException e) {
            throw new ConflictException(e.getMessage(), e.getCause());

        }
    }

    public void deleteByEmail(String email) {
        this.userRepository.deleteByEmail(email);
    }

    private void existsByEmail(String email) {
        try {
            boolean exists = userRepository.existsByEmail(email);
            if (exists) {
                throw new ConflictException("User with email " + email + " already exists");
            }
        } catch (ConflictException e) {
            throw new ConflictException("User with email " + email + " already exists", e.getCause());
        }
    }
}
