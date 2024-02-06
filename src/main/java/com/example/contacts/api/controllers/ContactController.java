package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.api.exceptions.BadRequestException;
import com.example.contacts.api.exceptions.NotFoundException;
import com.example.contacts.api.factories.ContactDtoFactory;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<ContactDto> getContactList() {

        List<ContactEntity> contactEntityList = contactRepository.findAll();
        List<ContactDto> contactDtoList = new ArrayList<>();

        for(ContactEntity contactEntity : contactEntityList) {
            contactDtoList.add(contactDtoFactory.makeContactDto(contactEntity));
        }

        return contactDtoList;
    }

    @GetMapping("/contacts/{id}")
    public ContactDto getContact(@PathVariable("id") int id) {

        ContactEntity contactEntity = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id:'%d' doesn't exist",
                                        id
                                )
                        )
                );

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    @PostMapping("/contacts/add")
    public ContactDto createContact(@RequestParam String bin, @RequestParam String name) {

        contactRepository.findByBin(bin)
                .ifPresent(contact -> {
                    throw new BadRequestException(String.format("Contact with bin:'%s' already exists", bin));
                });

        ContactEntity savedContact = contactRepository.save(
                ContactEntity.builder()
                        .bin(bin)
                        .name(name)
                        .build()
        );

        return contactDtoFactory.makeContactDto(savedContact);
    }

    @PatchMapping("/contacts/{id}")
    public ContactDto updateContact(@PathVariable("id") int id, @RequestParam String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("An empty name is prohibited");
        }

        ContactEntity contactEntity = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id: '%d' doesn't exist",
                                        id
                                )
                        )
                );

        contactEntity.setName(name);

        contactEntity = contactRepository.save(contactEntity);

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    @DeleteMapping("/contacts/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("id") int id) {

        ContactEntity contactEntity = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id: '%d' doesn't exist",
                                        id
                                )
                        )
                );

        contactRepository.delete(contactEntity);

        return ResponseEntity.noContent().build();
    }
}
