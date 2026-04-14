-- clients
INSERT INTO client (name) VALUES ('John');
INSERT INTO client (name) VALUES ('Anna');
INSERT INTO client (name) VALUES ('Mike');

-- projects
INSERT INTO project (name, client_id, start_date, finish_date)
VALUES ('Project A', 1, '2020-01-01', '2020-06-01');

INSERT INTO project (name, client_id, start_date, finish_date)
VALUES ('Project B', 1, '2021-01-01', '2021-12-01');

INSERT INTO project (name, client_id, start_date, finish_date)
VALUES ('Project C', 2, '2022-01-01', '2022-03-01');

INSERT INTO project (name, client_id, start_date, finish_date)
VALUES ('Project D', 3, '2022-05-01', '2022-08-01');

-- workers
INSERT INTO worker (name, salary) VALUES ('Bob', 1000);
INSERT INTO worker (name, salary) VALUES ('Alice', 2000);
INSERT INTO worker (name, salary) VALUES ('Tom', 3000);