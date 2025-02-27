package com.bank.controller;

import com.bank.dto.ContactRequest;
import com.bank.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contact")
@CrossOrigin(origins = "http://localhost:3000")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/send")
    public ResponseEntity<String> sendContactEmail(@Valid @RequestBody ContactRequest request) {
        boolean isSent = contactService.sendContactEmail(request);
        return isSent
                ? ResponseEntity.ok("Contact message sent and saved successfully!")
                : ResponseEntity.status(500).body("Failed to send contact message.");
    }
}
