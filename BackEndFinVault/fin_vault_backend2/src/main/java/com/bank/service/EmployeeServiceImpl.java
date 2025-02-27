package com.bank.service;

import java.util.*;
import com.bank.dto.EmployeeDTO;
import com.bank.model.Employee;
import com.bank.model.Role;
import com.bank.model.User;
import com.bank.repository.EmployeeRepository;
import com.bank.repository.UserRepository;
import com.bank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    // Convert Entity to DTO
    private EmployeeDTO convertToDTO(Employee employee) {
        return EmployeeDTO.builder()
                .id(employee.getId())
                .userId(employee.getUser().getId())
                .name(employee.getName())
                .designation(employee.getDesignation())
                .salary(employee.getSalary())
                .email(employee.getEmail())
                .build();
    }

    // Convert DTO to Entity
    private Employee convertToEntity(EmployeeDTO employeeDTO, User user) {
        return Employee.builder()
                .user(user)
                .name(employeeDTO.getName())
                .designation(employeeDTO.getDesignation())
                .salary(employeeDTO.getSalary())
                .email(employeeDTO.getEmail())
                .build();
    }
    
    public Employee findByUser(User user) {
        return employeeRepository.findByUser(user);
    }



    @Override
    public EmployeeDTO assignDesignationToEmployee(Long userId, EmployeeDTO employeeDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Ensure the user is an EMPLOYEE before adding them
        if (user.getRole() != Role.EMPLOYEE) {
            throw new RuntimeException("Only employees can be assigned designations");
        }

        // Check if Employee already exists
        if (employeeRepository.findByUserId(userId).isPresent()) {
            throw new RuntimeException("Employee is already assigned a designation");
        }

        Employee employee = convertToEntity(employeeDTO, user);
        Employee savedEmployee = employeeRepository.save(employee);
        return convertToDTO(savedEmployee);
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDTO getEmployeeById(Long id) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));
        return convertToDTO(employee);
    }

    @Override
    public EmployeeDTO updateEmployee(Long id, EmployeeDTO employeeDTO) {
        Employee existingEmployee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Employee not found"));

        existingEmployee.setName(employeeDTO.getName());
        existingEmployee.setDesignation(employeeDTO.getDesignation());
        existingEmployee.setSalary(employeeDTO.getSalary());

        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return convertToDTO(updatedEmployee);
    }

    @Override
    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }
}
