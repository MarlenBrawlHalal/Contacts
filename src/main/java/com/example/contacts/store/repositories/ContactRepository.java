package com.example.contacts.store.repositories;

import com.example.contacts.store.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    Optional<ContactEntity> findByBin(String bin);
}
