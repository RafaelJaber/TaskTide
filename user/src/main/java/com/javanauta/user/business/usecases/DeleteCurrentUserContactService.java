package com.javanauta.user.business.usecases;

import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.exceptions.OperationNotPermittedException;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteCurrentUserContactService {

    private final ContactRepository contactRepository;
    private final JwtUtil jwtUtil;

    public void execute(Long contactId) {
        Contact contact = contactRepository.findById(contactId).orElseThrow(
                () -> new ResourceNotFoundException("Contact with id " + contactId + " not found")
        );
        User user = jwtUtil.getCurrentUser();
        if (!Objects.equals(contact.getUser().getId(), user.getId())) {
            throw new OperationNotPermittedException("You do not have permission to delete this contact");
        }
        contactRepository.deleteById(contactId);
    }
}
