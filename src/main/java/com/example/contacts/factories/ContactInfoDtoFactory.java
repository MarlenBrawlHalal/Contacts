package com.example.contacts.factories;

import com.example.contacts.dto.ContactInfoDto;
import com.example.contacts.entities.ContactInfoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ContactInfoDtoFactory {

    private final PhoneDtoFactory phoneDtoFactory;

    @Autowired
    public ContactInfoDtoFactory(PhoneDtoFactory phoneDtoFactory) {
        this.phoneDtoFactory = phoneDtoFactory;
    }

    public ContactInfoDto makeContactInfoDto(ContactInfoEntity contactInfoEntity) {

        return ContactInfoDto.builder()
                .id(contactInfoEntity.getId())
                .email(contactInfoEntity.getEmail())
                .phones(
                        contactInfoEntity
                                .getPhones()
                                .stream()
                                .map(phoneDtoFactory::makePhoneDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
