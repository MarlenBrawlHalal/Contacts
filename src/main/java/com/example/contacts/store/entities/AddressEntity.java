package com.example.contacts.store.entities;

import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private float longitude;

    private float latitude;

    private String street;

    private String buildingNumber;

    private String office;

    private String comment;

//    @Builder.Default
//    private boolean primary = false;

    @ManyToOne
    ContactEntity contact;
}
