package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.api.exceptions.BadRequestException;
import com.example.contacts.api.factories.ContactDtoFactory;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("/contacts/all")
    public List<ContactDto> contactDtoList() {

        List<ContactEntity> contactEntityList = contactRepository.findAll();
        List<ContactDto> contactDtoList = new ArrayList<>();

        for(ContactEntity contactEntity : contactEntityList) {
            contactDtoList.add(contactDtoFactory.makeContactDTO(contactEntity));
        }

        return contactDtoList;
    }

    @GetMapping("/contacts/{id}")
    public ContactDto contactDto (@PathVariable("id") int id) {

        Optional<ContactEntity> contactEntity = contactRepository.findById(id);

        if (contactEntity.isEmpty())
            throw new BadRequestException(String.format("Contact with id:'%d' doesn't exist", id));

        return contactDtoFactory.makeContactDTO(contactEntity.get());
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
