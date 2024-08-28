package com.javanauta.user.controllers;

import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.business.usecases.CreateCurrentUserAddressService;
import com.javanauta.user.business.usecases.DeleteCurrentUserAddressService;
import com.javanauta.user.business.usecases.FindAddressesFromCurrentUserService;
import com.javanauta.user.business.usecases.UpdateAddressCurrentUserService;
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
public class UserAddressController {

    private final FindAddressesFromCurrentUserService findAddressesFromCurrentUserService;
    private final CreateCurrentUserAddressService createCurrentUserAddressService;
    private final UpdateAddressCurrentUserService updateAddressCurrentUserService;
    private final DeleteCurrentUserAddressService deleteCurrentUserAddressService;


    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAddresses() {
        List<AddressResponseDTO> result = this.findAddressesFromCurrentUserService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping
    public ResponseEntity<AddressResponseDTO> createAddress(@RequestBody AddressRequestDTO request) {
        AddressResponseDTO result = this.createCurrentUserAddressService.execute(request);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
        return ResponseEntity.created(uri).body(result);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @RequestBody AddressRequestDTO request,
            @PathVariable Long addressId
    ){
        AddressResponseDTO addressResponseDTO =  updateAddressCurrentUserService.execute(request, addressId);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO);
    }

    @DeleteMapping("/{addressId}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long addressId) {
        deleteCurrentUserAddressService.execute(addressId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
