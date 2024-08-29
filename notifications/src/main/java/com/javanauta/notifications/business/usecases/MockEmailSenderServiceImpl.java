package com.javanauta.notifications.business.usecases;

import com.javanauta.notifications.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.notifications.business.interfaces.EmailSenderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MockEmailSenderServiceImpl implements EmailSenderService {

    private final Logger LOG = LoggerFactory.getLogger(MockEmailSenderServiceImpl.class);

    @Override
    public void execute(EmailSenderRequestDTO dto) {
        LOG.info("Email sent to: {}", dto.getUserEmail());
    }
}
