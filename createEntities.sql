INSERT INTO client VALUES (2,  'royd@mail.ru', 'client', 'some url' ,'sto', '$2a$12$1hiwzi6gw/vZiDMzpbH5weNr2F7ZdnlazVq5vW0saVyWm.PwdyxWW', '88005555535', now());

INSERT INTO advantage VALUES (2, 'We repair very fast!', 'Fast repair', 2);

INSERT INTO address VALUES(2, 'Kyev', '13-A',  34.234342, 35.423523, 'Polytechnichna street', 2);

INSERT INTO address VALUES(3, 'Kharkiv', '8-B',  25.234342, 26.423523, 'Pobedy street', 2);

INSERT INTO advantage VALUES (3, 'Our price polithic will surprise you!', 'Low prices', 2);

INSERT INTO advantage VALUES (4, 'High level quality!', 'Hish quality', 2);


INSERT INTO work_time VALUES (2, 'MONDAY', '09:00:00', 'FRIDAY', '20:00:00', 2);

INSERT INTO work_time VALUES (3, 'SATURDAY', '09:00:00', 'SUNDAY', '18:30:00', 2);

INSERT INTO news VALUES (2, 'Some cool news here you can see a lot of text ', 'some url', 'news', now(), 2);

INSERT INTO comment VALUES (2, 5, 1, 0, 2, 'Positive cool comment so you can read it and watch cool news also what do i write', now(), 2);

INSERT INTO comment VALUES (3, 5, 0, 2, 1, 'some text', now(), 2);

INSERT INTO user VALUES (2, 'royd@mail.ua', 'Vitia', 'password', '052553', 'GUEST', 'Bolgov');

INSERT INTO car VALUES(2, 'Mercedes', 'T1000', 2);
INSERT INTO car VALUES(3, 'Ferrari', 'f12', 2);

INSERT INTO service VALUES (2,  'Custom Service. This service doesnt take much time. We help you do smth',30, 'service 1', 5000, 2);
INSERT INTO service VALUES (3, 'Custom Service. This service doesnt take much time. We help you do smth', 45, 'service 2', 1000, 2);
INSERT INTO service VALUES (4, 'Try to do Service. This service doesnt take much time. We help you do smth', 70, 'service 3', 2000, 2);
INSERT INTO service VALUES (5, 'Service.This service doesnt take much time. We help you do smth', 110, 'service 4', 3000, 2);

INSERT INTO appointment VALUES (2,  now(), 2, 2, 2);