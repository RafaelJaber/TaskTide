package com.javanauta.notifications.business.interfaces;

import com.javanauta.notifications.business.dto.request.EmailSenderRequestDTO;

public interface EmailSenderService {
    void execute(EmailSenderRequestDTO dto);

}
