package com.javanauta.taskscheduler.infrastructure.security;


import com.javanauta.taskscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.taskscheduler.infrastructure.client.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    @Autowired
    private UserClient userClient;

    public UserDetails loadUserByUsername(String email, String token) {
        UserResponseDTO userResponseDTO = userClient.findUserByEmail(email, token);
        return User
                .withUsername(userResponseDTO.getEmail())
                .password(userResponseDTO.getEmail())
                .build();
    }
}
