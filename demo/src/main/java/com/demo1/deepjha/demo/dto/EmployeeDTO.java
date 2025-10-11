// EmployeeDTO.java
// Data Transfer Object (DTO) for Employee data.
// Purpose: DTOs are used to transfer data between different layers of an application (e.g., controller to service).
// They help decouple internal entity models from external representations, improving security and flexibility.
// This class represents employee information sent to or received from clients, without exposing database entity details.
package com.demo1.deepjha.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    // Unique identifier for the employee
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
