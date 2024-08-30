package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.EmailSenderRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications-microservice", path = "/email")
public interface EmailClient {

    @PostMapping("/send")
    Void sendEmail(@RequestBody EmailSenderRequestDTO request);
}
