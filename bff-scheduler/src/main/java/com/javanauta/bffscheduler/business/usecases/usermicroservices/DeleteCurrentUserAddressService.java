package com.javanauta.bffscheduler.business.usecases.usermicroservices;

import com.javanauta.bffscheduler.infrastructure.client.UserAddressClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCurrentUserAddressService {

    private final UserAddressClient client;

    public Void execute(Long addressId, String token) {
        return client.deleteAddress(addressId, token);
    }
}
