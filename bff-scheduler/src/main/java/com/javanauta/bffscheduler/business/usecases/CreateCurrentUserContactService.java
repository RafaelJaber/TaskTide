package com.javanauta.bffscheduler.business.usecases;


import com.javanauta.bffscheduler.business.dto.request.ContactRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserContactClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCurrentUserContactService {

    private final UserContactClient client;


    public ContactResponseDTO execute(ContactRequestDTO dto, String token) {
        return client.createContact(dto, token);
    }
}
