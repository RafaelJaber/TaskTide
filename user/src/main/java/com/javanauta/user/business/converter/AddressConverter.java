package com.javanauta.user.business.converter;

import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.infrastructure.entities.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressConverter {

    public Address toEntity(AddressRequestDTO dto) {
        return Address.builder()
                .street(dto.getStreet())
                .number(dto.getNumber())
                .complement(dto.getComplement())
                .neighborhood(dto.getNeighborhood())
                .city(dto.getCity())
                .state(dto.getState())
                .zipCode(dto.getZipCode())
                .build();
    }

    public Address toEntity(AddressRequestDTO dto, Long id) {
        Address address = this.toEntity(dto);
        address.setId(id);
        return address;
    }

    public AddressResponseDTO toResponse(Address address) {
        return AddressResponseDTO.builder()
                .id(address.getId())
                .street(address.getStreet())
                .number(address.getNumber())
                .complement(address.getComplement())
                .neighborhood(address.getNeighborhood())
                .city(address.getCity())
                .state(address.getState())
                .zipCode(address.getZipCode())
                .build();
    }

    public Address updateAddress(AddressRequestDTO dto, Address entity) {
        return Address.builder()
                .id(entity.getId())
                .street(dto.getStreet() != null ? dto.getStreet() : entity.getStreet())
                .number(dto.getNumber() != null ? dto.getNumber() : entity.getNumber())
                .complement(dto.getComplement() != null ? dto.getComplement() : entity.getComplement())
                .neighborhood(dto.getNeighborhood() != null ? dto.getNeighborhood() : entity.getNeighborhood())
                .city(dto.getCity() != null ? dto.getCity() : entity.getCity())
                .state(dto.getState() != null ? dto.getState() : entity.getState())
                .zipCode(dto.getZipCode() != null ? dto.getZipCode() : entity.getZipCode())
                .user(entity.getUser())
                .build();
    }
}
