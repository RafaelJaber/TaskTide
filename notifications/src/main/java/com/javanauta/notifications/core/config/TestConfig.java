package com.javanauta.notifications.core.config;

import com.javanauta.notifications.business.interfaces.EmailSenderService;
import com.javanauta.notifications.business.usecases.MockEmailSenderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Bean
    public EmailSenderService emailService() {
        return new MockEmailSenderServiceImpl();
    }
}
