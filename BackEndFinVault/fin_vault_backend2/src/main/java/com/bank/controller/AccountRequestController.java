package com.bank.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.bank.model.AccountRequest;
import com.bank.service.AccountRequestService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account-requests")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountRequestController {

    @Autowired
    private AccountRequestService accountRequestService;

    // Create Account Request
    @PostMapping
    public ResponseEntity<AccountRequest> createRequest(@RequestBody AccountRequest request) {
        AccountRequest savedRequest = accountRequestService.createAccountRequest(request);
        return ResponseEntity.ok(savedRequest);
    }

    // Get All Account Requests
    @GetMapping
    public ResponseEntity<List<AccountRequest>> getAllRequests() {
        return ResponseEntity.ok(accountRequestService.getAllAccountRequests());
    }

    // Get Account Request by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<AccountRequest>> getRequestById(@PathVariable Long id) {
        return ResponseEntity.ok(accountRequestService.getAccountRequestById(id));
    }

    // Delete Account Request
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRequest(@PathVariable Long id) {
        accountRequestService.deleteAccountRequest(id);
        return ResponseEntity.ok("Account request deleted successfully.");
    }
}
