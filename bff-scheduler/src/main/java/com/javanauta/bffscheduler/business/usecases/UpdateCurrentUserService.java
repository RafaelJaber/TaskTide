package com.javanauta.bffscheduler.business.usecases;


import com.javanauta.bffscheduler.business.dto.request.UserRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.UserResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCurrentUserService {

    private final UserClient client;


    public UserResponseDTO execute(UserRequestDTO dto, String token) {
        return client.updateCurrentUserData(dto, token);
    }
}
