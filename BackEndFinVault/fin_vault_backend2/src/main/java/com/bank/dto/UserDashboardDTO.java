package com.bank.dto;

import com.bank.model.Account;
import com.bank.model.Transaction;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDashboardDTO {
    
    private List<Account> accounts; // User account details
    private List<Transaction> transactions; // List of transactions
    private Map<String, BigDecimal> weeklyActivity; // Transaction summary per day
    private Map<String, BigDecimal> balanceHistory; // Balance trend for last few months
    private Map<String, Double> transactionTypePercentage; // Added

    

    public Map<String, Double> getTransactionTypePercentage() {
        return transactionTypePercentage;
    }
}
