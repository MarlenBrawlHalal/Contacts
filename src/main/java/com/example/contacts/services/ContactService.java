package com.example.contacts.services;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.exceptions.BadRequestException;
import com.example.contacts.exceptions.NotFoundException;
import com.example.contacts.factories.ContactDtoFactory;
import com.example.contacts.entities.ContactEntity;
import com.example.contacts.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContactService {

    private final ContactRepository contactRepository;

    private final ContactDtoFactory contactDtoFactory;

    @Autowired
    public ContactService(ContactRepository contactRepository, ContactDtoFactory contactDtoFactory) {

        this.contactRepository = contactRepository;
        this.contactDtoFactory = contactDtoFactory;
    }

    public List<ContactDto> getAllContact() {

        return contactRepository
                .findAll()
                .stream()
                .map(contactDtoFactory::makeContactDto)
                .collect(Collectors.toList());
    }

    public ContactDto getContact(int contact_id) {

        ContactEntity contactEntity = contactRepository
                .findById(contact_id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id:'%d' doesn't exist",
                                        contact_id
                                )
                        )
                );

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    public List<ContactDto> searchContactByName(String prefix) {

        List<ContactEntity> contactEntityList = contactRepository.findByNameStartsWithIgnoreCase(prefix);

        return contactEntityList
                .stream()
                .map(contactDtoFactory::makeContactDto)
                .collect(Collectors.toList());
    }

    public ContactDto createContact(ContactEntity contactEntity) {

        String bin = contactEntity.getBin();
        contactRepository.findByBin(bin)
                .ifPresent(contact -> {
                    throw new BadRequestException(String.format("Contact with bin:'%s' already exists", bin));
                });

        contactEntity = contactRepository.save(contactEntity);

        return contactDtoFactory.makeContactDto(contactEntity);
    }

    public ContactDto updateContactByName(int contact_id, String name) {

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
