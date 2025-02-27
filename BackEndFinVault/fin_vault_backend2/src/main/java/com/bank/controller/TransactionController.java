package com.bank.controller;

import com.bank.dto.TransactionRequestDTO;
import com.bank.dto.TransactionResponseDTO;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.service.TransactionService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;
    
 // Get transactions by account number
    @GetMapping("/account/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by type (DEPOSIT, WITHDRAW, TRANSFER)
    @GetMapping("/type/{transactionType}")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable TransactionType transactionType) {
        List<Transaction> transactions = transactionService.getTransactionsByType(transactionType);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by date (Format: YYYY-MM-DD)
    @GetMapping("/date/{date}")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Transaction> transactions = transactionService.getTransactionsByDate(localDate);
        return ResponseEntity.ok(transactions);
    }

//    // Endpoint for depositing money
//    @PostMapping("/deposit")
//    public ResponseEntity<TransactionResponseDTO> deposit(@RequestBody TransactionRequestDTO requestDTO) {
//        try {
//            TransactionResponseDTO responseDTO = transactionService.deposit(requestDTO);
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Handle error gracefully
//        }
//    }
 // Endpoint for depositing money
    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody TransactionRequestDTO requestDTO) {
        try {
            TransactionResponseDTO responseDTO = transactionService.deposit(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Send error message as response
        }
    }

//    // Endpoint for withdrawing money
//    @PostMapping("/withdraw")
//    public ResponseEntity<TransactionResponseDTO> withdraw(@RequestBody TransactionRequestDTO requestDTO) {
//        try {
//            TransactionResponseDTO responseDTO = transactionService.withdraw(requestDTO);
//            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//        } catch (RuntimeException e) {
//            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Handle error gracefully
//        }
//    }
 // Endpoint for withdrawing money
    @PostMapping("/withdraw")
    public ResponseEntity<?> withdraw(@RequestBody TransactionRequestDTO requestDTO) {
        try {
            TransactionResponseDTO responseDTO = transactionService.withdraw(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Send error message as response
        }
    }

    // Endpoint for transferring money
    @PostMapping("/transfer")
    public ResponseEntity<?> transfer(@RequestBody TransactionRequestDTO requestDTO) {
        try {
            TransactionResponseDTO responseDTO = transactionService.transfer(requestDTO);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); // Handle error gracefully
        }
    }
}

