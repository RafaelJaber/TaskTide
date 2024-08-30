package com.javanauta.bffscheduler.controller;


import com.javanauta.bffscheduler.business.dto.request.ContactRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.ContactResponseDTO;
import com.javanauta.bffscheduler.business.usecases.CreateCurrentUserContactService;
import com.javanauta.bffscheduler.business.usecases.DeleteCurrentUserContactService;
import com.javanauta.bffscheduler.business.usecases.FindContactsFromCurrentUserService;
import com.javanauta.bffscheduler.business.usecases.UpdateContactCurrentUserService;
import com.javanauta.bffscheduler.infrastructure.openapi.UserContactControllerOpenAPI;
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
public class UserContactController implements UserContactControllerOpenAPI {

    private final FindContactsFromCurrentUserService findContactsFromCurrentUserService;
    private final CreateCurrentUserContactService createCurrentUserContactService;
    private final UpdateContactCurrentUserService updateContactCurrentUserService;
    private final DeleteCurrentUserContactService deleteCurrentUserContactService;

    @GetMapping
    public ResponseEntity<List<ContactResponseDTO>> findContacts(@RequestHeader("Authorization") String token) {
        List<ContactResponseDTO> result = this.findContactsFromCurrentUserService.execute(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<ContactResponseDTO> createContact(@RequestBody ContactRequestDTO request,
                                                             @RequestHeader("Authorization") String token) {
        ContactResponseDTO result = this.createCurrentUserContactService.execute(request, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{contactId}")
    public ResponseEntity<ContactResponseDTO> updateContact(
            @RequestBody ContactRequestDTO request,
            @PathVariable Long contactId,
            @RequestHeader("Authorization") String token
    ){
        ContactResponseDTO contactResponseDTO =  updateContactCurrentUserService.execute(request, contactId, token);
        return ResponseEntity.status(HttpStatus.OK).body(contactResponseDTO);
    }

    @DeleteMapping("/{contactId}")
    public ResponseEntity<Void> deleteContact(@PathVariable Long contactId,
                                              @RequestHeader("Authorization") String token) {
        deleteCurrentUserContactService.execute(contactId, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
