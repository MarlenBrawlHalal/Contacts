package com.example.contacts.api.factories;

import com.example.contacts.api.dto.ContactDto;
import com.example.contacts.store.entities.ContactEntity;
import org.springframework.stereotype.Component;

@Component
public class ContactDtoFactory {

    public ContactDto makeContactDto(ContactEntity contactEntity) {

        return ContactDto.builder()
                .id(contactEntity.getId())
                .bin(contactEntity.getBin())
                .name(contactEntity.getName())
                .createDate(contactEntity.getCreateDate())
                .build();
    }
}
