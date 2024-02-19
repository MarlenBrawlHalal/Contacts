package com.example.contacts.factories;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.entities.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContactDtoFactory {

    private final AddressDtoFactory addressDtoFactory;
    private final ContactInfoDtoFactory contactInfoDtoFactory;

    @Autowired
    public ContactDtoFactory(AddressDtoFactory addressDtoFactory, ContactInfoDtoFactory contactInfoDtoFactory) {

        this.addressDtoFactory = addressDtoFactory;
        this.contactInfoDtoFactory = contactInfoDtoFactory;
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
                            .collect(Collectors.toList())
                )
                .contactInfo(
                        contactEntity
                                .getContactInfo()
                                .stream()
                                .map(contactInfoDtoFactory::makeContactInfoDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
