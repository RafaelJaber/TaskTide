package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.EmailSenderRequestDTO;
import com.javanauta.bffscheduler.core.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "notifications-microservice", path = "/email", configuration = FeignConfig.class)
public interface EmailClient {

    @PostMapping("/send")
    Void sendEmail(@RequestBody EmailSenderRequestDTO request);
}
