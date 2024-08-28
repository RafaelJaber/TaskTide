package com.javanauta.user.infrastructure.repositories;

import com.javanauta.user.infrastructure.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
}
