package com.bank.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class TransactionRequestDTO {

    @NotNull(message = "Account number cannot be null")
    private String accountNumber; // User's account number

    @NotNull(message = "Amount cannot be null")
    @Positive(message = "Amount must be positive")
    private BigDecimal amount; // The amount for deposit, withdrawal, or transfer

    @NotNull(message = "Transaction type cannot be null")
    private String transactionType; // DEPOSIT, WITHDRAW, or TRANSFER

    private String receiverAccountNumber; // Optional, used only for transfer transactions
}

