package com.javanauta.user.infrastructure.repositories;

import com.javanauta.user.infrastructure.entities.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
}
