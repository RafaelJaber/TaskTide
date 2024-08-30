package com.javanauta.bffscheduler.business.usecases.usermicroservices;


import com.javanauta.bffscheduler.business.dto.request.ContactRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserContactClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateContactCurrentUserService {

    private final UserContactClient client;

    public ContactResponseDTO execute(ContactRequestDTO request, Long contactId, String token) {
        return client.updateContact(request, contactId, token);
    }
}
