package com.example.contacts.api.controllers;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.api.exceptions.BadRequestException;
import com.example.contacts.api.exceptions.NotFoundException;
import com.example.contacts.api.factories.ContactDtoFactory;
import com.example.contacts.store.entities.AddressEntity;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.entities.ContactInfoEntity;
import com.example.contacts.store.entities.PhoneEntity;
import com.example.contacts.store.repositories.AddressRepository;
import com.example.contacts.store.repositories.ContactInfoRepository;
import com.example.contacts.store.repositories.ContactRepository;
import com.example.contacts.store.repositories.PhoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/contact/all")
    public List<ContactDto> getContactList() {

        return contactRepository
                .findAll()
                .stream()
                .map(contactDtoFactory::makeContactDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/contact/{contact_id}")
    public ContactDto getContact(@PathVariable("contact_id") int id) {

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

    @GetMapping("/contact/search")
    public List<ContactDto> searchContactByName(@RequestParam("prefix") String prefix) {

        List<ContactEntity> contactEntityList = contactRepository.findByNameStartsWithIgnoreCase(prefix);

        return contactEntityList
                .stream()
                .map(contactDtoFactory::makeContactDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/contact/add")
    public ContactDto createContact(@RequestBody ContactEntity contactEntity) {

        String bin = contactEntity.getBin();
        contactRepository.findByBin(bin)
                .ifPresent(contact -> {
                    throw new BadRequestException(String.format("Contact with bin:'%s' already exists", bin));
                });

        contactEntity = contactRepository.save(contactEntity);

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    @PatchMapping("/contact/{contact_id}")
    public ContactDto updateContact(@PathVariable("contact_id") int contact_id, @RequestParam String name) {

        if (name.trim().isEmpty()) {
            throw new BadRequestException("An empty name is prohibited");
        }

        ContactEntity contactEntity = contactRepository
                .findById(contact_id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id: '%d' doesn't exist",
                                        contact_id
                                )
                        )
                );

        contactEntity.setName(name);

        contactEntity = contactRepository.save(contactEntity);

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    @DeleteMapping("/contact/{contact_id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("contact_id") int contact_id) {

        ContactEntity contactEntity = contactRepository
                .findById(contact_id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id: '%d' doesn't exist",
                                        contact_id
                                )
                        )
                );

        contactRepository.delete(contactEntity);

        return ResponseEntity.noContent().build();
    }
}
