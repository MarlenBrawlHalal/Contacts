package com.example.contacts.api.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContactDto {

    private int id;

    private String bin;

    private String name;

    @JsonProperty("create_date")
    private Instant createDate;

    List<AddressDto> addresses;
}
