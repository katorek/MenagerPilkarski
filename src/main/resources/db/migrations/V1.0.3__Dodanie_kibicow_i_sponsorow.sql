CREATE TABLE sponsorzy
(
  ID         INT PRIMARY KEY AUTO_INCREMENT,
  NAZWA      VARCHAR(30) NOT NULL,
  RODZAJ     VARCHAR(30) NOT NULL,
  DRUZYNA_ID INT         NOT NULL
);
CREATE UNIQUE INDEX SPONSORZY_NAZWA_uindex
  ON sponsorzy (NAZWA);

INSERT INTO sponsorzy (NAZWA, RODZAJ, DRUZYNA_ID) VALUES
  ('Aforti', 'Sponsor strategiczny', (SELECT ID
                                      FROM druzyny
                                      WHERE NAZWA = 'Lech Poznań')),
  ('LECH Pils', 'Sponsor główny', (SELECT ID
                                   FROM druzyny
                                   WHERE NAZWA = 'Lech Poznań')),
  ('STS', 'Sponsor premium', (SELECT ID
                              FROM druzyny
                              WHERE NAZWA = 'Lech Poznań')),
  ('Amica', 'Sponsor premium', (SELECT ID
                                FROM druzyny
                                WHERE NAZWA = 'Lech Poznań')),
  ('Inea', 'Sponsor premium', (SELECT ID
                               FROM druzyny
                               WHERE NAZWA = 'Lech Poznań'));

CREATE TABLE kibice
(
  ID       INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  PESEL    VARCHAR(11) NULL,
  IMIE     VARCHAR(30) NOT NULL,
  NAZWISKO VARCHAR(30) NOT NULL,
  ZNIZKA   TINYINT(1),
  CONSTRAINT PESEL
  UNIQUE (PESEL)
);

INSERT INTO kibice (PESEL, IMIE, NAZWISKO, ZNIZKA) VALUES
  (1, 'Wojtek', 'Jaro', '0'),
  (2, 'Jan', 'Hal', '1'),
  (3, 'Piotr', 'Krylski', '1'),
  (4, 'Kasia', 'Potek', '1'),
  (5, 'Maciej', 'Kaszuba', '0'),
  (6, 'Zosia', 'Kowalska', '1');