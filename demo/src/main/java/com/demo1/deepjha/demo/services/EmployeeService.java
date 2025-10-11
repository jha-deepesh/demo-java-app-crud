// EmployeeService.java
// Service class for business logic related to Employee operations.
// Purpose: Services in Spring Boot contain business logic and interact with repositories for data access.
// This class demonstrates how ModelMapper simplifies object mapping between entities and DTOs.
package com.demo1.deepjha.demo.services;

import com.demo1.deepjha.demo.dto.EmployeeDTO;
import com.demo1.deepjha.demo.entities.EmployeeEntity;
import com.demo1.deepjha.demo.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // Marks this class as a Spring service component
public class EmployeeService {

    // Repository for EmployeeEntity database operations
    final EmployeeRepository employeeRepository;
    // ModelMapper for automatic conversion between DTOs and entities
    final ModelMapper modelMapper;

    // Constructor injection for dependencies
    public EmployeeService(EmployeeRepository employeeRepository, ModelMapper modelMapper) {
        this.employeeRepository = employeeRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Retrieves an employee by ID and maps the entity to a DTO.
     * ModelMapper automatically copies matching fields, reducing manual mapping code.
     * @param id Employee ID
     * @return EmployeeDTO or null if not found
     */
    public EmployeeDTO getEmployee(Long id) {
        EmployeeEntity employeeEntity = employeeRepository.findById(id).orElse(null);
        if (employeeEntity != null) {
            // Previously, manual mapping was required:
            // return new EmployeeDTO(
            //     employeeEntity.getId(),
            //     employeeEntity.getName(),
            //     employeeEntity.getEmail(),
            //     employeeEntity.getPhone(),
            //     employeeEntity.getDateOfJoining(),
            //     employeeEntity.isActive()
            // );
            // Now, ModelMapper automatically maps fields from EmployeeEntity to EmployeeDTO,
            // reducing boilerplate and improving maintainability.
            return modelMapper.map(employeeEntity, EmployeeDTO.class);
        } else {
            return null;
        }
    }

    /**
     * Creates a new employee record from a DTO.
     * ModelMapper converts the DTO to an entity for saving, then maps the saved entity back to a DTO for response.
     * @param employeeDTO Data transfer object with employee details
     * @return EmployeeDTO of the saved employee
     */
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        // ModelMapper converts EmployeeDTO to EmployeeEntity for persistence
        EmployeeEntity employeeEntity = modelMapper.map(employeeDTO, EmployeeEntity.class);
        EmployeeEntity savedEntity = employeeRepository.save(employeeEntity);
        // ModelMapper converts the saved entity back to DTO for response
        return modelMapper.map(savedEntity, EmployeeDTO.class);
    }

    public List<EmployeeDTO> getAllEmployees() {
        // Using Java Streams for mapping entities to DTOs
        return employeeRepository.findAll()
                .stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class))
                .toList();

        // Alternative using a traditional for loop:
        /*
        List<EmployeeEntity> entities = employeeRepository.findAll();
        List<EmployeeDTO> dtos = new ArrayList<>();
        for (EmployeeEntity entity : entities) {
            dtos.add(modelMapper.map(entity, EmployeeDTO.class));
        }
        return dtos;
        */
    }

    public boolean deleteEmployee(Long id) {
//        boolean isPresent = employeeRepository.deleteById(id);
//        return isPresent;
        if (employeeRepository.existsById(id)) {
            employeeRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
