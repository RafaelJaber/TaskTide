package com.javanauta.user.business.usecases;

import com.javanauta.user.core.utils.JwtUtil;
import com.javanauta.user.infrastructure.entities.Address;
import com.javanauta.user.infrastructure.entities.User;
import com.javanauta.user.infrastructure.exceptions.OperationNotPermittedException;
import com.javanauta.user.infrastructure.exceptions.ResourceNotFoundException;
import com.javanauta.user.infrastructure.repositories.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class DeleteCurrentUserAddressService {

    private final AddressRepository addressRepository;
    private final JwtUtil jwtUtil;

    @Transactional
    public void execute(Long addressId) {
        Address address = addressRepository.findById(addressId).orElseThrow(
                () -> new ResourceNotFoundException("Address with id " + addressId + " not found")
        );
        User user = jwtUtil.getCurrentUser();
        if (!Objects.equals(address.getUser().getId(), user.getId())) {
            throw new OperationNotPermittedException("You do not have permission to delete this address");
        }
        addressRepository.deleteById(addressId);
    }
}
