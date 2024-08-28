package com.javanauta.user.business.converter;

import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.entities.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserConverter {

    private final AddressConverter addressConverter;
    private final ContactConverter contactConverter;

    public User toEntity(UserRequestDTO dto) {
        List<Contact> contacts = dto.getContacts().stream().map(contactConverter::toEntity).toList();
        List<Address> addresses = dto.getAddresses().stream().map(addressConverter::toEntity).toList();
        return User.builder()
                .name(dto.getName())
                .email(dto.getEmail())
                .password(dto.getPassword())
                .addresses(addresses)
                .contacts(contacts)
                .build();
    }

    public User toEntity(UserRequestDTO dto, Long id) {
        User user = this.toEntity(dto);
        user.setId(id);
        return user;
    }

    public UserResponseDTO toResponse(User user) {
        List<ContactResponseDTO> contacts = user.getContacts().stream().map(contactConverter::toResponse).toList();
        List<AddressResponseDTO> addresses = user.getAddresses().stream().map(addressConverter::toResponse).toList();
        return UserResponseDTO.builder()
                .name(user.getName())
                .email(user.getEmail())
                .addresses(addresses)
                .contacts(contacts)
                .build();
    }

    public User updateUser(UserRequestDTO dto, User entity) {
        return User.builder()
                .id(entity.getId())
                .name(dto.getName() != null ? dto.getName() : entity.getName())
                .password(dto.getPassword() != null ? dto.getPassword() : entity.getPassword())
                .email(dto.getEmail() != null ? dto.getEmail() : entity.getEmail())
                .addresses(entity.getAddresses())
                .contacts(entity.getContacts())
                .build();
    }
}
