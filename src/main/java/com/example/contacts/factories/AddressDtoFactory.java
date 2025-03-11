package com.example.contacts.factories;

import com.example.contacts.dto.AddressDto;
import com.example.contacts.entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoFactory {

    public AddressDto makeAddressDto(AddressEntity addressEntity) {

        return AddressDto.builder()
                .id(addressEntity.getId())
                .longitude(addressEntity.getLongitude())
                .latitude(addressEntity.getLatitude())
                .street(addressEntity.getStreet())
                .buildingNumber(addressEntity.getBuildingNumber())
                .office(addressEntity.getOffice())
                .comment(addressEntity.getComment())
                .primary(addressEntity.isPrimary())
                .build();
    }
}
