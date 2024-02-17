//package com.example.contacts.store.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//import java.util.List;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class ContactInfoEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private int id;
//
//    private String email;
//
//    @OneToMany(mappedBy = "contactInfo", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<PhoneEntity> phones;
//
//    @ManyToOne
//    @JoinColumn(name = "contact_id")
//    private ContactEntity contact;
//}
