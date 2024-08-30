package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.ContactRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
import com.javanauta.bffscheduler.core.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-microservice", path = "users/me/contacts", configuration = FeignConfig.class)
public interface UserContactClient {

    @GetMapping
    List<ContactResponseDTO> findContacts(@RequestHeader("Authorization") String token);

    @PostMapping
    ContactResponseDTO createContact(@RequestBody ContactRequestDTO request,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/{contactId}")
    ContactResponseDTO updateContact(
            @RequestBody ContactRequestDTO request,
            @PathVariable("contactId") Long contactId,
            @RequestHeader("Authorization") String token);

    @DeleteMapping("/{contactId}")
    Void deleteContact(@PathVariable("contactId") Long contactId,
                       @RequestHeader("Authorization") String token);
}