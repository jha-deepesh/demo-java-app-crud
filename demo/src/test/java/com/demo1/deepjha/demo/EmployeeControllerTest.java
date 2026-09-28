package com.demo1.deepjha.demo;

import com.demo1.deepjha.demo.entities.EmployeeEntity;
import com.demo1.deepjha.demo.repositories.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private EmployeeRepository employeeRepository;

    @BeforeEach
    void setUp() {
        employeeRepository.deleteAll();
    }

    @Test
    void testCreateEmployee() throws Exception {
        String employeeJson = """
                {
                    "name": "Ana Atanasovska",
                    "email": "marko.test@example.com",
                    "phone": "0711234567",
                    "dateOfJoining": "2025-01-15",
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.name").value("Ana Atanasovska"))
                .andExpect(jsonPath("$.email").value("marko.test@example.com"));
    }

    @Test
    void testGetAllEmployees() throws Exception {
        String employeeJson = """
                {
                    "name": "Ana Atanasovska",
                    "email": "marko.test@example.com",
                    "phone": "0711234567",
                    "dateOfJoining": "2025-01-15",
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/employees/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson));

        mockMvc.perform(get("/employees/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Ana Atanasovska"))
                .andExpect(jsonPath("$[0].email").value("marko.test@example.com"));
    }

    @Test
    void testGetEmployeeById() throws Exception {
        String employeeJson = """
                {
                    "name": "Ana Atanasovska",
                    "email": "marko.test@example.com",
                    "phone": "0711234567",
                    "dateOfJoining": "2025-01-15",
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/employees/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson));

        EmployeeEntity employee = employeeRepository.findAll().get(0);

        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana Atanasovska"))
                .andExpect(jsonPath("$.email").value("marko.test@example.com"));
    }

    @Test
    void testDeleteEmployee() throws Exception {
        String employeeJson = """
                {
                    "name": "Ana Atanasovska",
                    "email": "marko.test@example.com",
                    "phone": "0711234567",
                    "dateOfJoining": "2025-01-15",
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/employees/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(employeeJson));

        EmployeeEntity employee = employeeRepository.findAll().get(0);

        mockMvc.perform(delete("/employees/delete/" + employee.getId()))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/employees/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testCreateMultipleEmployees() throws Exception {
        String firstEmployeeJson = """
                {
                    "name": "Ana Atanasovska",
                    "email": "ana@example.com",
                    "phone": "0711111111",
                    "dateOfJoining": "2025-01-15",
                    "isActive": true
                }
                """;

        String secondEmployeeJson = """
                {
                    "name": "Bojan Petrov",
                    "email": "bojan@example.com",
                    "phone": "0722222222",
                    "dateOfJoining": "2025-02-20",
                    "isActive": true
                }
                """;

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(firstEmployeeJson))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(secondEmployeeJson))
                .andExpect(status().is2xxSuccessful());

        mockMvc.perform(get("/employees/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Ana Atanasovska"))
                .andExpect(jsonPath("$[1].name").value("Bojan Petrov"));
    }

    @Test
    void testDeleteNonExistingEmployee() throws Exception {
        mockMvc.perform(delete("/employees/delete/999"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(content().string("false"));
    }

    @Test
    void testGetAllEmployeesWhenDatabaseIsEmpty() throws Exception {
        mockMvc.perform(get("/employees/getAllEmployees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    void testGetEmployeeByIdWithAllFields() throws Exception {
        String employeeJson = """
            {
                "name": "Ana Atanasovska",
                "email": "ana.full@example.com",
                "phone": "0719876543",
                "dateOfJoining": "2025-03-10",
                "isActive": true
            }
            """;

        mockMvc.perform(post("/employees/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(employeeJson))
                .andExpect(status().is2xxSuccessful());

        EmployeeEntity employee = employeeRepository.findAll().get(0);

        mockMvc.perform(get("/employees/" + employee.getId()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ana Atanasovska"))
                .andExpect(jsonPath("$.email").value("ana.full@example.com"))
                .andExpect(jsonPath("$.phone").value("0719876543"));
    }
}