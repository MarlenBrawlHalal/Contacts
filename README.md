This is a Spring Boot contact service for managing reference data.

## Prerequisites

- Java 21
- Maven
- PostgreSQL 16

## Dependencies

- Spring Web
- Spring Data JPA
- Lombok
- PostgreSQL JDBC Driver

## Configuration

Configure the database connection in `src/main/resources/application.properties` if using Spring Data JPA.

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/<your_db>
spring.datasource.username=<your_username>
spring.datasource.password=<your_password>

spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## Build the project
```
mvn clean install
```
## Run the project
```
mvn spring-boot:run
```

The application will start at http://localhost:8080.
