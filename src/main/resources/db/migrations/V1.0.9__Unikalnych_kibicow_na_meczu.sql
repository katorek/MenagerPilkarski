# DELIMITER $$
#
# CREATE PROCEDURE UsunDruzyne(p_druzynaId INTEGER)
#   BEGIN
#
# #     DECLARE wynik INTEGER;
# #     DECLARE przy INTEGER;
# #     DECLARE PKT INTEGER;
#
# #     SET gosp = SUBSTRING_INDEX(p_wynik, ':', 1);
# #     SET przy = SUBSTRING_INDEX(SUBSTRING_INDEX(p_wynik, ':', 2), ':', -1);
#
#     select count(DISTINCT(kibic_id)) into wynik from bilety where mecz_id = p_meczId;
#
#
#     RETURN (wynik);
#   END $$
#
# DELIMITER ;
#
