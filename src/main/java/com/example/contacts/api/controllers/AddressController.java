package com.example.contacts.api.controllers;


import com.example.contacts.api.dto.AddressDto;
import com.example.contacts.api.exceptions.NotFoundException;
import com.example.contacts.api.factories.AddressDtoFactory;
import com.example.contacts.store.entities.AddressEntity;
import com.example.contacts.store.entities.ContactEntity;
import com.example.contacts.store.repositories.AddressRepository;
import com.example.contacts.store.repositories.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressDtoFactory addressDtoFactory;
    private final ContactRepository contactRepository;

    @Autowired
    public AddressController(AddressRepository addressRepository, AddressDtoFactory addressDtoFactory, ContactRepository contactRepository) {

        this.addressRepository = addressRepository;
        this.addressDtoFactory = addressDtoFactory;
        this.contactRepository = contactRepository;
    }

    @GetMapping("/contact/{id}/address/all")
    public List<AddressDto> getAddressList(@PathVariable("id") int id) {

        ContactEntity contact = contactRepository
                .findById(id)
                .orElseThrow(() ->
                        new NotFoundException(
                                String.format(
                                        "Contact with id:'%d' doesn't exist",
                                        id
                                )
                        )
                );

        return contact
                .getAddresses()
                .stream()
                .map(addressDtoFactory::makeAddressDto)
                .collect(Collectors.toList());
    }

    @PostMapping("/contact/{id}/address/add")
    public AddressDto createAddress(@PathVariable("id") int id, @RequestParam String street, @RequestParam String buildingNumber) {

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

        AddressEntity address = addressRepository.save(AddressEntity.builder()
                        .street(street)
                        .buildingNumber(buildingNumber)
                        .contact(foundContact)
                        .build());

        return addressDtoFactory.makeAddressDto(address);
    }
}
