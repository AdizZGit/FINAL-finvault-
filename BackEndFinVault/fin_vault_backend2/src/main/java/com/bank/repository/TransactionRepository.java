package com.bank.repository;

import com.bank.model.Transaction;
import com.bank.model.TransactionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    // Find all transactions for a specific account
    List<Transaction> findByAccount_AccountNumber(String accountNumber);

    // Find a transaction by its reference ID (for querying a specific transaction)
    Optional<Transaction> findByReferenceId(String referenceId);

    // Find transactions by type (DEPOSIT, WITHDRAW, TRANSFER)
    List<Transaction> findByTransactionType(TransactionType transactionType);

    // Find all transactions by status (PENDING, SUCCESS, FAILED)
    List<Transaction> findByTransactionStatus(String transactionStatus);
    
    List<Transaction> findByCreatedAtBetween(LocalDateTime start, LocalDateTime end);

}
