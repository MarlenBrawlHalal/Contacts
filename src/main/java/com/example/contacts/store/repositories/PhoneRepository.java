package com.example.contacts.store.repositories;

import com.example.contacts.store.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {

}
