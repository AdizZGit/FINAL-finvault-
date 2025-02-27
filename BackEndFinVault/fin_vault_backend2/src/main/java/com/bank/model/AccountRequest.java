package com.bank.model;


import java.sql.Date;

import jakarta.persistence.Column;

//import javax.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "accountrequest")
public class AccountRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String firstName;
	
	private String lastName;
	
	private Date dateOfBirth;
	
	private String phone;
	
	private String location;
	
	private String email; 
	
	
	// Restricting accountType to 'savings' or 'current' using regular expression
    @Column(name = "account_type", nullable = false)
    @Pattern(regexp = "savings|current", message = "Account type must be 'savings' or 'current'")
    private String accountType;  // accountType can only be 'savings' or 'current'
	

}
