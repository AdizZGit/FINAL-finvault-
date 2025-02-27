package com.bank.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bank.model.AccountRequest;
import com.bank.repository.AccountRequestRepository;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class AccountRequestService {

    @Autowired
    private AccountRequestRepository accountRequestRepository;
    
   
    // Create Account Request
    public AccountRequest createAccountRequest(AccountRequest request) {
        if (accountRequestRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Email already exists!");
        }
        return accountRequestRepository.save(request);
    }

    // Get All Requests
    public List<AccountRequest> getAllAccountRequests() {
        return accountRequestRepository.findAll();
    }

    // Get Request by ID
    public Optional<AccountRequest> getAccountRequestById(Long id) {
        return accountRequestRepository.findById(id);
    }

    
    // Delete request after processing
    public void deleteAccountRequest(Long id) {
        if (!accountRequestRepository.existsById(id)) {
            throw new IllegalArgumentException("Request not found!");
        }
        accountRequestRepository.deleteById(id);
    }
}
