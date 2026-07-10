# 💳 Digital Wallet System

<p align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=openjdk)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-4.1.0-6DB33F?style=for-the-badge&logo=springboot)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=springsecurity)
![JWT](https://img.shields.io/badge/JWT-Authentication-blue?style=for-the-badge)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-336791?style=for-the-badge&logo=postgresql)
![Liquibase](https://img.shields.io/badge/Liquibase-2962FF?style=for-the-badge)
![Swagger](https://img.shields.io/badge/OpenAPI-Swagger-85EA2D?style=for-the-badge&logo=swagger)
![Maven](https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apachemaven)

</p>

<p align="center">
A secure RESTful Digital Wallet API built using Spring Boot, Spring Security and JWT Authentication.
</p>

---

# 📚 Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Design Decisions](#design-decisions)
- [Tech Stack](#tech-stack)
- [Project Structure](#project-structure)
- [Security](#security)
- [API Endpoints](#api-endpoints)
- [Getting Started](#getting-started)
- [Challenges & Solutions](#challenges--solutions)
- [Future Improvements](#future-improvements)
- [Author](#author)

---

# Overview

Digital Wallet System is a secure backend application that simulates the core functionality of a real-world digital wallet.

The system allows authenticated users to securely manage digital wallets through REST APIs while applying industry-standard security practices using JWT Authentication and Spring Security.

### Main Capabilities

- 👤 Register & Login
- 💼 Create Wallet
- 💰 Deposit Money
- 💸 Withdraw Money
- 🔄 Transfer Money
- 💳 Issue Virtual Cards
- 🛒 Pay Using Virtual Cards
- 📜 View Transaction History
- 👮 Admin User Management

The project focuses on building clean, maintainable, and secure backend applications following layered architecture principles.

---

# Features

## 🔑 Authentication

- User Registration
- User Login
- JWT Authentication
- Secure Logout
- Token Blacklisting

---

## 💼 Wallet

- Create Wallet
- Deposit Money
- Withdraw Money
- Transfer Money

---

## 💳 Virtual Cards

- Generate Virtual Cards
- Pay Using Virtual Cards

---

## 📜 Transactions

- Transaction History
- Wallet Activity Tracking

---

## 👮 Authorization

- Role-Based Authorization
- Method Security
- Admin-only Endpoints

---

## ✅ Validation

- Jakarta Validation
- Global Exception Handling
- Custom Exceptions

---

# Architecture

The application follows a clean layered architecture where each layer has a single responsibility.

```text
                  Client
                     │
                     ▼
             REST Controllers
                     │
                     ▼
                Service Layer
                     │
                     ▼
             Repository Layer
                     │
                     ▼
               PostgreSQL Database
```

### Layers

```
Controller
      │
      ▼
Service
      │
      ▼
Repository
      │
      ▼
Database
```

### Advantages

- Separation of Concerns
- Clean Code
- Maintainability
- Scalability
- Testability

---

# Design Decisions

While implementing the project, several design decisions were made to improve security, consistency, and maintainability.

| Decision | Description |
|----------|-------------|
| 🗂 **Liquibase ChangeSet IDs** | Used timestamp-based IDs to ensure every migration remains unique and avoids conflicts between changesets. |
| 🔄 **Optimistic Locking** | Applied `@Version` to prevent lost updates when multiple users modify the same wallet simultaneously. |
| 🛡 **Unified Security Exception Handling** | Integrated a custom `AccessDeniedHandler` with `HandlerExceptionResolver` so Spring Security exceptions are returned using the same API response format as the global exception handler. |
| 🔐 **Server-side Wallet Resolution** | The sender wallet is resolved from the authenticated JWT instead of accepting a wallet ID from the client, preventing unauthorized transfers. |

---

# Tech Stack

| Category | Technology |
|----------|------------|
| ☕ Language | Java 17 |
| 🌱 Framework | Spring Boot 4 |
| 🔐 Security | Spring Security |
| 🔑 Authentication | JWT |
| 🗄 Database | PostgreSQL |
| 🏛 ORM | Spring Data JPA + Hibernate |
| 🔄 Database Versioning | Liquibase |
| 📄 API Documentation | OpenAPI / Swagger |
| 📦 Build Tool | Maven |
| ✅ Validation | Jakarta Validation |
| ✨ Boilerplate Reduction | Lombok |

---

# Project Structure

```text
src
└── main
    └── java
        └── com.example.digital_wallet_system
            │
            ├── config
            ├── controllers
            ├── errors
            │   ├── dtos
            │   ├── exceptions
            │   └── handlers
            ├── models
            │   ├── dtos
            │   ├── entities
            │   └── enums
            ├── repositories
            ├── security
            │   ├── jwt
            │   └── user
            ├── services
            ├── utils
            └── DigitalWalletSystemApplication
```

---

# Highlights

- 🔐 JWT Authentication
- 🛡 Spring Security
- 💳 Virtual Card Management
- 💸 Secure Money Transfer
- 📜 Transaction History
- 🚫 Token Blacklisting
- 🔄 Liquibase Database Versioning
- 📄 Swagger Documentation
- 🗄 PostgreSQL Database
- 🧩 Clean Layered Architecture

---
# Security

The application is built using **Spring Security** with **JWT Authentication** to provide a secure and stateless authentication mechanism.

## Security Features

- 🔐 JWT Authentication
- 🔑 BCrypt Password Encryption
- 🚫 Token Blacklisting after Logout
- 👮 Role-Based Authorization
- 🔒 Protected REST Endpoints
- 🛡 Authentication Filter
- ⚡ Stateless Session Management
- ✅ Method Security using `@PreAuthorize`

### Authentication Flow

```text
Client
   │
   │ Login
   ▼
AuthenticationManager
   │
   ▼
UserDetailsService
   │
   ▼
Database
   │
   ▼
Generate JWT
   │
   ▼
Client Stores Token
   │
   ▼
Authorization: Bearer <JWT>
   │
   ▼
JwtAuthenticationFilter
   │
   ▼
Validate Token
   │
   ▼
Controller
```

---

# API Endpoints

## Authentication

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/auth/register` | Register a new user |
| POST | `/api/v1/auth/login` | Authenticate user |
| POST | `/api/v1/auth/logout` | Logout and blacklist JWT |

---

## Wallet

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/wallets/create` | Create wallet |
| POST | `/api/v1/wallets/deposit` | Deposit money |
| POST | `/api/v1/wallets/withdraw` | Withdraw money |
| POST | `/api/v1/wallets/transfer` | Transfer money |

---

## Virtual Cards

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/v1/virtual-cards` | Issue virtual card |
| POST | `/api/v1/virtual-cards/pay` | Pay using virtual card |

---

## Transactions

| Method | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/v1/transactions/history` | Wallet transaction history |

---

## Users

| Method | Endpoint | Description |
|---------|----------|-------------|
| DELETE | `/api/v1/users/{id}` | Delete user (Admin Only) |

---

# Getting Started

## Clone Repository

```bash
git clone https://github.com/MGaballh/digital-wallet-system.git
```

---

## Navigate

```bash
cd digital-wallet-system
```

---

## Configure Database

Configure your PostgreSQL database inside:

```properties
application.properties
```

Example:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/digital_wallet
spring.datasource.username=postgres
spring.datasource.password=your_password
```

---

## Run Application

```bash
mvn spring-boot:run
```

---

## Open Swagger

After starting the application:

```
http://localhost:8080/swagger-ui.html
```

or

```
http://localhost:8080/swagger-ui/index.html
```

---

# Challenges & Solutions

## 🔄 Handling Concurrent Wallet Updates

### Challenge

Multiple users could update the same wallet simultaneously, resulting in lost updates.

### Solution

Implemented **Optimistic Locking** using JPA's `@Version` annotation to ensure updates are only applied when the entity version matches.

---

## 🔐 Stateless Authentication

### Challenge

Managing authentication securely without relying on HTTP Sessions.

### Solution

Implemented **JWT Authentication** with Spring Security to create a completely stateless authentication flow.

---

## 🚫 Secure Logout

### Challenge

JWT tokens remain valid until they expire.

### Solution

Implemented a **Token Blacklist** to immediately invalidate tokens after logout.

---

## 🛡 Consistent Security Error Responses

### Challenge

Exceptions thrown inside the Spring Security filter chain are not handled by `@RestControllerAdvice`.

### Solution

Configured a custom `AccessDeniedHandler` combined with `HandlerExceptionResolver` to keep all API error responses consistent.

---

## 🔒 Preventing Wallet Spoofing

### Challenge

Allowing clients to send a sender wallet ID could enable unauthorized money transfers.

### Solution

The sender wallet is resolved directly from the authenticated JWT instead of trusting client-provided identifiers.

---

## 📦 Database Versioning

### Challenge

Managing schema evolution safely across environments.

### Solution

Liquibase is used to version database changes, with timestamp-based ChangeSet IDs to guarantee uniqueness.

---

# Future Improvements

The following enhancements can be added in future versions:

- 🔄 Refresh Tokens
- 📧 Email Verification
- 📱 Two-Factor Authentication (2FA)
- 🐳 Docker Support
- ☸ Kubernetes Deployment
- ⚙ GitHub Actions CI/CD
- 🧪 Unit Testing
- 🔬 Integration Testing
- 📈 Monitoring with Prometheus & Grafana
- ⚡ Redis Token Blacklist
- 📨 Notification Service
- 📊 Audit Logging
- 💱 Currency Exchange Support
- 💳 Physical Card Integration

---

# Author

## 👨‍💻 Mohamed Gaballah

Backend Developer passionate about building secure and scalable backend systems using Java and Spring Boot.

### Connect with me

- 💼 LinkedIn: https://www.linkedin.com/in/mohamed-gaballah-277460396/
- 💻 GitHub: https://github.com/MGaballh

---

# If you found this project helpful

⭐ Consider giving this repository a star on GitHub.
It motivates me to continue building high-quality backend projects.

---

<p align="center">

Made with ❤️ using Spring Boot & Spring Security

</p>
