package com.example.contacts.api.factories;

import com.example.contacts.api.dto.AddressDto;
import com.example.contacts.store.entities.AddressEntity;
import org.springframework.stereotype.Component;

@Component
public class AddressDtoFactory {

    public AddressDto makeAddressdto(AddressEntity addressEntity) {

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
