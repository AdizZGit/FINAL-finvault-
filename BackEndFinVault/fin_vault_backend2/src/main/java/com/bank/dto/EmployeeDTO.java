package com.bank.dto;

import com.bank.model.Designation;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeDTO {
    private Long id;
    private Long userId;  // Link to User Table
    private String name;
    private Designation designation;  // Enum type
    private double salary;
    private String email;
}
