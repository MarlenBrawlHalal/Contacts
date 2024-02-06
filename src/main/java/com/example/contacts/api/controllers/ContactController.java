package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.api.exceptions.BadRequestException;
import com.example.contacts.api.factories.ContactDtoFactory;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    private final ContactRepository contactRepository;
    private final ContactDtoFactory contactDtoFactory;

    @Autowired
    public ContactController(ContactRepository contactRepository, ContactDtoFactory contactDtoFactory) {
        this.contactRepository = contactRepository;
        this.contactDtoFactory = contactDtoFactory;
    }

    @PostMapping("/contacts/add")
    public ContactDto contactDto(@RequestParam String bin, @RequestParam String name) {

        contactRepository.findByBin(bin)
                .ifPresent(contact -> {
                    throw new BadRequestException(String.format("Contact '%s' already exists", bin));
                });

        ContactEntity savedContact = contactRepository.save(
                ContactEntity.builder()
                        .bin(bin)
                        .name(name)
                        .build()
        );

        return contactDtoFactory.makeContactDTO(savedContact);
    }
}
