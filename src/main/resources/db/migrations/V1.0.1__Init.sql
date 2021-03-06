CREATE TABLE DRUZYNY(
  ID INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  NAZWA VARCHAR(30) NOT NULL UNIQUE
);

CREATE TABLE PILKARZE(
  ID INT(6) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  PESEL VARCHAR(11) UNIQUE,
  IMIE VARCHAR(30) NOT NULL,
  NAZWISKO VARCHAR(30) NOT NULL
);

INSERT INTO DRUZYNY (NAZWA) VALUES
  ('Lech Poznań'),
  ('Legia Warszawa'),
  ('Korona Kielce'),
  ('Real Madryt');

INSERT INTO PILKARZE (IMIE,NAZWISKO) VALUES
  ('Robert','Lewandowski'),
  ('Cristiano','Ronaldo'),
  ('Lionel','Messi'),
  ('Wojciech','Szczęsny');