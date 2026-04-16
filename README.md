# JDBC Module 8

## 📌 Description
This project demonstrates working with a database using JDBC, Flyway migrations, and layered architecture (DAO + Service).

The application:
- automatically creates and updates the database using Flyway migrations
- performs CRUD operations for `Client` entity
- separates business logic and database access using DAO pattern
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

### 🔹 Migrations
- `resources/db.migration/V1__init_db.sql` — creates database schema
- `resources/db.migration/V2__populate_db.sql` — inserts initial data

---

### 🔹 Packages

#### `dto`
Data Transfer Objects (models):
- `Client`
- `Project`
- `MaxProjectCountClient`

---

#### `jdbc`
Infrastructure layer:
- `Database` — singleton for DB connection
- `DatabaseMigrationService` — runs Flyway migrations

---

#### `dao`
Data Access Layer (works with database):
- `ClientDaoService` — interface
- `ClientDaoServiceImpl` — JDBC implementation (SQL queries)

---

#### `service`
Business Logic Layer:
- `ClientService` — interface
- `ClientServiceImpl` — validation and business logic

---

#### Root package
- `Main` — application entry point

---

## ▶ How to run

Run the application:

```bash
./gradlew run
```

---

## 📊 Features
- Flyway migrations for database versioning
- Clean separation of layers:
  - DAO — database logic
  - Service — business logic
- CRUD operations for Client
- Safe SQL execution using PreparedStatement
- Automatic resource management (try-with-resources)

---

## 💡 Notes
- H2 database file (*.mv.db) is excluded via .gitignore
- Migrations are applied automatically on application startup
- Project follows basic SOLID principles