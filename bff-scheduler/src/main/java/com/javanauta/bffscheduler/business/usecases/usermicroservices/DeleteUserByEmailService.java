package com.javanauta.bffscheduler.business.usecases.usermicroservices;

import com.javanauta.bffscheduler.infrastructure.client.UserClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteUserByEmailService {

    private final UserClient client;

    public Void execute(String email, String token) {
        return client.deleteUser(email, token);
    }
}
