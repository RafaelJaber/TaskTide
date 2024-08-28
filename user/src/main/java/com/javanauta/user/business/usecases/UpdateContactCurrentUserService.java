package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.ContactConverter;
import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateContactCurrentUserService {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;

    @Transactional
    public ContactResponseDTO execute(ContactRequestDTO request, Long contactId) {
        Contact contactEntity = this.contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact with id " + contactId + " not found")
        );
        Contact contact = this.contactConverter.updateContact(request, contactEntity);

        Contact updated = this.contactRepository.save(contact);
        return this.contactConverter.toResponse(updated);
    }
}
