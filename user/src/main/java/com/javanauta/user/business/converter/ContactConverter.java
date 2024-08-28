package com.javanauta.user.business.converter;

import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.infrastructure.entities.Contact;
import org.springframework.stereotype.Component;

@Component
public class ContactConverter {

    public Contact toEntity(ContactRequestDTO dto) {
        return Contact.builder()
                .areaCode(dto.getAreaCode())
                .phoneNumber(dto.getPhoneNumber())
                .type(dto.getType())
                .isWhatsapp(dto.getIsWhatsapp())
                .build();
    }

    public Contact toEntity(ContactRequestDTO dto, Long id) {
        Contact contact = this.toEntity(dto);
        contact.setId(id);
        return contact;
    }

    public ContactResponseDTO toResponse(Contact contact) {
        return ContactResponseDTO.builder()
                .id(contact.getId())
                .areaCode(contact.getAreaCode())
                .phoneNumber(contact.getPhoneNumber())
                .type(contact.getType())
                .isWhatsapp(contact.getIsWhatsapp())
                .build();
    }
}
