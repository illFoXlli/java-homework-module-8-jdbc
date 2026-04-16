# JDBC Module 8

## Description
This project demonstrates working with a database using JDBC, Flyway migrations, and layered architecture (DAO + Service).

The application:
- automatically creates and updates the database using Flyway migrations
- performs CRUD operations for `Client` entity
- separates business logic and database access using DAO pattern
- executes SQL queries using `PreparedStatement`
- stores SQL queries in resource files and loads them from the classpath

---

## Technologies
- Java
- JDBC
- H2 Database
- Flyway
- Gradle

---

## Project Structure

### Migrations
- `src/main/resources/db/migration/V1__init_db.sql` — creates database schema
- `src/main/resources/db/migration/V2__populate_db.sql` — inserts initial data

### SQL queries
- `src/main/resources/sql/create_client.sql`
- `src/main/resources/sql/delete_client_by_id.sql`
- `src/main/resources/sql/find_all_clients.sql`
- `src/main/resources/sql/find_client_by_id.sql`
- `src/main/resources/sql/find_clients_by_name.sql`
- `src/main/resources/sql/find_max_projects_client.sql`
- `src/main/resources/sql/find_projects_by_client_id.sql`
- `src/main/resources/sql/update_client_name.sql`

---

### Packages

#### `dto`
Data Transfer Objects (models):
- `Client`
- `Project`
- `MaxProjectCountClient`

---

#### `jdbc`
Infrastructure layer:
- `Database` — singleton for DB settings and connection creation
- `DatabaseMigrationService` — runs Flyway migrations
- `SqlReader` — loads SQL files from `src/main/resources/sql`

---

#### `dao`
Data Access Layer (works with database):
- `ClientDaoService` — interface
- `ClientDaoServiceImpl` — JDBC implementation using SQL files from resources

---

#### `service`
Business Logic Layer:
- `ClientService` — interface
- `ClientServiceImpl` — validation and business logic

---

#### Root package
- `Main` — application entry point

---

## How to run

Run the application:

```bash
./gradlew run
```

Run tests:

```bash
./gradlew test
```

---

## Features
- Flyway migrations for database versioning
- Clean separation of layers:
  - DAO — database logic
  - Service — business logic
- CRUD operations for Client
- Safe SQL execution using PreparedStatement
- SQL queries stored in `src/main/resources/sql`
- DAO methods open and close database connections with try-with-resources
- Automatic resource management (try-with-resources)

---

## Notes
- H2 database file (*.mv.db) is excluded via .gitignore
- Migrations are applied automatically on application startup
- Database settings are stored in `src/main/resources/application.properties`
- Project follows basic SOLID principles
