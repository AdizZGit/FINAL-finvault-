package com.bank.model;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Entity;
import lombok.*;
import jakarta.persistence.*; 
@Entity
@Getter
@Setter
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

//    @Column(nullable = false)
//    private String role;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;
    
//    @ManyToMany(mappedBy = "users")
//    private Set<Employee> employees = new HashSet<>();
    @CreationTimestamp  // ✅ Auto-set at creation
    private LocalDateTime createdAt;

    @UpdateTimestamp  // ✅ Auto-set at every update
    private LocalDateTime updatedAt;
    
}
