package com.example.contacts.store.repositories;

import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.entities.ContactInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ContactInfoRepository extends JpaRepository<ContactInfoEntity, Integer> {

}
