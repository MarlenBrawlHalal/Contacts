package com.example.contacts.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private int id;

    private String bin;

    private String name;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private List<AddressDto> addresses;

    private List<ContactInfoDto> contactInfo;
}
