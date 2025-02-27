package com.bank.service;

import com.bank.dto.TransactionRequestDTO;
import com.bank.dto.TransactionResponseDTO;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface TransactionService {

    TransactionResponseDTO deposit(TransactionRequestDTO requestDTO);

    TransactionResponseDTO withdraw(TransactionRequestDTO requestDTO);

    TransactionResponseDTO transfer(TransactionRequestDTO requestDTO);
    
    // Method to validate balance before withdrawal or transfer
    boolean isBalanceSufficient(String accountNumber, BigDecimal amount);
 
    // Method to retrieve transactions by account number
    List<Transaction> getTransactionsByAccountNumber(String accountNumber);

    // Method to retrieve transactions by transaction type
    List<Transaction> getTransactionsByType(TransactionType transactionType);

    // Method to retrieve transactions by date
    List<Transaction> getTransactionsByDate(LocalDate date);
}

