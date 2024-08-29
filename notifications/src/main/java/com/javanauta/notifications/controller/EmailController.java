package com.javanauta.notifications.controller;

import com.javanauta.notifications.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.notifications.business.interfaces.EmailSenderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    private final EmailSenderService emailSenderService;

    public EmailController(EmailSenderService emailSenderService) {
        this.emailSenderService = emailSenderService;
    }


    @PostMapping("/send")
    public ResponseEntity<Void> sendEmail(@RequestBody EmailSenderRequestDTO request) {
        this.emailSenderService.execute(request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
