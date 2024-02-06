package com.example.contacts.store.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

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
    @Column(name = "id")
    private int id;

    @Column(name = "bin", unique = true)
    private String bin;

    @Column(name = "name")
    private String name;

    @Builder.Default
    @Column(name = "create_date")
    private Instant createDate = Instant.now();
}
