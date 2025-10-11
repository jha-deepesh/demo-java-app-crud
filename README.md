# Java Spring Boot CRUD Demo Application

## Overview
This project is a beginner-friendly Java Spring Boot application demonstrating basic CRUD (Create, Read, Update, Delete) operations using RESTful APIs, JPA, and H2 in-memory database. It is designed to help you learn the fundamentals of building web applications with Spring Boot.

## Features
- RESTful API endpoints for managing Employee data
- CRUD operations: Create, Read, Update, Delete
- Uses DTOs, Entities, Repositories, Services, and Controllers
- ModelMapper for object mapping
- H2 in-memory database for easy setup
- Maven build system

## Technologies Used
- Java 17+
- Spring Boot 3+
- Spring Data JPA
- H2 Database
- ModelMapper
- Maven

## Prerequisites
- Java JDK 17 or higher
- Maven
- IDE (IntelliJ IDEA, Eclipse, VS Code, etc.)

## Getting Started
1. **Clone the repository:**
   ```
   git clone https://github.com/your-username/demo-java-app-crud.git
   ```
2. **Navigate to the project directory:**
   ```
   cd demo-java-app-crud/demo
   ```
3. **Build the project:**
   ```
   mvn clean install
   ```
4. **Run the application:**
   ```
   mvn spring-boot:run
   ```
5. **Access H2 Console:**
   - Visit [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
   - JDBC URL: `jdbc:h2:mem:testdb`

## Project Structure
```
demo/
├── src/main/java/com/demo1/deepjha/demo/
│   ├── DemoApplication.java         # Main Spring Boot application
│   ├── entities/EmployeeEntity.java # Entity class (maps to DB)
│   ├── dto/EmployeeDTO.java         # Data Transfer Object
│   ├── repositories/EmployeeRepository.java # JPA Repository
│   ├── services/EmployeeService.java # Business logic
│   ├── controllers/EmployeeController.java # REST API endpoints
│   └── configuration/AppConfig.java # ModelMapper bean config
├── src/main/resources/
│   └── application.properties       # DB and app config
├── pom.xml                         # Maven dependencies
```

## How CRUD Works
- **Create:** `POST /employees` - Add a new employee
- **Read:** `GET /employees` - Get all employees
- **Update:** `PUT /employees/{id}` - Update employee by ID
- **Delete:** `DELETE /employees/{id}` - Delete employee by ID

### Sample Request (Create Employee)
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "1234567890",
  "dateOfJoining": "2023-01-01",
  "isActive": true
}
```

## Key Concepts Explained
### Entity
Represents a table in the database. Example: `EmployeeEntity.java` defines fields like id, name, email, etc.

### DTO (Data Transfer Object)
Used to transfer data between layers (e.g., from client to server). Keeps internal model safe and flexible.

### Repository
Interface for database operations. Extends `JpaRepository` to provide CRUD methods.

### Service
Contains business logic. Calls repository methods and handles data transformation.

### Controller
Defines REST API endpoints. Receives HTTP requests and returns responses.

### ModelMapper
Library for mapping between DTOs and Entities. Reduces manual conversion code.

#### Example Usage
```java
ModelMapper modelMapper = new ModelMapper();
EmployeeEntity entity = modelMapper.map(employeeDTO, EmployeeEntity.class);
```

## Database Configuration (H2)
- H2 is an in-memory database, so data is lost when the app stops.
- Configured in `application.properties`:
  ```properties
  spring.datasource.url=jdbc:h2:mem:testdb
  spring.datasource.driverClassName=org.h2.Driver
  spring.datasource.username=sa
  spring.datasource.password=
  spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
  spring.h2.console.enabled=true
  ```

## Common Errors & Troubleshooting
- **Dialect not set:** Ensure `spring.jpa.database-platform` is set in `application.properties`.
- **H2 Console not accessible:** Check `spring.h2.console.enabled=true`.
- **Data not persisting:** H2 is in-memory; use a file-based DB for persistence.

## Useful Resources
- [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/current/reference/html/)
- [Spring Data JPA Guide](https://spring.io/projects/spring-data-jpa)
- [ModelMapper Documentation](http://modelmapper.org/)
- [H2 Database Guide](https://www.h2database.com/html/main.html)
- [Baeldung Spring Boot Tutorials](https://www.baeldung.com/spring-boot-start)

## Next Steps for Beginners
- Try adding new fields to Employee
- Implement validation
- Switch to a persistent database (e.g., MySQL)
- Add unit and integration tests
- Explore authentication and security

---
Happy coding! If you have questions, check the resources above or open an issue.

