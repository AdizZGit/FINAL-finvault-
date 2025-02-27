package com.bank.service;

import com.bank.dto.ContactRequest;

public interface ContactService {
    boolean sendContactEmail(ContactRequest request);
}
