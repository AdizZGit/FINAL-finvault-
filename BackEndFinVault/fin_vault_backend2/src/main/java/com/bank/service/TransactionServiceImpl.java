package com.bank.service;

import com.bank.dto.TransactionRequestDTO;
import com.bank.dto.TransactionResponseDTO;
import com.bank.model.Account;
import com.bank.model.Transaction;
import com.bank.model.TransactionStatus;
import com.bank.model.TransactionType;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private TwilioService twilioService;

    @Override
    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
        return transactionRepository.findByAccount_AccountNumber(accountNumber);
    }

    @Override
    public List<Transaction> getTransactionsByType(TransactionType transactionType) {
        return transactionRepository.findByTransactionType(transactionType);
    }

    @Override
    public List<Transaction> getTransactionsByDate(LocalDate date) {
        return transactionRepository.findByCreatedAtBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
    }

    @Override
    public TransactionResponseDTO deposit(TransactionRequestDTO requestDTO) {
        Account account = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        double newBalance = account.getBalance() + requestDTO.getAmount().doubleValue();
        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(requestDTO.getAmount())
                .transactionType(TransactionType.DEPOSIT)
                .transactionStatus(TransactionStatus.SUCCESS)
                .timestamp(LocalDateTime.now())
                .referenceId(UUID.randomUUID().toString())
                .build();

        transactionRepository.save(transaction);

        // Fetch phone number from the account table and send SMS
        String message = "Dear Customer, ₹" + requestDTO.getAmount() +
                " has been deposited to your FINVAULT account. Available balance: ₹" + newBalance;
        twilioService.sendSms(account.getPhone(), message);

        return buildTransactionResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDTO withdraw(TransactionRequestDTO requestDTO) {
        Account account = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (!isBalanceSufficient(account.getAccountNumber(), requestDTO.getAmount())) {
            throw new RuntimeException("Insufficient balance");
        }

        double newBalance = account.getBalance() - requestDTO.getAmount().doubleValue();
        account.setBalance(newBalance);
        accountRepository.save(account);

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(requestDTO.getAmount())
                .transactionType(TransactionType.WITHDRAW)
                .transactionStatus(TransactionStatus.SUCCESS)
                .timestamp(LocalDateTime.now())
                .referenceId(UUID.randomUUID().toString())
                .build();

        transactionRepository.save(transaction);

        // Fetch phone number from the account table and send SMS
        String message = "Dear Customer, ₹" + requestDTO.getAmount() +
                " has been withdrawn from your FINVAULT account. Available balance: ₹" + newBalance;
        twilioService.sendSms(account.getPhone(), message);

        return buildTransactionResponseDTO(transaction);
    }

    @Override
    public TransactionResponseDTO transfer(TransactionRequestDTO requestDTO) {
        Account senderAccount = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
                .orElseThrow(() -> new RuntimeException("Sender Account not found"));

        if (!isBalanceSufficient(senderAccount.getAccountNumber(), requestDTO.getAmount())) {
            throw new RuntimeException("Insufficient balance for transfer");
        }

        Account receiverAccount = accountRepository.findByAccountNumber(requestDTO.getReceiverAccountNumber())
                .orElseThrow(() -> new RuntimeException("Receiver Account not found"));

        double senderNewBalance = senderAccount.getBalance() - requestDTO.getAmount().doubleValue();
        senderAccount.setBalance(senderNewBalance);
        accountRepository.save(senderAccount);

        double receiverNewBalance = receiverAccount.getBalance() + requestDTO.getAmount().doubleValue();
        receiverAccount.setBalance(receiverNewBalance);
        accountRepository.save(receiverAccount);

        Transaction senderTransaction = Transaction.builder()
                .account(senderAccount)
                .receiverAccountNumber(requestDTO.getReceiverAccountNumber())
                .amount(requestDTO.getAmount())
                .transactionType(TransactionType.TRANSFER)
                .transactionStatus(TransactionStatus.SUCCESS)
                .timestamp(LocalDateTime.now())
                .referenceId(UUID.randomUUID().toString())
                .build();

        transactionRepository.save(senderTransaction);

        Transaction receiverTransaction = Transaction.builder()
                .account(receiverAccount)
                .receiverAccountNumber(requestDTO.getAccountNumber())
                .amount(requestDTO.getAmount())
                .transactionType(TransactionType.TRANSFER)
                .transactionStatus(TransactionStatus.SUCCESS)
                .timestamp(LocalDateTime.now())
                .referenceId(UUID.randomUUID().toString())
                .build();

        transactionRepository.save(receiverTransaction);

        // Fetch phone numbers from the account table and send SMS
        String senderMessage = "Dear Customer, ₹" + requestDTO.getAmount() +
                " has been transferred from your FINVAULT account. Available balance: ₹" + senderNewBalance;
        twilioService.sendSms(senderAccount.getPhone(), senderMessage);

        String receiverMessage = "Dear Customer, ₹" + requestDTO.getAmount() +
                " has been received in your FINVAULT account. Available balance: ₹" + receiverNewBalance;
        twilioService.sendSms(receiverAccount.getPhone(), receiverMessage);

        return buildTransactionResponseDTO(senderTransaction);
    }

    @Override
    public boolean isBalanceSufficient(String accountNumber, BigDecimal amount) {
        Account account = accountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        return account.getBalance() >= amount.doubleValue();
    }

    private TransactionResponseDTO buildTransactionResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .transactionId(transaction.getTransactionId())
                .accountNumber(transaction.getAccount().getAccountNumber())
                .receiverAccountNumber(transaction.getReceiverAccountNumber())
                .amount(transaction.getAmount())
                .transactionType(transaction.getTransactionType())
                .transactionStatus(transaction.getTransactionStatus())
                .referenceId(transaction.getReferenceId())
                .timestamp(transaction.getTimestamp())
                .createdAt(transaction.getCreatedAt())
                .updatedAt(transaction.getUpdatedAt())
                .build();
    }
}

































//package com.bank.service;
//
//import com.bank.dto.TransactionRequestDTO;
//import com.bank.dto.TransactionResponseDTO;
//import com.bank.model.Account;
//import com.bank.model.Transaction;
//import com.bank.model.TransactionStatus;
//import com.bank.model.TransactionType;
//import com.bank.repository.AccountRepository;
//import com.bank.repository.TransactionRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.UUID;
//
//@Service
//public class TransactionServiceImpl implements TransactionService {
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    @Autowired
//    private TransactionRepository transactionRepository;
//    
//    
////    @Override
////    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
////        return transactionRepository.findByAccount_AccountNumber(accountNumber);
////    }
////
////    @Override
////    public List<Transaction> getTransactionsByType(String transactionType) {
////        TransactionType type = TransactionType.valueOf(transactionType.toUpperCase());
////        return transactionRepository.findByTransactionType(type);
////    }
////
////    @Override
////    public List<Transaction> getTransactionsByDate(LocalDate date) {
////        return transactionRepository.findByTimestampBetween(date.atStartOfDay(), date.atTime(23, 59, 59));
////    }
//    
// // Method to retrieve transactions by account number
//    @Override
//    public List<Transaction> getTransactionsByAccountNumber(String accountNumber) {
//        return transactionRepository.findByAccount_AccountNumber(accountNumber);
//    }
//
//    // Method to retrieve transactions by transaction type
//    @Override
//    public List<Transaction> getTransactionsByType(TransactionType transactionType) {
//        return transactionRepository.findByTransactionType(transactionType);
//    }
//
//    // Method to retrieve transactions by date
//    @Override
//    public List<Transaction> getTransactionsByDate(LocalDate date) {
//        return transactionRepository.findByCreatedAtBetween(date.atStartOfDay(), date.plusDays(1).atStartOfDay());
//    }
//
//    @Override
//    public TransactionResponseDTO deposit(TransactionRequestDTO requestDTO) {
//        // Step 1: Retrieve the account based on account number
//        Account account = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        // Step 2: Update balance after deposit
//        double newBalance = account.getBalance() + requestDTO.getAmount().doubleValue();
//        account.setBalance(newBalance);
//        accountRepository.save(account); // Save updated balance
//
//        // Step 3: Create transaction record
//        Transaction transaction = Transaction.builder()
//                .account(account)
//                .amount(requestDTO.getAmount())
//                .transactionType(TransactionType.DEPOSIT)
//                .transactionStatus(TransactionStatus.SUCCESS)
//                .timestamp(LocalDateTime.now())
//                .referenceId(UUID.randomUUID().toString())
//                .build();
//
//        transactionRepository.save(transaction); // Save transaction record
//
//        // Step 4: Return transaction response
//        return buildTransactionResponseDTO(transaction);
//    }
//
//    @Override
//    public TransactionResponseDTO withdraw(TransactionRequestDTO requestDTO) {
//        // Step 1: Retrieve the account based on account number
//        Account account = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//
//        // Step 2: Validate sufficient balance for withdrawal
//        if (!isBalanceSufficient(account.getAccountNumber(), requestDTO.getAmount())) {
//        	System.out.println("im here");
//            throw new RuntimeException("Insufficient balance");
//        }
//
//        // Step 3: Update balance after withdrawal
//        double newBalance = account.getBalance() - requestDTO.getAmount().doubleValue();
//        account.setBalance(newBalance);
//        accountRepository.save(account); // Save updated balance
//
//        // Step 4: Create transaction record
//        Transaction transaction = Transaction.builder()
//                .account(account)
//                .amount(requestDTO.getAmount())
//                .transactionType(TransactionType.WITHDRAW)
//                .transactionStatus(TransactionStatus.SUCCESS)
//                .timestamp(LocalDateTime.now())
//                .referenceId(UUID.randomUUID().toString())
//                .build();
//
//        transactionRepository.save(transaction); // Save transaction record
//
//        // Step 5: Return transaction response
//        return buildTransactionResponseDTO(transaction);
//    }
//
//    @Override
//    public TransactionResponseDTO transfer(TransactionRequestDTO requestDTO) {
//        // Step 1: Retrieve the sender account based on account number
//        Account senderAccount = accountRepository.findByAccountNumber(requestDTO.getAccountNumber())
//                .orElseThrow(() -> new RuntimeException("Sender Account not found"));
//
//        // Step 2: Validate sufficient balance for transfer
//        if (!isBalanceSufficient(senderAccount.getAccountNumber(), requestDTO.getAmount())) {
//            throw new RuntimeException("Insufficient balance for transfer");
//        }
//
//        // Step 3: Retrieve the receiver account based on receiver account number
//        Account receiverAccount = accountRepository.findByAccountNumber(requestDTO.getReceiverAccountNumber())
//                .orElseThrow(() -> new RuntimeException("Receiver Account not found"));
//
//        // Step 4: Update sender balance after transfer
//        double senderNewBalance = senderAccount.getBalance() - requestDTO.getAmount().doubleValue();
//        senderAccount.setBalance(senderNewBalance);
//        accountRepository.save(senderAccount); // Save updated sender balance
//
//        // Step 5: Update receiver balance after transfer
//        double receiverNewBalance = receiverAccount.getBalance() + requestDTO.getAmount().doubleValue();
//        receiverAccount.setBalance(receiverNewBalance);
//        accountRepository.save(receiverAccount); // Save updated receiver balance
//
//        // Step 6: Create transaction record for sender
//        Transaction senderTransaction = Transaction.builder()
//                .account(senderAccount)
//                .receiverAccountNumber(requestDTO.getReceiverAccountNumber())
//                .amount(requestDTO.getAmount())
//                .transactionType(TransactionType.TRANSFER)
//                .transactionStatus(TransactionStatus.SUCCESS)
//                .timestamp(LocalDateTime.now())
//                .referenceId(UUID.randomUUID().toString())
//                .build();
//
//        transactionRepository.save(senderTransaction); // Save sender transaction record
//
//        // Step 7: Create transaction record for receiver
//        Transaction receiverTransaction = Transaction.builder()
//                .account(receiverAccount)
//                .receiverAccountNumber(requestDTO.getAccountNumber())
//                .amount(requestDTO.getAmount())
//                .transactionType(TransactionType.TRANSFER)
//                .transactionStatus(TransactionStatus.SUCCESS)
//                .timestamp(LocalDateTime.now())
//                .referenceId(UUID.randomUUID().toString())
//                .build();
//
//        transactionRepository.save(receiverTransaction); // Save receiver transaction record
//
//        // Step 8: Return transaction response
//        return buildTransactionResponseDTO(senderTransaction);
//    }
//
//    // Helper method to check if balance is sufficient
//    @Override
//    public boolean isBalanceSufficient(String accountNumber, BigDecimal amount) {
//        Account account = accountRepository.findByAccountNumber(accountNumber)
//                .orElseThrow(() -> new RuntimeException("Account not found"));
//        return account.getBalance() >= amount.doubleValue();
//    }
//
//    // Helper method to build the TransactionResponseDTO
//    private TransactionResponseDTO buildTransactionResponseDTO(Transaction transaction) {
//        return TransactionResponseDTO.builder()
//                .transactionId(transaction.getTransactionId())
//                .accountNumber(transaction.getAccount().getAccountNumber())
//                .receiverAccountNumber(transaction.getReceiverAccountNumber())
//                .amount(transaction.getAmount())
//                .transactionType(transaction.getTransactionType())
//                .transactionStatus(transaction.getTransactionStatus())
//                .referenceId(transaction.getReferenceId())
//                .timestamp(transaction.getTimestamp())
//                .createdAt(transaction.getCreatedAt())
//                .updatedAt(transaction.getUpdatedAt())
//                .build();
//    }
//}
