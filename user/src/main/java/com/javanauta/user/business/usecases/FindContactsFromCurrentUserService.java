package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.ContactConverter;
import com.javanauta.user.business.dto.response.ContactResponseDTO;
import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Contact;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.repositories.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindContactsFromCurrentUserService {

    private final ContactRepository contactRepository;
    private final ContactConverter contactConverter;
    private final JwtUtil jwtUtil;

    @Transactional(readOnly = true)
    public List<ContactResponseDTO> execute() {
        User user = jwtUtil.getCurrentUser();
        List<Contact> addresses = this.contactRepository.findAllByUserId(user.getId());
        return addresses.stream().map(contactConverter::toResponse).toList();
    }
}
