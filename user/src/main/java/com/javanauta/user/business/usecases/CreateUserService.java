package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.AddressConverter;
import com.javanauta.user.business.converter.ContactConverter;
import com.javanauta.user.business.converter.UserConverter;
import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.request.ContactRequestDTO;
import com.javanauta.user.business.dto.request.UserRequestDTO;
import com.javanauta.user.business.dto.response.UserResponseDTO;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.exceptions.ConflictException;
import com.javanauta.user.infrastructure.repositories.AddressRepository;
import com.javanauta.user.infrastructure.repositories.ContactRepository;
import com.javanauta.user.infrastructure.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CreateUserService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final ContactRepository contactRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserConverter userConverter;
    private final AddressConverter addressConverter;
    private final ContactConverter contactConverter;


    @Transactional
    public UserResponseDTO execute(UserRequestDTO dto) {
        this.existsByEmail(dto.getEmail());
        User user = userConverter.toEntity(dto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);

        List<Address> addresses = convertAndAssignUserAddress(dto.getAddresses(), user);
        List<Contact> contacts = convertAndAssignUserContacts(dto.getContacts(), user);

        addressRepository.saveAll(addresses);
        contactRepository.saveAll(contacts);

        return userConverter.toResponse(user);
    }

    private List<Address> convertAndAssignUserAddress(List<AddressRequestDTO> addressRequestDTOList, User user) {
        return addressRequestDTOList.stream()
                .map(dto -> {
                    Address address = addressConverter.toEntity(dto);
                    address.setUser(user);
                    return address;
                })
                .toList();
    }

    private List<Contact> convertAndAssignUserContacts(List<ContactRequestDTO> contactRequestDTOList, User user) {
        return contactRequestDTOList.stream()
                .map(dto -> {
                    Contact contact = contactConverter.toEntity(dto);
                    contact.setUser(user);
                    return contact;
                })
                .toList();
    }

    private void existsByEmail(String email) {
        try {
            boolean exists = userRepository.existsByEmail(email);
            if (exists) {
                throw new ConflictException("User with email " + email + " already exists");
            }
        } catch (ConflictException e) {
            throw new ConflictException("User with email " + email + " already exists", e.getCause());
        }
    }
}
