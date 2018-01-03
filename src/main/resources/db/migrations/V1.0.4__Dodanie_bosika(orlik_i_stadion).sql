CREATE TABLE orlik
(
  ID           INT PRIMARY KEY AUTO_INCREMENT,
  NAZWA        VARCHAR(50) NOT NULL,
  ILOSC_MIEJSC INT         NOT NULL,
  MIEJSCOWOSC  VARCHAR(20) NOT NULL
);

CREATE TABLE stadiony
(
  ID                    INT PRIMARY KEY AUTO_INCREMENT,
  NAZWA                 VARCHAR(30) NOT NULL,
  ILOSC_MIEJSC          INT         NOT NULL,
  MIEJSCOWOSC           VARCHAR(20) NOT NULL,
  CHRONI_PRZED_DESZCZEM TINYINT(1)  NOT NULL
);

INSERT INTO stadiony (NAZWA, ILOSC_MIEJSC, MIEJSCOWOSC, CHRONI_PRZED_DESZCZEM) VALUES
  ('Stadion Miejski w Poznaniu', 43269, 'Poznań', 0),
  ('Camp Nou', 99354, 'Barcelona', 1);

INSERT INTO orlik (NAZWA, ILOSC_MIEJSC, MIEJSCOWOSC) VALUES
  ('Młodzieżowy Ośrodek Sportowy',100,'Poznań'),
  ('Stadion Towarzystwa Sportowego Polonia',100,'Poznań');
