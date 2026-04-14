# JDBC Module 8

## 📌 Description
This project demonstrates working with a database using JDBC, PreparedStatement, and Flyway migrations.

The application:
- automatically creates and updates the database using Flyway
- performs CRUD operations for clients
- executes parameterized SQL queries

---

## 🛠 Technologies
- Java
- JDBC
- H2 Database
- Flyway
- Gradle

---

## 📁 Project Structure

### Migrations (Flyway)
- db/migration/V1__init_db.sql — creates database tables
- db/migration/V2__populate_db.sql — inserts initial data

---

### SQL scripts
- sql/find_all_clients.sql — get all clients
- sql/find_max_projects_client.sql — client with max projects
- sql/find_client_by_id.sql — find client by ID
- sql/find_clients_by_name.sql — find clients by name
- sql/find_projects_by_client_id.sql — find projects by client ID

---

### Java classes
- Database — singleton for DB connection
- DatabaseQueryService — executes SELECT queries
- ClientService — CRUD operations for clients
- Main — entry point (runs Flyway and test queries)

---

## ▶ How to run

1. Run application:

---

## 📊 Example output

Client{id=1, name='John Doe'}  
Client{id=2, name='Anna Smith'}

MaxProjectCountClient{name='John Doe', projectCount=3}

---

## 💡 Features
- Uses Flyway for database migrations
- Uses PreparedStatement for parameterized queries
- Prevents SQL injection
- Implements CRUD operations for Client entity
- Clean separation between SQL and Java code

---

## ⚠ Notes
- H2 database is created automatically (`test.mv.db`)
- Flyway applies migrations on application start
- SQL scripts are located in `/sql`
- Migration scripts are located in `/db/migration`  