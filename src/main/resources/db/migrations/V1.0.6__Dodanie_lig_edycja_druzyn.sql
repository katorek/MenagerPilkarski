ALTER TABLE druzyny
  ADD LIGA_ID INTEGER UNSIGNED NULL;

CREATE TABLE ligi (
  ID     INTEGER UNSIGNED PRIMARY KEY AUTO_INCREMENT,
  NAZWA VARCHAR(30) NOT NULL UNIQUE
);

INSERT INTO ligi (NAZWA) VALUES ('Ekstraklasa'), ('I liga'),('II liga'), ('III liga'), ('Brak');

UPDATE druzyny set LIGA_ID = (SELECT ID FROM ligi WHERE NAZWA='Ekstraklasa') WHERE NAZWA in ('Lech Poznań', 'Legia Warszawa');