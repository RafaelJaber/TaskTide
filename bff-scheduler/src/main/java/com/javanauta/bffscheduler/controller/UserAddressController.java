package com.javanauta.bffscheduler.controller;


import com.javanauta.bffscheduler.business.dto.request.AddressRequestDTO;
import com.javanauta.bffscheduler.business.dto.response.AddressResponseDTO;
import com.javanauta.bffscheduler.business.usecases.usermicroservices.CreateCurrentUserAddressService;
import com.javanauta.bffscheduler.business.usecases.usermicroservices.DeleteCurrentUserAddressService;
import com.javanauta.bffscheduler.business.usecases.usermicroservices.FindAddressesFromCurrentUserService;
import com.javanauta.bffscheduler.business.usecases.usermicroservices.UpdateAddressCurrentUserService;
import com.javanauta.bffscheduler.infrastructure.openapi.UserAddressControllerOpenAPI;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/me/addresses")
public class UserAddressController implements UserAddressControllerOpenAPI {

    private final FindAddressesFromCurrentUserService findAddressesFromCurrentUserService;
    private final CreateCurrentUserAddressService createCurrentUserAddressService;
    private final UpdateAddressCurrentUserService updateAddressCurrentUserService;
    private final DeleteCurrentUserAddressService deleteCurrentUserAddressService;


    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAddresses(@RequestHeader("Authorization") String token) {
        List<AddressResponseDTO> result = this.findAddressesFromCurrentUserService.execute(token);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO request,
                                                            @RequestHeader("Authorization") String token) {
        AddressResponseDTO result = this.createCurrentUserAddressService.execute(request, token);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @RequestBody AddressRequestDTO request,
            @PathVariable Long addressId,
            @RequestHeader("Authorization") String token
    ){
        AddressResponseDTO addressResponseDTO =  updateAddressCurrentUserService.execute(request, addressId, token);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId,
                                              @RequestHeader("Authorization") String token) {
        deleteCurrentUserAddressService.execute(addressId, token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
