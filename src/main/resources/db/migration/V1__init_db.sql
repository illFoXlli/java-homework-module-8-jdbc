CREATE TABLE client (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL
);

CREATE TABLE project (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(100),
                         client_id INT,
                         start_date DATE,
                         finish_date DATE,
                         FOREIGN KEY (client_id) REFERENCES client(id)
);

CREATE TABLE worker (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100),
                        salary INT
);