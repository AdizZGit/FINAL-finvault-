package com.bank.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountnumber", referencedColumnName = "accountnumber", nullable = false)
    @JsonIgnore
    private Account account; // Link to Account table (sender or account performing transaction)

//
//    @Column(name = "account_number", nullable = false)
//    private String accountNumber; // User's account number

    @Column(name = "receiver_account_number")
    private String receiverAccountNumber; // Only for transfers

    @Column(nullable = false)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionType transactionType; // DEPOSIT, WITHDRAW, TRANSFER

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransactionStatus transactionStatus; // PENDING, SUCCESS, FAILED

    @Column(nullable = false)
    private LocalDateTime timestamp; // Transaction date & time

    @Column(name = "reference_id", unique = true, nullable = false)
    private String referenceId; // Unique transaction reference number
    
    @CreationTimestamp  // ✅ Auto-set at creation
    private LocalDateTime createdAt;

    @UpdateTimestamp  // ✅ Auto-set at every update
    private LocalDateTime updatedAt;
}

