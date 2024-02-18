package com.example.contacts.api.factories;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.store.entities.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContactDtoFactory {

    private final AddressDtoFactory addressDtoFactory;

    @Autowired
    public ContactDtoFactory(AddressDtoFactory addressDtoFactory) {
        this.addressDtoFactory = addressDtoFactory;
    }

    public ContactDto makeContactDto(ContactEntity contactEntity) {

        return ContactDto.builder()
                .id(contactEntity.getId())
                .bin(contactEntity.getBin())
                .name(contactEntity.getName())
                .createDate(contactEntity.getCreateDate())
                .updateDate(contactEntity.getUpdateDate())
                .addresses(
                        contactEntity
                        .getAddresses()
                        .stream()
                        .map(addressDtoFactory::makeAddressDto)
                        .collect(Collectors.toList()))
                .build();
    }
}
