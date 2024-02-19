package com.example.contacts.entities;


import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "phone")
public class PhoneEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String phoneNumber;

    @Column(name = "\"primary\"")
    private boolean primary;

    private String comment;

    @ManyToOne
    private ContactInfoEntity contactInfo;
}
