package com.bank.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user; // Link Employee to a User

    @Column(nullable = false)
    private String name;
    
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Designation designation;  // Now using ENUM

    @Column(nullable = false)
    private double salary;   
    
    @CreationTimestamp  // ✅ Auto-set at creation
    private LocalDateTime createdAt;

    @UpdateTimestamp  // ✅ Auto-set at every update
    private LocalDateTime updatedAt;
    
}
