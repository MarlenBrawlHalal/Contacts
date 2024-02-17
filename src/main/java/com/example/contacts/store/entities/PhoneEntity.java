//package com.example.contacts.store.entities;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@Entity
//public class PhoneEntity {
//
//    @Id
//    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//    private int id;
//
//    private String phoneNumber;
//
//    private boolean primary;
//
//    private String comment;
//
//    @ManyToOne
//    @JoinColumn(name = "contact_info_id")
//    private ContactInfoEntity contactInfo;
//}
