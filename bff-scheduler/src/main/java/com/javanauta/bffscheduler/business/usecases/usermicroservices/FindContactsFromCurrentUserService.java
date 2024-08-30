package com.javanauta.bffscheduler.business.usecases.usermicroservices;


import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserContactClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindContactsFromCurrentUserService {

    private final UserContactClient client;

    public List<ContactResponseDTO> execute(String token) {
        return client.findContacts(token);
    }
}
