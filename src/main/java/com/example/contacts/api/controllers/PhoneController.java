package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.PhoneDto;
import com.example.contacts.api.exceptions.NotFoundException;
import com.example.contacts.api.factories.PhoneDtoFactory;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.entities.ContactInfoEntity;
import com.example.contacts.store.entities.PhoneEntity;
import com.example.contacts.store.repositories.ContactRepository;
import com.example.contacts.store.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class PhoneController {

    private final ContactRepository contactRepository;
    private final PhoneRepository phoneRepository;

    private final PhoneDtoFactory phoneDtoFactory;

    @Autowired
    public PhoneController(ContactRepository contactRepository, PhoneRepository phoneRepository, PhoneDtoFactory phoneDtoFactory) {
        this.contactRepository = contactRepository;
        this.phoneRepository = phoneRepository;
        this.phoneDtoFactory = phoneDtoFactory;
    }

    @PostMapping("/contact/{contact_id}/contactInfo/{contactInfo_id}/add")
    private PhoneDto createPhone(@PathVariable("contact_id") int contact_id,
                                 @PathVariable("contactInfo_id") int contactInfo_id,
                                 @RequestBody PhoneEntity phone) {

        ContactEntity foundContact = contactRepository
                .findById(contact_id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id:'%d' doesn't exist",
                                        contact_id
                                )
                        )
                );

        ContactInfoEntity foundContactInfo = foundContact
                .getContactInformation()
                .stream()
                .filter(info -> info.getId() == contactInfo_id)
                .findFirst()
                .orElseThrow(() ->
                                new NotFoundException(
                                        String.format(
                                                "ContactInfo with id:'%d' doesn't exist",
                                                contactInfo_id
                                        )
                                )
                        );

        phone.setContactInformation(foundContactInfo);

        PhoneEntity savedPhone = phoneRepository.save(phone);

        return phoneDtoFactory.makePhoneDto(savedPhone);
    }
}
