package com.bank.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import java.sql.Date;
import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Getter
@Setter
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

  
    private Date dateOfBirth;

    private String phone;
    private String location;
    private String email;
//    private String accountType;
    
    @Column(name = "account_type", nullable = false)
    @Pattern(regexp = "savings|current", message = "Account type must be 'savings' or 'current'")
    private String accountType;  // accountType can only be 'savings' or 'current'
	
    @Column(unique = true, nullable = false)
    private String accountNumber;  // Unique Account Number
    private double balance; // Default is 0
    
    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Transaction> transactions; // List of all transactions for this account
    
    @CreationTimestamp  // ✅ Auto-set at creation
    private LocalDateTime createdAt;

    @UpdateTimestamp  // ✅ Auto-set at every update
    private LocalDateTime updatedAt;
}
