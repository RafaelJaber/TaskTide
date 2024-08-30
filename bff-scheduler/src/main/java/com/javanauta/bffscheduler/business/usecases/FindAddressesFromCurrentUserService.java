package com.javanauta.bffscheduler.business.usecases;


import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
import com.javanauta.bffscheduler.infrastructure.client.UserAddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FindAddressesFromCurrentUserService {

    private final UserAddressClient client;

    public List<AddressResponseDTO> execute(String token) {
        return client.findAddresses(token);
    }
}
