package com.javanauta.bffscheduler.infrastructure.client;

import com.javanauta.bffscheduler.business.dto.request.AddressRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
import com.javanauta.bffscheduler.core.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-microservice", path = "users/me/addresses", configuration = FeignConfig.class)
public interface UserAddressClient {

    @GetMapping
    List<AddressResponseDTO> findAddresses(@RequestHeader("Authorization") String token);

    @PostMapping
    AddressResponseDTO createAddress(@RequestBody AddressRequestDTO request,
                                     @RequestHeader("Authorization") String token);

    @PutMapping("/{addressId}")
    AddressResponseDTO updateAddress(
            @RequestBody AddressRequestDTO request,
            @PathVariable("addressId") Long addressId,
            @RequestHeader("Authorization") String token
    );

    @DeleteMapping("/{addressId}")
    Void deleteAddress(@PathVariable("addressId") Long addressId,
                       @RequestHeader("Authorization") String token);
}