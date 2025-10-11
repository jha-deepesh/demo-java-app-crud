// EmployeeRepository.java
// This interface provides CRUD operations for EmployeeEntity using Spring Data JPA.
package com.demo1.deepjha.demo.repositories;

import com.demo1.deepjha.demo.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository // Marks this interface as a Spring Data repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    // You can define custom query methods here if needed
    // Currently, this interface inherits all CRUD methods from JpaRepository
}
