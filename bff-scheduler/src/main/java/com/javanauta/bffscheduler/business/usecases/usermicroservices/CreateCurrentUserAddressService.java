package com.javanauta.bffscheduler.business.usecases.usermicroservices;

import com.javanauta.bffscheduler.business.dto.request.AddressRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserAddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class CreateCurrentUserAddressService {

    private final UserAddressClient client;


    public AddressResponseDTO execute(AddressRequestDTO dto, String token) {
        return client.createAddress(dto, token);
    }
}
