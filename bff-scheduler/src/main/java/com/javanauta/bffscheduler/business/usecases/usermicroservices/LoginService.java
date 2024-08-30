package com.javanauta.bffscheduler.business.usecases.usermicroservices;

import com.javanauta.bffscheduler.business.dto.request.LoginRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.LoginResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserClient client;

    public LoginResponseDTO execute(LoginRequestDTO dto) {
        return client.login(dto);
    }
}
