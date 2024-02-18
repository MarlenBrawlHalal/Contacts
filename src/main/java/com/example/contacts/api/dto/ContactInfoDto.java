package com.example.contacts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactInfoDto {

    private int id;

    private String email;

    private List<PhoneDto> phones;
}
