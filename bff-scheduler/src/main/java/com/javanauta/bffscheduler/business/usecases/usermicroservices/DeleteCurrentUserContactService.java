package com.javanauta.bffscheduler.business.usecases.usermicroservices;


import com.javanauta.bffscheduler.infrastructure.client.UserContactClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCurrentUserContactService {

    private final UserContactClient client;

    public Void execute(Long contactId, String token) {
        return client.deleteContact(contactId, token);
    }
}
