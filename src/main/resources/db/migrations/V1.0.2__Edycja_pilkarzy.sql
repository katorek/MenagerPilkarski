ALTER TABLE pilkarze
  ADD DRUZYNA_ID INTEGER NULL;
ALTER TABLE pilkarze
  ADD NUMER INT NULL;
ALTER TABLE pilkarze
  ADD POZYCJA VARCHAR(13) NULL;

INSERT INTO druzyny (NAZWA) VALUE ('Bayern Monachium');
INSERT INTO druzyny (NAZWA) VALUES ('FC Juventus');
INSERT INTO druzyny (NAZWA) VALUES ('FC Barcelona');

UPDATE pilkarze
SET
  POZYCJA    = 'NAPASTNIK',
  NUMER      = 9,
  DRUZYNA_ID = (SELECT ID
                FROM druzyny
                WHERE NAZWA = 'Bayern Monachium')
WHERE IMIE = 'Robert' AND NAZWISKO = 'Lewandowski';

UPDATE pilkarze
SET
  POZYCJA    = 'BRAMKARZ',
  NUMER      = 23,
  DRUZYNA_ID = (SELECT ID
                FROM druzyny
                WHERE NAZWA = 'FC Juventus')
WHERE IMIE = 'Wojciech' AND NAZWISKO = 'Szczęsny';

UPDATE pilkarze
SET
  POZYCJA    = 'NAPASTNIK',
  NUMER      = 10,
  DRUZYNA_ID = (SELECT ID
                FROM druzyny
                WHERE NAZWA = 'FC Barcelona')
WHERE IMIE = 'Lionel' AND NAZWISKO = 'Messi';


UPDATE pilkarze
SET
  POZYCJA    = 'NAPASTNIK',
  NUMER      = 7,
  DRUZYNA_ID = (SELECT ID
                FROM druzyny
                WHERE NAZWA = 'Real Madryt')
WHERE IMIE = 'Cristiano' AND NAZWISKO = 'Ronaldo';

INSERT INTO pilkarze(PESEL, IMIE, NAZWISKO, DRUZYNA_ID, NUMER, POZYCJA) VALUE
  (1,'W','J',(SELECT ID
              FROM druzyny
              WHERE NAZWA = 'Real Madryt'),1,'BRAMKARZ');