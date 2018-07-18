INSERT INTO client VALUES (2, 'Kyev', '13-A', 34.234342, 35.423523, 'Polytechnichna street', 'Were the best', 'royd@mail.ru', 'client', 'some url' ,'sto', '0000', '88005555535', now());

INSERT INTO work_time VALUES (2, 'MONDAY', '09:00:00', 'FRIDAY', '20:00:00', 2);

INSERT INTO work_time VALUES (3, 'SATURDAY', '09:00:00', 'SUNDAY', '18:30:00', 2);

INSERT INTO news VALUES (2, 'Some cool news', 'some url', 'news', now(), 2);

INSERT INTO comment VALUES (2, 5, 1, 0, 2, 'some text', now(), 2);

INSERT INTO comment VALUES (3, 5, 0, 2, 1, 'some text', now(), 2);

INSERT INTO customer VALUES (2, 'mercedes', 't100', 'royd@mail.ua', 'Vitia', '052553', 'Bolgov');

INSERT INTO service VALUES (2, 30, 'service 1', 5000, 2);
INSERT INTO service VALUES (3, 45, 'service 2', 1000, 2);
INSERT INTO service VALUES (4, 70, 'service 3', 2000, 2);
INSERT INTO service VALUES (5, 110, 'service 4', 3000, 2);

INSERT INTO appointment VALUES (2,  now(), 2, 2, 2);