package com.example.contacts.api.controllers;


import com.example.contacts.api.dto.AddressDto;
import com.example.contacts.api.factories.AddressDtoFactory;
import com.example.contacts.store.entities.AddressEntity;
import com.example.contacts.store.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class AddressController {

    private final AddressRepository addressRepository;
    private final AddressDtoFactory addressDtoFactory;

    @Autowired
    public AddressController(AddressRepository addressRepository, AddressDtoFactory addressDtoFactory) {

        this.addressRepository = addressRepository;
        this.addressDtoFactory = addressDtoFactory;
    }

    @GetMapping("/address/all")
    public List<AddressDto> getAddressList() {

        List<AddressEntity> addressEntityList = addressRepository.findAll();

        List<AddressDto> addressDtoList = new ArrayList<>();

        for(AddressEntity addressEntity : addressEntityList) {
            addressDtoList.add(addressDtoFactory.makeAddressdto(addressEntity));
        }

        return  addressDtoList;
    }

    @PostMapping("/address/add")
    public AddressDto createAddress(@RequestBody AddressEntity addressEntity) {
        addressRepository.save(addressEntity);

        return addressDtoFactory.makeAddressdto(addressEntity);
    }
}
