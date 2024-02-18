package com.example.contacts.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact_information")
public class ContactInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String email;

    @ManyToOne
    ContactEntity contact;

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "contact_information_id", referencedColumnName = "id")
    private List<PhoneEntity> phones = new ArrayList<>();
}
