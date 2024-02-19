package com.example.contacts.controllers;

import com.example.contacts.dto.ContactDto;
import com.example.contacts.services.ContactService;
import com.example.contacts.entities.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ContactController {

    private final ContactService contactService;

    @Autowired
    public ContactController(ContactService contactService) {
         this.contactService = contactService;
    }

    @GetMapping("/contacts/all")
    public List<ContactDto> getAllContact() {

        return contactService.getAllContact();
    }

    @GetMapping("/contacts/{contact_id}")
    public ContactDto getContact(@PathVariable("contact_id") int contact_id) {

        return contactService.getContact(contact_id);
    }

    @GetMapping("/contacts/search")
    public List<ContactDto> searchContactByName(@RequestParam("prefix") String prefix) {

        return contactService.searchContactByName(prefix);
    }

    @PostMapping("/contacts")
    public ContactDto createContact(@RequestBody ContactEntity contactEntity) {

        return contactService.createContact(contactEntity);
    }

    @PatchMapping("/contacts/{contact_id}")
    public ContactDto updateContactByName(@PathVariable("contact_id") int contact_id, @RequestParam String name) {

        return contactService.updateContactByName(contact_id, name);
    }

    @DeleteMapping("/contacts/{contact_id}")
    public ResponseEntity<Void> deleteContact(@PathVariable("contact_id") int contact_id) {

        return contactService.deleteContact(contact_id);
    }
}
