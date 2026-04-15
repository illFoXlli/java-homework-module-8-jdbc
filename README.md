# JDBC Module 8

## 📌 Description
This project demonstrates working with a database using JDBC, Flyway migrations and CRUD operations.

The application:
- automatically creates and updates the database using Flyway migrations
- performs CRUD operations for `Client` entity
- executes SQL queries using `PreparedStatement`

---

## 🛠 Technologies
- Java
- JDBC
- H2 Database
- Flyway
- Gradle

---

## 📁 Project Structure

### Migrations
- `resources/db.migration/V1__init_db.sql` — creates database schema
- `resources/db.migration/V2__populate_db.sql` — inserts initial data

---

### Java classes
- `Database` — singleton for DB connection
- `DatabaseMigrationService` — runs Flyway migrations
- `ClientService` — CRUD operations for Client
- `DatabaseQueryService` — complex SELECT queries
- `Main` — entry point

---

## ▶ How to run

1. Run application:
```bash
run Main