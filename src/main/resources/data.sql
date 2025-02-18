CREATE TABLE person (
    id INT PRIMARY KEY,
    name VARCHAR(200) NOT NULL ,
    location VARCHAR(200),
    birthDate TIMESTAMP
);

INSERT INTO person (id, name, location, birthDate)
VALUES
    (10001, 'Iza', 'Warsaw',  '1990-05-15 10:20:00'),
    (10002, 'Piotr', 'Paris',  '2003-02-22 14:21:00'),
    (10003, 'Jan', 'Berlin',  '1992-10-15 08:30:00'),
    (10004, 'Jan', 'London',  '1972-11-15 08:30:00');