package com.bank.controller;

import com.bank.dto.UserDashboardDTO;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.service.AccountService;
import com.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminDashboardController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/dashboard")
    public ResponseEntity<UserDashboardDTO> getUserDashboard(@RequestParam String accountNumber) {
        // Fetch account details
        Account account = accountService.getAccountByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found with account number: " + accountNumber));

        // Fetch transactions for the account
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);

        // Calculate weekly activity
        Map<String, BigDecimal> weeklyActivity = calculateWeeklyActivity(transactions);

        // Get balance history (last 5 months)
        Map<String, BigDecimal> balanceHistory = calculateBalanceHistory(account, transactions);

        // Calculate transaction type percentage
        Map<String, Double> transactionTypePercentage = calculateTransactionTypePercentage(transactions);

        // Create DTO response
        UserDashboardDTO dashboardDTO = new UserDashboardDTO(
                Collections.singletonList(account), 
                transactions, 
                weeklyActivity, 
                balanceHistory,
                transactionTypePercentage // Added transaction type percentage
        );

        return ResponseEntity.ok(dashboardDTO);
    }

    private Map<String, BigDecimal> calculateWeeklyActivity(List<Transaction> transactions) {
        Map<String, BigDecimal> weeklyActivity = new LinkedHashMap<>();
        for (int i = 6; i >= 0; i--) {
            String day = LocalDate.now().minusDays(i).getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            weeklyActivity.put(day, BigDecimal.ZERO);
        }

        for (Transaction txn : transactions) {
            String day = txn.getTimestamp().getDayOfWeek().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            weeklyActivity.put(day, weeklyActivity.get(day).add(txn.getAmount()));
        }
        return weeklyActivity;
    }

    private Map<String, BigDecimal> calculateBalanceHistory(Account account, List<Transaction> transactions) {
        Map<String, BigDecimal> balanceHistory = new LinkedHashMap<>();
        LocalDate today = LocalDate.now();

        for (int i = 4; i >= 0; i--) {
            LocalDate monthStart = today.minusMonths(i).withDayOfMonth(1);
            LocalDate monthEnd = monthStart.withDayOfMonth(monthStart.lengthOfMonth());

            // Get balance at the end of the month based on past transactions
            BigDecimal balanceAtMonthEnd = calculateBalanceAtDate(transactions, monthEnd);

            String month = monthStart.getMonth().getDisplayName(TextStyle.SHORT, Locale.ENGLISH);
            balanceHistory.put(month, balanceAtMonthEnd);
        }

        return balanceHistory;
    }

    private BigDecimal calculateBalanceAtDate(List<Transaction> transactions, LocalDate date) {
        BigDecimal balance = BigDecimal.ZERO;

        for (Transaction txn : transactions) {
            LocalDate txnDate = txn.getCreatedAt().toLocalDate();

            if (!txnDate.isAfter(date)) { // Include only transactions up to the given date
                if (txn.getTransactionType() == TransactionType.DEPOSIT || txn.getReceiverAccountNumber() != null) {
                    balance = balance.add(txn.getAmount());
                } else if (txn.getTransactionType() == TransactionType.WITHDRAW || txn.getReceiverAccountNumber() == null) {
                    balance = balance.subtract(txn.getAmount());
                }
            }
        }

        return balance;
    }

    private Map<String, Double> calculateTransactionTypePercentage(List<Transaction> transactions) {
        Map<String, Double> percentageMap = new HashMap<>();
        long totalTransactions = transactions.size();

        if (totalTransactions == 0) {
            return Map.of("DEPOSIT", 0.0, "WITHDRAW", 0.0, "TRANSFER", 0.0);
        }

        Map<TransactionType, Long> countMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getTransactionType, Collectors.counting()));

        for (TransactionType type : TransactionType.values()) {
            percentageMap.put(type.name(), (countMap.getOrDefault(type, 0L) * 100.0) / totalTransactions);
        }

        return percentageMap;
    }
}
