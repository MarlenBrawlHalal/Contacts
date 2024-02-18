package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.ContactInfoDto;
import com.example.contacts.api.exceptions.NotFoundException;
import com.example.contacts.api.factories.ContactInfoDtoFactory;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.entities.ContactInfoEntity;
import com.example.contacts.store.repositories.ContactInfoRepository;
import com.example.contacts.store.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ContactInfoController {

    private final ContactRepository contactRepository;
    private final ContactInfoRepository contactInfoRepository;

    private final ContactInfoDtoFactory contactInfoDtoFactory;

    @Autowired
    public ContactInfoController(ContactRepository contactRepository,
                                 ContactInfoRepository contactInfoRepository,
                                 ContactInfoDtoFactory contactInfoDtoFactory) {
        this.contactRepository = contactRepository;
        this.contactInfoRepository = contactInfoRepository;
        this.contactInfoDtoFactory = contactInfoDtoFactory;
    }

    @PostMapping("/contact/{contact_id}/contactInfo/add")
    private ContactInfoDto createContactInfo(@PathVariable("contact_id") int id,
                                             @RequestBody ContactInfoEntity contactInfoEntity) {

        ContactEntity foundContact = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id:'%d' doesn't exist",
                                        id
                                )
                        )
                );

        contactInfoEntity.setContact(foundContact);

        ContactInfoEntity savedContactInfo = contactInfoRepository.save(contactInfoEntity);

        return contactInfoDtoFactory.makeContactInfoDto(savedContactInfo);
    }
}
