package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.AddressConverter;
import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreateCurrentUserAddressService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;
    private final JwtUtil jwtUtil;


    @Transactional
    public AddressResponseDTO execute(AddressRequestDTO dto) {
        User user = jwtUtil.getCurrentUser();
        Address address = this.addressConverter.toEntity(dto);
        address.setUser(user);

        Address insertedAddress = this.addressRepository.save(address);
        return this.addressConverter.toResponse(insertedAddress);
    }
}
