package com.example.contacts.factories;

import com.example.contacts.dto.PhoneDto;
import com.example.contacts.entities.PhoneEntity;
import org.springframework.stereotype.Component;

@Component
public class PhoneDtoFactory {

    public PhoneDto makePhoneDto(PhoneEntity phoneEntity){

        return PhoneDto.builder()
                .id(phoneEntity.getId())
                .phoneNumber(phoneEntity.getPhoneNumber())
                .comment(phoneEntity.getComment())
                .primary(phoneEntity.isPrimary())
                .build();
    }
}
