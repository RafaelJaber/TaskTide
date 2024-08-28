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

    public Contact updateContact(ContactRequestDTO dto, Contact entity) {
        return Contact.builder()
                .id(entity.getId())
                .areaCode(dto.getAreaCode() != null ? dto.getAreaCode() : entity.getAreaCode())
                .phoneNumber(dto.getPhoneNumber() != null ? dto.getPhoneNumber() : entity.getPhoneNumber())
                .type(dto.getType() != null ? dto.getType() : entity.getType())
                .isWhatsapp(dto.getIsWhatsapp() != null ? dto.getIsWhatsapp() : entity.getIsWhatsapp())
                .user(entity.getUser())
                .build();
    }
}
