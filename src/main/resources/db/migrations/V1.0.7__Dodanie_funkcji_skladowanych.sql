DELIMITER $$

CREATE FUNCTION Wygrana(p_wynik VARCHAR(10), p_druzyna INTEGER)
  RETURNS INTEGER
DETERMINISTIC
  BEGIN
    DECLARE gosp INTEGER;
    DECLARE przy INTEGER;
    DECLARE PKT INTEGER;

    SET gosp = SUBSTRING_INDEX(p_wynik, ':', 1);
    SET przy = SUBSTRING_INDEX(SUBSTRING_INDEX(p_wynik, ':', 2), ':', -1);

    IF p_druzyna = 0 # 0 - liczymy wygrane gospodarzy, 1 - wygrane przyjezdnych
    THEN
      IF gosp > przy
      THEN
        SET PKT = 1;
      ELSE
        SET PKT = 0;
      END IF;
    ELSE
      IF gosp < przy
      THEN
        SET PKT = 1;
      ELSE
        SET PKT = 0;
      END IF;
    END IF;

    RETURN (PKT);
  END $$

DELIMITER ;

DELIMITER $$

CREATE FUNCTION WygranychMeczyPrzezDruzyne(p_druzynaId INTEGER)
  RETURNS INTEGER
DETERMINISTIC
  BEGIN
    DECLARE jakoGospodarz INTEGER;
    DECLARE jakoPrzyjezdni INTEGER;
    DECLARE suma INTEGER;

    SET jakoGospodarz = 0;
    SET jakoPrzyjezdni = 0;

    SELECT sum(Wygrana(wynik_meczu, 0))
    INTO jakoGospodarz
    FROM mecze
    WHERE gospodarz_id = p_druzynaId;
    SELECT sum(Wygrana(wynik_meczu, 1))
    INTO jakoPrzyjezdni
    FROM mecze
    WHERE przyjezdni_id = p_druzynaId;

    SET jakoGospodarz = IFNULL(jakoGospodarz, 0);
    SET jakoPrzyjezdni = IFNULL(jakoPrzyjezdni, 0);

    SET suma = jakoGospodarz + jakoPrzyjezdni;

    RETURN (suma);
  END $$

DELIMITER ;


# Tests
SELECT Wygrana('3:4', 0) AS 'exp: 0';
SELECT Wygrana('3:4', 1) AS 'exp: 1';

SELECT Wygrana('4:4', 0) AS 'exp: 0';
SELECT Wygrana('4:4', 1) AS 'exp: 0';

SELECT Wygrana('4:3', 0) AS 'exp: 1';
SELECT Wygrana('4:3', 1) AS 'exp: 0';
