package com.bank.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
	boolean existsByEmail(String email);
	// Method to find an account by account number
    Optional<Account> findByAccountNumber(String accountNumber);
}
