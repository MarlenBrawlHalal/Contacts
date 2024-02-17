package com.example.contacts.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "contact")
public class ContactEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String bin;

    private String name;

    @Builder.Default
    private Instant createDate = Instant.now();

    @Builder.Default
    @OneToMany
    @JoinColumn(name = "contact_id", referencedColumnName = "id")
    private List<AddressEntity> addresses = new ArrayList<>();
}
