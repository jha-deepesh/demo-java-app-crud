// EmployeeController.java
// Controller class for handling HTTP requests related to Employee operations.
// Purpose: Controllers in Spring Boot manage incoming REST API requests, delegate business logic to services, and return responses.
// This class demonstrates endpoint definitions for employee retrieval and creation, using EmployeeService for business logic.
package com.demo1.deepjha.demo.controllers;

import com.demo1.deepjha.demo.dto.EmployeeDTO;
import com.demo1.deepjha.demo.services.EmployeeService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController // Marks this class as a REST controller, enabling request mapping and response handling
@RequestMapping(path = "employees") // Base path for all endpoints in this controller
public class EmployeeController {

    // Service layer dependency for business logic, injected via constructor, using final to make it immutable
    private final EmployeeService employeeService;

    /**
     * Constructor for dependency injection of EmployeeService.
     * @param employeeService Service layer for employee business logic
     */
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

//    /**
//     * Handles GET requests for employees with optional sorting and limiting parameters.
//     * Demonstrates basic request parameter handling.
//     * @param sortBy Optional sort parameter
//     * @param limit Optional limit parameter
//     * @return Simple greeting string with provided parameters
//     */
//    @GetMapping(path="/")
//    public String getData(@PathParam("sortBy") String sortBy,
//                          @PathParam("limit") String limit){
//        // Returns a simple greeting with provided parameters
//        return "Hello " + sortBy + " " + limit;
//    }

    @GetMapping(path="/getAllEmployees")
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    /**
     * Handles GET requests for a specific employee by ID.
     * Delegates retrieval logic to EmployeeService, which uses ModelMapper for DTO conversion.
     * @param employeeId Employee ID from path variable
     * @return EmployeeDTO for the requested employee
     */
    @GetMapping(path="/{id}")
    public EmployeeDTO getEmployeeById(@PathVariable("id") Long employeeId){
        // Calls service layer to fetch and map employee data
        return employeeService.getEmployee(employeeId);
    }

    /**
     * Handles POST requests to create a new employee.
     * Delegates creation logic to EmployeeService, which uses ModelMapper for DTO/entity conversion.
     * @param employeeDTO Employee data from request body
     * @return EmployeeDTO of the created employee
     */
    @PostMapping(path="/create")
    public EmployeeDTO createEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.createEmployee(employeeDTO);
    }

    @DeleteMapping(path="/delete/{id}")
    public boolean deleteEmployeeById(@PathVariable("id") Long employeeId) {
        return employeeService.deleteEmployee(employeeId);
//        return "Employee with id " + employeeId + " deleted successfully";
    }

}
