CREATE TABLE mecze (
  ID            INT PRIMARY KEY AUTO_INCREMENT,
  BOISKO_ID     INT,
  CENA_BILETU   DOUBLE,
  WYNIK_MECZU   VARCHAR(10),
  GOSPODARZ_ID  INTEGER,
  PRZYJEZDNI_ID INTEGER,
  DATA          DATE
);

INSERT INTO mecze (BOISKO_ID, CENA_BILETU, WYNIK_MECZU, GOSPODARZ_ID, PRZYJEZDNI_ID, DATA)
VALUES ((SELECT ID
         FROM boiska
         WHERE NAZWA = 'Stadion Miejski w Poznaniu'), 25.6, '3:0',
        (SELECT ID
         FROM druzyny
         WHERE NAZWA = 'Lech Poznań'),
        (SELECT ID
         FROM druzyny
         WHERE NAZWA =
               'Legia Warszawa'),
        '2017-10-01');

CREATE TABLE bilety (
  ID       INT PRIMARY KEY AUTO_INCREMENT,
  MECZ_ID  INT,
  ZNIZKA   DOUBLE,
  KIBIC_ID INT
);

INSERT INTO bilety (MECZ_ID, ZNIZKA, KIBIC_ID) VALUES
  (1,0,1);
