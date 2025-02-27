package com.bank.controller;

import com.bank.model.Account;
import com.bank.model.AccountRequest;
import com.bank.service.AccountRequestService;
import com.bank.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/accounts")
@CrossOrigin(origins = "http://localhost:3000")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRequestService accountRequestService;

    // Get all pending account requests
    @GetMapping("/requests")
    public ResponseEntity<List<AccountRequest>> getAllRequests() {
        return ResponseEntity.ok(accountRequestService.getAllAccountRequests());
    }

    // Create account from request (Only ACCOUNT_MANAGER)
    @PostMapping("/create/{requestId}")
    public ResponseEntity<Account> createAccount(@PathVariable Long requestId) {
        Account newAccount = accountService.createAccountFromRequest(requestId);
        return ResponseEntity.ok(newAccount);
    }

    // Get all accounts
    @GetMapping
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // Get account by ID
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }

    // Update account details
    @PutMapping("/{id}")
    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account updatedAccount) {
        Account account = accountService.updateAccount(id, updatedAccount);
        return ResponseEntity.ok(account);
    }

    // Delete account
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
        accountService.deleteAccount(id);
        return ResponseEntity.ok("Account deleted successfully.");
    }
}

//
//@RestController
//@RequestMapping("/UserAccounts")
//@CrossOrigin(origins = "http://localhost:3000")
//public class AccountController {
//
//    @Autowired
//    private AccountService accountService;
//
//    // Get all accounts
//    @GetMapping
//    public List<Account> getAllAccounts() {
//        return accountService.getAllAccounts();
//    }
//
//    // Get account by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Account> getAccountById(@PathVariable Long id) {
//        Optional<Account> account = accountService.getAccountById(id);
//        return account.map(ResponseEntity::ok)
//                      .orElse(ResponseEntity.notFound().build());
//    }
//
//    // Create a new account
//    @PostMapping
//    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
//        Account createdAccount = accountService.createAccount(account);
//        return ResponseEntity.ok(createdAccount);
//    }
//
//    // Update an existing account
//    @PutMapping("/{id}")
//    public ResponseEntity<Account> updateAccount(@PathVariable Long id, @RequestBody Account account) {
//        try {
//            Account updatedAccount = accountService.updateAccount(id, account);
//            return ResponseEntity.ok(updatedAccount);
//        } catch (RuntimeException e) {
//            return ResponseEntity.notFound().build();
//        }
//    }
//
//    // Delete an account
//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteAccount(@PathVariable Long id) {
//        accountService.deleteAccount(id);
//        return ResponseEntity.ok("Account deleted successfully");
//    }
//}
