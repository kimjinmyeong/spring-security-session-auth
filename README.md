# Spring Security Session Auth

## Description

This Spring Boot application implements session-based authentication using Spring Security, without utilizing the traditional form-based login or JWT.
## Features

- **Custom LoginFilter**: Implements custom login filter.
- **Session Management**: Session management using Redis.
- **OpenAPI Documentation**: Integrated with OpenAPI (Swagger) for API documentation.

## Technologies Used

- **Spring Boot 3.3.1**
- **Spring Security**
- **Spring Data JPA**
- **MySQL**
- **Redis**
- **Spring Session Data Redis**
- **Redisson**
- **Springdoc OpenAPI**

## Prerequisites

- Recommend JDK 17 or higher
- Gradle
- Docker

## Accessing the Application

- **API Documentation**: [http://localhost:8080/docs](http://localhost:8080/docs)
- **Register EndPoint**: [POST /register](http://localhost:8080/register)
- **Login Endpoint**: [POST /login](http://localhost:8080/login)
- **Logout Endpoint**: [GET /logout](http://localhost:8080/logout)
- **Get Session Create Time Endpoint**: [GET /session](http://localhost:8080/session)

### Example JSON Request

**register**
```json
{
  "username": "user",
  "password": "pass"
}
```
**login**
```json
{
  "username": "user",
  "password": "pass"
}
```
