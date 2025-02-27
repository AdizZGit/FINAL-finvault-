package com.bank.service;

import com.bank.model.Account;
import com.bank.model.AccountRequest;
import com.bank.repository.AccountRepository;
import com.bank.repository.AccountRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import java.util.Random;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRequestRepository accountRequestRepository;

    // Generate a unique account number
    private String generateAccountNumber() {
        Random random = new Random();
        return "FIN" + (100000 + random.nextInt(900000)); // 6-digit random number prefixed with 'ACC'
    }
    private Date convertToSqlDate(java.util.Date date) {
        return new Date(date.getTime());  // ✅ Convert java.util.Date -> java.sql.Date
    }
    
    public Optional<Account> getAccountByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }


    // Create Account from Request
    public Account createAccountFromRequest(Long requestId) {
        AccountRequest request = accountRequestRepository.findById(requestId)
                .orElseThrow(() -> new IllegalArgumentException("Account request not found"));

        Account account = new Account();
        account.setFirstName(request.getFirstName());
        account.setLastName(request.getLastName());
//        account.setDateOfBirth(request.getDateOfBirth());
        account.setDateOfBirth(convertToSqlDate(request.getDateOfBirth()));
        account.setPhone(request.getPhone());
        account.setLocation(request.getLocation());
        account.setEmail(request.getEmail());
        account.setAccountType(request.getAccountType());

        account.setAccountNumber(generateAccountNumber()); // Assigning account number
        account.setBalance(0.0); // Default balance

        Account savedAccount = accountRepository.save(account);

        accountRequestRepository.deleteById(requestId); // Delete request after processing
        return savedAccount;
    }

    // Get All Accounts
    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }

    // Get Account by ID
    public Optional<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    // Update Account
    public Account updateAccount(Long id, Account updatedAccount) {
        Account existingAccount = accountRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        existingAccount.setFirstName(updatedAccount.getFirstName());
        existingAccount.setLastName(updatedAccount.getLastName());
        existingAccount.setDateOfBirth(updatedAccount.getDateOfBirth());
        existingAccount.setPhone(updatedAccount.getPhone());
        existingAccount.setLocation(updatedAccount.getLocation());
        existingAccount.setEmail(updatedAccount.getEmail());
        existingAccount.setAccountType(updatedAccount.getAccountType());
        existingAccount.setBalance(updatedAccount.getBalance());

        return accountRepository.save(existingAccount);
    }

    // Delete Account
    public void deleteAccount(Long id) {
        if (!accountRepository.existsById(id)) {
            throw new IllegalArgumentException("Account not found!");
        }
        accountRepository.deleteById(id);
    }
}
