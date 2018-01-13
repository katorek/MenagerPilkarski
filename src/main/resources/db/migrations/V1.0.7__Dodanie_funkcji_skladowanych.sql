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

# DELIMITER $$

# DROP PROCEDURE IF EXISTS wygrana2;

# CREATE PROCEDURE wygrana2 (IN W_IN INT UNSIGNED)
#   BEGIN
#     DECLARE W_CUS INT UNSIGNED DEFAULT 0;
#     DECLARE W_TOT DOUBLE DEFAULT 0; -- NOT USED?
#     -- GET CUS_CODE
#     SELECT CUS_CODE INTO W_CUS
#     FROM INVOICE
#     WHERE INVOICE.INV_NUMBER = W_IN;
#
#     -- UPDATES CUSTOMER IF W_CUS > 0
#     IF W_CUS > 0 THEN
#       UPDATE CUSTOMER
#       SET CUS_BALANCE = CUS_BALANCE +
#                         (SELECT INV_TOTAL FROM INVOICE WHERE INV_NUMBER = W_IN)
#       WHERE CUS_CODE = W_CUS;
#     END IF;
#   END $$
#
# DELIMITER ;


# Tests
SELECT Wygrana('3:4', 0) AS 'exp: 0';
SELECT Wygrana('3:4', 1) AS 'exp: 1';

SELECT Wygrana('4:4', 0) AS 'exp: 0';
SELECT Wygrana('4:4', 1) AS 'exp: 0';

SELECT Wygrana('4:3', 0) AS 'exp: 1';
SELECT Wygrana('4:3', 1) AS 'exp: 0';
