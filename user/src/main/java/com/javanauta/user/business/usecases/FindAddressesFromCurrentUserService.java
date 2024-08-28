package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.AddressConverter;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAddressesFromCurrentUserService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;
    private final JwtUtil jwtUtil;

    public List<AddressResponseDTO> execute() {
        User user = jwtUtil.getCurrentUser();
        List<Address> addresses = this.addressRepository.findAllByUserId(user.getId());
        return addresses.stream().map(addressConverter::toResponse).toList();
    }
}
