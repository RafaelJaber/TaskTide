package com.javanauta.bffscheduler.business.usecases;


import com.javanauta.bffscheduler.business.dto.request.AddressRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserAddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAddressCurrentUserService {

    private final UserAddressClient client;

    public AddressResponseDTO execute(AddressRequestDTO request, Long addressId, String token) {
        return client.updateAddress(request, addressId, token);
    }
}
