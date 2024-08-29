package com.javanauta.notifications.core.config;

import com.javanauta.notifications.business.interfaces.EmailSenderService;
import com.javanauta.notifications.business.usecases.SendGridEmailSenderServiceImpl;
import com.sendgrid.SendGrid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.thymeleaf.TemplateEngine;

@Configuration
@Profile("dev")
@RequiredArgsConstructor
public class DeveloperConfig {

    private final SendGrid sendGrid;
    private final TemplateEngine templateEngine;

    @Bean
    public EmailSenderService emailService() {
        return new SendGridEmailSenderServiceImpl(sendGrid, templateEngine);
    }
}
