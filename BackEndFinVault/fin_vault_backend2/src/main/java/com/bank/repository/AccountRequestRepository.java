package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bank.model.AccountRequest;

@Repository
public interface AccountRequestRepository extends JpaRepository<AccountRequest, Long> {
    boolean existsByEmail(String email); // To check if email already exists
}
