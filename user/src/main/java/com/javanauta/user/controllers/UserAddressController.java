package com.javanauta.user.controllers;

import com.javanauta.user.business.dto.request.AddressRequestDTO;
import com.javanauta.user.business.dto.response.AddressResponseDTO;
import com.javanauta.user.business.usecases.FindAddressesFromCurrentUserService;
import com.javanauta.user.business.usecases.UpdateAddressCurrentUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("users/me/addresses")
public class UserAddressController {

    private final UpdateAddressCurrentUserService updateAddressCurrentUserService;
    private final FindAddressesFromCurrentUserService findAddressesFromCurrentUserService;


    @GetMapping
    public ResponseEntity<List<AddressResponseDTO>> findAddresses() {
        List<AddressResponseDTO> result = this.findAddressesFromCurrentUserService.execute();
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/{addressId}")
    public ResponseEntity<AddressResponseDTO> updateAddress(
            @RequestBody AddressRequestDTO request,
            @PathVariable Long addressId
    ){
        AddressResponseDTO addressResponseDTO =  updateAddressCurrentUserService.execute(request, addressId);
        return ResponseEntity.status(HttpStatus.OK).body(addressResponseDTO);
    }
}
