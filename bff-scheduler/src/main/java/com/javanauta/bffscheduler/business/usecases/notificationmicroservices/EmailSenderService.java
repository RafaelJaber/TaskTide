package com.javanauta.bffscheduler.business.usecases.notificationmicroservices;

import com.javanauta.bffscheduler.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.bffscheduler.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailClient client;

    public Void execute(EmailSenderRequestDTO dto) {
        return client.sendEmail(dto);
    }
}
