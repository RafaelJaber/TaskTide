package com.javanauta.bffscheduler.business.usecases;


import com.javanauta.bffscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindUserByEmailService {

    private final UserClient client;

    public UserResponseDTO execute(String email) {
        return client.findUserByEmail(email);
    }
}
