package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.ContactConverter;
import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCurrentUserContactService {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;
    private final JwtUtil jwtUtil;


    @Transactional
    public ContactResponseDTO execute(ContactRequestDTO dto) {
        User user = jwtUtil.getCurrentUser();
        Contact contact = this.contactConverter.toEntity(dto);
        contact.setUser(user);

        Contact insertedContact = this.contactRepository.save(contact);
        return this.contactConverter.toResponse(insertedContact);
    }
}
