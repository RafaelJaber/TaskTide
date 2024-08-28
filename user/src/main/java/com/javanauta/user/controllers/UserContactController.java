package com.javanauta.user.controllers;

import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.business.usecases.FindContactsFromCurrentUserService;
import com.javanauta.user.business.usecases.UpdateContactCurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/me/contacts")
public class UserContactController {

    private final UpdateContactCurrentUserService updateContactCurrentUserService;
    private final FindContactsFromCurrentUserService findContactsFromCurrentUserService;


    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAddresses() {
        List<ContactResponseDTO> result = this.findContactsFromCurrentUserService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @RequestBody ContactRequestDTO request,
            @PathVariable Long contactId
    ){
        ContactResponseDTO contactResponseDTO =  updateContactCurrentUserService.execute(request, contactId);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDTO);
    }
}
