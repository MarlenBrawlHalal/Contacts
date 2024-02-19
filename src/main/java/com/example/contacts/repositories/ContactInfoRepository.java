package com.example.contacts.repositories;

import com.example.contacts.entities.ContactInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ContactInfoRepository extends JpaRepository<ContactInfoEntity, Integer> {

}
