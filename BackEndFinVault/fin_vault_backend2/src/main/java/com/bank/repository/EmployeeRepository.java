package com.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import com.bank.model.Employee;
import com.bank.model.User;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByEmail(String email);
	
    Optional<Employee> findByUserId(Long userId);

	Employee findByUser(User user);
	//@Query("SELECT u.id FROM User u WHERE u.id NOT IN (SELECT e.userId FROM Employee e)")
   // List<Long> findUsersNotInEmployees();
}

