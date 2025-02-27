package com.bank.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.dto.EmployeeDTO;
import com.bank.model.Account;
import com.bank.model.AccountRequest;
import com.bank.model.Transaction;
import com.bank.model.TransactionType;
import com.bank.model.User;
import com.bank.service.AccountRequestService;
import com.bank.service.AccountService;
import com.bank.service.EmployeeService;
import com.bank.service.TransactionService;
import com.bank.service.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
	
	 @Autowired
	    private AccountService accountService;

	    @Autowired
	    private AccountRequestService accountRequestService;
	    
	    @Autowired
	    private UserService userService;
	    
	    @Autowired
	    private EmployeeService employeeService;
	    
	    
	//for user dashboard
	
	// Get all pending account requests
    @GetMapping("/accounts/getAllRequests")
    public ResponseEntity<List<AccountRequest>> getAllRequests() {
        return ResponseEntity.ok(accountRequestService.getAllAccountRequests());
    }
    
    // Get all accounts
    @GetMapping("/accounts/getAllAccounts")
    public ResponseEntity<List<Account>> getAllAccounts() {
        return ResponseEntity.ok(accountService.getAllAccounts());
    }

    // Get account by ID
    @GetMapping("/accounts/{id}")
    public ResponseEntity<Optional<Account>> getAccountById(@PathVariable Long id) {
        return ResponseEntity.ok(accountService.getAccountById(id));
    }
    
    @GetMapping("/users/getUser/{userId}")
    public ResponseEntity<User> getUserDetails(@PathVariable Long userId) {
        User user = userService.getUserDetails(userId);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @GetMapping("/users/getAllUsers")
    public ResponseEntity<List<User>> getAllUserDetails() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }	
    
    @DeleteMapping("/users/delete/{userId}")
    public ResponseEntity<String> deleteUserDetails(@PathVariable Long userId) {
        String response = userService.deleteUserDetails(userId);
        return ResponseEntity.ok(response);
    }
    
    //for employees
    // Admin Assigns Designation to Employee
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/employees/assign/{userId}")
    public ResponseEntity<EmployeeDTO> assignDesignation(@PathVariable Long userId, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.assignDesignationToEmployee(userId, employeeDTO));
    }
    
    
    @GetMapping("/getAllEmployees")
  public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
      return ResponseEntity.ok(employeeService.getAllEmployees());
  }
    
    @GetMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
    }

    @DeleteMapping("/employees/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return ResponseEntity.ok("Employee deleted successfully");
    }
    
    //for txns
    
    @Autowired
    private TransactionService transactionService;
    
    // Get transactions by account number
    @GetMapping("/transaction/{accountNumber}")
    public ResponseEntity<List<Transaction>> getTransactionsByAccountNumber(@PathVariable String accountNumber) {
        List<Transaction> transactions = transactionService.getTransactionsByAccountNumber(accountNumber);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by type (DEPOSIT, WITHDRAW, TRANSFER)
    @GetMapping("/transaction/type/{transactionType}")
    public ResponseEntity<List<Transaction>> getTransactionsByType(@PathVariable TransactionType transactionType) {
        List<Transaction> transactions = transactionService.getTransactionsByType(transactionType);
        return ResponseEntity.ok(transactions);
    }

    // Get transactions by date (Format: YYYY-MM-DD)
    @GetMapping("/transaction/date/{date}")
    public ResponseEntity<List<Transaction>> getTransactionsByDate(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        List<Transaction> transactions = transactionService.getTransactionsByDate(localDate);
        return ResponseEntity.ok(transactions);
    }

}
