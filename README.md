# Authentication-and-Authorization

## Objectives

This project demononstrates the implementation of Authentication and Authorization security tactics using the Spring Framework. 

The goal is to prototype a system that satisfies the principles of controlled access and role-based privilege management as described in software security architecture design.

The implementation showcases how multiple users and user types can securely access distinct system services, each protected by different authorization levels.

## System Overview

The application uses Spring Boot and Spring Security to control user authentication and authorization.

## Key Features

- Multiple users with different roles and access privileges
- Role-based access control implemented through URL-based endpoint protection
- In-memory authentication setup
- Form-based login and automatic logout provided by Spring Security
- Demonstrates security quality attributes such as confidentiality, integrity, and least privilege

## Architecture Summary

| Component | Description |
| AuthDemoApplication.java | Entry point of the Spring Boot application |
| SecurityConfig.java | Defines authentication and authorization rules (users, roles, and endpoint restrictions) |
| UserController.java | Endpoint for users with the `ROLE_USER` privilege |
| AnalystController.java | Endpoint for users with the `ROLE_ANALYST` privilege |
| ManagerController.java | Endpoint for users with the `ROLE_MANAGER` privilege |
| AdminController.java | Endpoint for users with the `ROLE_ADMIN` privilege |

Each controller represents a protected service, accessible only to a specific role through a URL mapping.

## Technologies and Libraries Used

| Technology / Library | Purpose |
| Java 17 | Programming language used (per assignment constraint) |
| Sprint Boot 3.x | Framework for rapid application development |
| Spring Security | Provides authentication and authorization mechanisms | 
| Maven | Dependency management and build automation tool |

## How to run the appliction

### Requirements

- Java 17+
- Maven

### Steps

- Clone or download the project files
- Open a terminal in the project directory
- Run the application using Maven:
```bash
mvn spring-boot:run
```
- Once started, open a browser and navigate to:
[http://localhost:8080/]

- When prompted for login credentials, use any of the accounts below

## Login Accounts and password

| **Username** | **password** | **URLs** |
|---|---|---|
|Tim|password|[http://localhost:8080/api/user/profile]|
|Bill|password|[http://localhost:8080/api/analyst/report]|
|Mark|password|[http://localhost:8080/api/manager/report]|
|Sam|password|[http://localhost:8080/api/admin/report]|

## Logout

You can log out manually using:
[http://localhost:8080/logout]

## Design Rationale

This implementation follows the Authentication and Authorization security tactics as described in software architecture best practices:

1. Authentication Tactic

- Ensures only legitimate users can access the system
- Implemented via Spring Security’s in-memory authentication configuration (SecurityConfig.java)
- Each user is verified using a username and password form-based login

2. Authorization Tactic

- Enforces access control based on user roles.
- Endpoint access is restricted using Spring Security’s hasRole() method.
- Example configuration:

```bash
http.authorizeHttpRequests()
    .requestMatchers("/api/admin/**").hasRole("ADMIN")
    .requestMatchers("/api/manager/**").hasRole("MANAGER")
    .requestMatchers("/api/analyst/**").hasRole("ANALYST")
    .requestMatchers("/api/user/**").hasRole("USER")
    .anyRequest().authenticated()
    .and()
    .formLogin()
    .and()
    .logout().permitAll();
```

This tactic aligns with least privilege, ensuring users can only access endpoints relevant to their role

## Sequence Diagram

![Sequence Diagram](assets/Sequence%20Diagram.png)
