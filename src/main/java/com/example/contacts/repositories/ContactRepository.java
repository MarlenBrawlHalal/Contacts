package com.example.contacts.repositories;

import com.example.contacts.entities.ContactEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ContactRepository extends JpaRepository<ContactEntity, Integer> {

    Optional<ContactEntity> findByBin(String bin);
    List<ContactEntity> findByNameStartsWithIgnoreCase(String name);
}
