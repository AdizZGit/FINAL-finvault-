package com.bank.service;

import com.bank.dto.ContactRequest;
import com.bank.model.ContactMessage;
import com.bank.repository.ContactRepository;
import com.bank.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public boolean sendContactEmail(ContactRequest request) {
        // Save message in database
        ContactMessage contactMessage = new ContactMessage(
                request.getName(),
                request.getEmail(),
                request.getSubject(),
                request.getMessage()
        );
        contactRepository.save(contactMessage);

        // Send email notification to admin
        String subject = "New Contact Us Message: " + request.getSubject();
        String body = "Name: " + request.getName() + "\n" +
                      "Email: " + request.getEmail() + "\n\n" +
                      "Message:\n" + request.getMessage();

        return emailService.sendEmail("admin@bank.com", subject, body);
    }
}
