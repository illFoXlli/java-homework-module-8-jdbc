# JDBC Module 8

## 📌 Description
This project demonstrates working with a database using JDBC and PreparedStatement.

The application:
- creates a database
- populates it with sample data
- executes SQL queries (including parameterized ones)

---

## 🛠 Technologies
- Java
- JDBC
- H2 Database
- Gradle

---

## 📁 Project Structure

### SQL scripts
- sql/init_db.sql — creates database tables
- sql/populate_db.sql — inserts test data
- sql/find_all_clients.sql — get all clients
- sql/find_max_projects_client.sql — client with max projects
- sql/find_client_by_id.sql — find client by ID (PreparedStatement)
- sql/find_clients_by_name.sql — find clients by name (PreparedStatement)
- sql/find_projects_by_client_id.sql — find projects by client ID (PreparedStatement)

---

### Java classes
- Database — singleton for DB connection
- DatabaseInitService — runs schema creation script
- DatabasePopulateService — fills database with data
- DatabaseQueryService — executes queries and maps results
- Main — entry point for running examples

---

## ▶ How to run

1. Initialize database:  
   run DatabaseInitService

2. Populate data:  
   run DatabasePopulateService

3. Execute queries:  
   run Main

---

## 📊 Example output

Client{id=1, name='John Doe'}  
Client{id=2, name='Anna Smith'}

MaxProjectCountClient{name='John Doe', projectCount=3}

---

## 💡 Features
- Uses PreparedStatement for parameterized queries
- Prevents SQL injection
- Clean separation between SQL and Java code
- Uses DTOs for mapping query results

---

## ⚠ Notes
- H2 database files are excluded via .gitignore
- SQL scripts are located in /sql  