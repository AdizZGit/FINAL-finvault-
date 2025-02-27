package com.bank.service;

import com.bank.dto.EmployeeDTO;
import com.bank.model.Employee;
import com.bank.model.User;

import java.util.List;

public interface EmployeeService {
    
   //EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
   EmployeeDTO assignDesignationToEmployee(Long userId, EmployeeDTO employeeDTO);
    
    List<EmployeeDTO> getAllEmployees();
    
    EmployeeDTO getEmployeeById(Long id);
    
    EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO);
    
    void deleteEmployee(Long id);

	Employee findByUser(User user);


}
