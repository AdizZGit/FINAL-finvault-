package com.bank.controller;

import com.bank.dto.EmployeeDTO;
import com.bank.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    // Admin Assigns Designation to Employee
//    @PostMapping("/assign/{userId}")
//    public ResponseEntity<EmployeeDTO> assignDesignation(@PathVariable Long userId, @RequestBody EmployeeDTO employeeDTO) {
//        return ResponseEntity.ok(employeeService.assignDesignationToEmployee(userId, employeeDTO));
//    }

//    @GetMapping
//    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
//        return ResponseEntity.ok(employeeService.getAllEmployees());
//    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Long id) {
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

//    @PutMapping("/{id}")
//    public ResponseEntity<EmployeeDTO> updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO employeeDTO) {
//        return ResponseEntity.ok(employeeService.updateEmployee(id, employeeDTO));
//    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<String> deleteEmployee(@PathVariable Long id) {
//        employeeService.deleteEmployee(id);
//        return ResponseEntity.ok("Employee deleted successfully");
//    }
    
}
