package com.javanauta.user.controllers;

import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.business.usecases.CreateCurrentUserContactService;
import com.javanauta.user.business.usecases.DeleteCurrentUserContactService;
import com.javanauta.user.business.usecases.FindContactsFromCurrentUserService;
import com.javanauta.user.business.usecases.UpdateContactCurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/me/contacts")
public class UserContactController {

    private final FindContactsFromCurrentUserService findContactsFromCurrentUserService;
    private final CreateCurrentUserContactService createCurrentUserContactService;
    private final UpdateContactCurrentUserService updateContactCurrentUserService;
    private final DeleteCurrentUserContactService deleteCurrentUserContactService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findAddresses() {
        List<ContactResponseDTO> result = this.findContactsFromCurrentUserService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createAddress(@RequestBody ContactRequestDTO request) {
        ContactResponseDTO result = this.createCurrentUserContactService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @RequestBody ContactRequestDTO request,
            @PathVariable Long contactId
    ){
        ContactResponseDTO contactResponseDTO =  updateContactCurrentUserService.execute(request, contactId);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDTO);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId) {
        deleteCurrentUserContactService.execute(contactId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
