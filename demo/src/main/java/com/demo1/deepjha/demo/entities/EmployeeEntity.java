// EmployeeEntity.java
// Entity class representing the Employee table in the database.
// Purpose: Entities are used to map Java objects to database tables using JPA/Hibernate.
// This class defines the structure of employee records stored in the 'employees' table.
// Each field corresponds to a column in the table, and annotations specify mapping details.
package com.demo1.deepjha.demo.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity // Marks this class as a JPA entity
@Table(name="employees") // Maps this entity to the 'employees' table
@Data // Lombok annotation to generate getters, setters, equals, hashCode, and toString
@AllArgsConstructor // Lombok annotation to generate a constructor with all fields
@NoArgsConstructor // Lombok annotation to generate a no-argument constructor
public class EmployeeEntity {
    @Id // Marks this field as the primary key
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq") // Uses a sequence for ID generation
    @SequenceGenerator(name = "employee_seq", sequenceName = "employees_seq", allocationSize = 1) // Maps to the created sequence
    private Long id;
    // Employee's full name
    private String name;
    // Employee's email address
    private String email;
    // Employee's phone number
    private String phone;
    // Date when the employee joined
    private LocalDate dateOfJoining;
    // Status flag indicating if the employee is active
    private boolean isActive;
}
