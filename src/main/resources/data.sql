INSERT INTO course (id, name, creation_date, last_updated_date)
VALUES (10001, 'Spring', NOW(),NOW()),
       (10002, 'jUnit 5', NOW(), NOW()),
       (10003, 'Spring Boot', NOW(), NOW()),
       (10004, 'Hibernate', NOW(), NOW());

INSERT INTO passport(id, number)
VALUES (40001, 'ABC123'),
       (40002, 'XYZ123'),
       (40003, 'A1B2C3'),
       (40004, '321BCA');

INSERT INTO student (id, name, passport_id)
VALUES (20001, 'Izabela', 40001),
       (20002, 'Piotr', 40002),
       (20003, 'Jan', 40003),
       (20004, 'Anna',40004);

INSERT INTO review(id, rating, description, course_id)
VALUES (50001, '5', 'Great course',10001),
       (50002, '1', 'Really bad course', 10002),
       (50003, '3', 'Its an okay course', 10003),
       (50004, '4', 'Really good', 10001);

INSERT INTO student_course(student_id, course_id)
VALUES (20001, 10003),
       (20002, 10001),
       (20001, 10001),
       (20003, 10004);