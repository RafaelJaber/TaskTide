package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateCurrentUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final JwtUtil jwtUtil;

    @Transactional
    public UserResponseDTO execute(UserRequestDTO dto) {
        User userEntity = jwtUtil.getCurrentUser();
        dto.setPassword(dto.getPassword() != null ? passwordEncoder.encode(dto.getPassword()) : null);

        User user = this.userConverter.updateUser(dto, userEntity);

        User updated = this.userRepository.save(user);
        return userConverter.toResponse(updated);
    }
}
