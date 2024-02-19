package com.example.contacts.repositories;

import com.example.contacts.entities.PhoneEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhoneRepository extends JpaRepository<PhoneEntity, Integer> {

}
