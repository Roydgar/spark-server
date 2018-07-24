INSERT INTO client VALUES (2,  'royd@mail.ru', 'client', 'https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png' ,'sto', '$2a$12$1hiwzi6gw/vZiDMzpbH5weNr2F7ZdnlazVq5vW0saVyWm.PwdyxWW', now());

INSERT INTO mobile_number VALUES (2, '88055535', 'MTC', 2);
INSERT INTO mobile_number VALUES (3, '51241253', 'LIFE', 2);

INSERT INTO advantage VALUES (2,  'https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png', 'We repair very fast!', 'Fast repair', 2);

INSERT INTO address VALUES(2, 'Kyev', '13-A',  34.234342, 35.423523, 'Polytechnichna street', 2);

INSERT INTO address VALUES(3, 'Kharkiv', '8-B',  25.234342, 26.423523, 'Pobedy street', 2);

INSERT INTO advantage VALUES (3, 'https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png','Our price polithic will surprise you!', 'Low prices', 2);

INSERT INTO advantage VALUES (4, 'https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png', 'High level quality!', 'Hish quality', 2);


INSERT INTO work_time VALUES (2, 'MONDAY', '09:00:00', 'FRIDAY', '20:00:00', 2);

INSERT INTO work_time VALUES (3, 'SATURDAY', '09:00:00', 'SUNDAY', '18:30:00', 2);

INSERT INTO news VALUES (2, 'Some cool news here you can see a lot of text ', 'https://upload.wikimedia.org/wikipedia/commons/a/ab/Logo_TV_2015.png', 'news', now(), 2);


INSERT INTO user VALUES (2, 'royd@mail.ua', 'Vitia', 'password', '052553', 'GUEST', 'Bolgov');

INSERT INTO user VALUES (3, 'roydgaryhska@gmail.com', 'Vanya', 'password', '052553', 'GUEST', 'Pups');

INSERT INTO comment VALUES (2, 5, 0, 0, 0, 'Positive cool comment so you can read it and watch cool news also what do i write', now(), 2, 2);

INSERT INTO comment VALUES (3, 5, 0, 2, 0, 'some text', now(), 2, 2);

INSERT INTO comment VALUES (4, 5, 0, 0, 0, 'some other cool text', now(), 2, 3);

INSERT INTO car VALUES(2, 'Mercedes', 'T1000', 2);
INSERT INTO car VALUES(3, 'Ferrari', 'f12', 2);

INSERT INTO service VALUES (2,  'Custom Service. This service doesnt take much time. We help you do smth',30, 'service 1', 5000, 2);
INSERT INTO service VALUES (3, 'Custom Service. This service doesnt take much time. We help you do smth', 45, 'service 2', 1000, 2);
INSERT INTO service VALUES (4, 'Try to do Service. This service doesnt take much time. We help you do smth', 70, 'service 3', 2000, 2);
INSERT INTO service VALUES (5, 'Service.This service doesnt take much time. We help you do smth', 110, 'service 4', 3000, 2);
INSERT INTO service VALUES (7, 'This service takes a lot time to do, it will cost you some money, but you can always take a credit to do this service. Your car will be happy to get this, becouse youre a good boy.', 110, 'Advanced full repair', 3000, 2);

