package com.javanauta.notifications.business.usecases;

import com.javanauta.notifications.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.notifications.business.interfaces.EmailSenderService;
import com.javanauta.notifications.infrastructure.exceptions.SendEmailException;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
public class SendGridEmailSenderServiceImpl implements EmailSenderService {

    private final Logger LOG = LoggerFactory.getLogger(SendGridEmailSenderServiceImpl.class);
    private final SendGrid sendGrid;
    private final TemplateEngine templateEngine;

    @Value("${task-tide.email-sender.from}")
    private String from;

    @Value("${task-tide.email-sender.friendly-name}")
    private String friendlyName;

    private final String contentType = "text/html";
    private final String subject = "Task Tile - Notifications";
    private DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public void execute(EmailSenderRequestDTO dto) {
        Email from = new Email(this.from, this.friendlyName);
        Email to = new Email(dto.getUserEmail());

        Context context = new Context();
        context.setVariable("taskName", dto.getTaskName());
        context.setVariable("schedulingDate", dto.getSchedulingDate().format(this.dateTimeFormatter));
        context.setVariable("description", dto.getDescription());
        Content content = new Content(this.contentType, templateEngine.process("notification", context));

        Mail mail = new Mail(from, this.subject, to, content);

        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            LOG.info("Sending email to: {}", dto.getUserEmail());
            Response response = sendGrid.api(request);
            if (response.getStatusCode() >= 400) {
                LOG.error("Send email failed: {}", response.getBody());
            } else {
                LOG.info("Send email successful! Status: {}", response.getStatusCode());
            }

        } catch (IOException ex) {
            LOG.error("Send email is failed: {}", ex.getMessage());
            throw new SendEmailException("Failed to send email: " + ex.getMessage());
        }
    }
}
