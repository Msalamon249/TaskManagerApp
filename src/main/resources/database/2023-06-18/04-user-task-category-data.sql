--liquibase formatted sql
--changeset msalamon:4

INSERT INTO EMPLOYEE (USER_NAME, EMAIL, PASSWORD)
VALUES ('Gaïa', 'vlaverock0@omniture.com', 'mJ3Gow'),
       ('Zoé', 'eberget1@nymag.com', 'hY6dtX'),
       ('Aloïs', 'lrichards2@dot.gov', 'iM5S5Z'),
       ('Östen', 'wwinkless3@auda.org.au', 'pV4dfFB'),
       ('Chloé', 'lcasazza4@usatoday.com', 'mA9JQRl'),
       ('Kallisté', 'jbethel5@arstechnica.com', 'rV0GDfD1'),
       ('Kù', 'ufeld6@lycos.com', 'tP097K4juA'),
       ('Yú', 'othornhill7@unblog.fr', 'wF0kqkhQv');

INSERT INTO CATEGORY(NAME, DESCRIPTION)
VALUES ('Word', 'Zadania związane z pracą zawodową lub projekty wykonywane w miejscu pracy.'),
       ('Personal', ' Zadania osobiste, takie jak zakupy, spotkania czy zadania domowe'),
       ('Projects', 'Kategorie związane z konkretnymi projektami lub inicjatywami'),
       ('Education', 'Zadania związane z nauką, kursami, czytaniem czy rozwojem osobistym'),
       ('Finance',
        'Zadania dotyczące zadań związanych z finansami, takie jak budżetowanie, rozliczenia czy inwestycje'),
       ('Meetings', 'Zadania związane z organizacją spotkań, zarządzaniem kalendarzem i harmonogramem');


INSERT INTO TASK (TITLE, DESCRIPTION, PRIORITY, END_DATE, CATEGORY_ID, EMPLOYEE_ID)
VALUES ('Zadanie 1', 'Opis zadania 1', 'LOW', '2023-06-15', 1, 3),
       ('Zadanie 2', 'Opis zadania 2', 'MEDIUM', '2023-06-20', 2, 2),
       ('Zadanie 3', 'Opis zadania 3', 'HIGH', '2023-06-25', 1, 1),
       ('Zadanie 4', 'Opis zadania 4', 'LOW', '2023-06-18', 3, 3),
       ('Zadanie 5', 'Opis zadania 5', 'MEDIUM', '2023-06-23', 2, 2),
       ('Zadanie 6', 'Opis zadania 6', 'HIGH', '2023-06-27', 3, 3),
       ('Zadanie 7', 'Opis zadania 7', 'LOW', '2023-06-21', 1, 1),
       ('Zadanie 8', 'Opis zadania 8', 'MEDIUM', '2023-06-30', 2, 2),
       ('Zadanie 9', 'Opis zadania 9', 'MEDIUM', '2023-07-02', 3, 3),
       ('Zadanie 10', 'Opis zadania 10', 'LOW', '2023-06-28', 1, 1),
       ('Zadanie 11', 'Opis zadania 11', 'LOW', '2023-06-28', 1, null);



