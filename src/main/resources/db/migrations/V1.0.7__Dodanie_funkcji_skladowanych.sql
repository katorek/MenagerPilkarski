DELIMITER $$

CREATE FUNCTION IleZwyciestw(p_creditLimit DOUBLE)
  RETURNS VARCHAR(10)
DETERMINISTIC
  BEGIN
    DECLARE lvl VARCHAR(10);

    IF p_creditLimit > 50000
    THEN
      SET lvl = 'PLATINUM';
    ELSEIF (p_creditLimit <= 50000 AND p_creditLimit >= 10000)
      THEN
        SET lvl = 'GOLD';
    ELSEIF p_creditLimit < 10000
      THEN
        SET lvl = 'SILVER';
    END IF;

    RETURN (lvl);
  END