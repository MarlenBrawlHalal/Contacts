package com.example.contacts.dto;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {

    private int id;

    private float longitude;

    private float latitude;

    private String street;

    private String buildingNumber;

    private String office;

    private String comment;

    private boolean primary;
}
