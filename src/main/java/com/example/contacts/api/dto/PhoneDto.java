package com.example.contacts.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDto {

    private int id;

    private String phoneNumber;

    private boolean primary;

    private String comment;
}
