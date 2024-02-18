package com.example.contacts.api.factories;

import com.example.contacts.api.dto.PhoneDto;
import com.example.contacts.store.entities.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneDtoFactory {

    public PhoneDto makePhoneDto(PhoneEntity phoneEntity){

        return PhoneDto.builder()
                .phoneNumber(phoneEntity.getPhoneNumber())
                .comment(phoneEntity.getComment())
                .primary(phoneEntity.isPrimary())
                .build();
    }
}
