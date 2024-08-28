package com.javanauta.user.business.usecases;

import com.javanauta.user.business.converter.AddressConverter;
import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UpdateAddressCurrentUserService {

    private final AddressRepository addressRepository;
    private final AddressConverter addressConverter;

    @Transactional
    public AddressResponseDTO execute(AddressRequestDTO request, Long addressId) {
        Address addressEntity = this.addressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("Address with id " + addressId + " not found")
        );
        Address address = this.addressConverter.updateAddress(request, addressEntity);

        Address updated = this.addressRepository.save(address);
        return this.addressConverter.toResponse(updated);
    }
}
