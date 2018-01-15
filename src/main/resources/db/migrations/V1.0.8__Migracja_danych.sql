UPDATE druzyny set LIGA_ID = 5 WHERE ID in(3,4,5,6,7);
ALTER TABLE druzyny MODIFY LIGA_ID INT(11) NOT NULL;

ALTER TABLE kibice MODIFY PESEL CHAR(11) NOT NULL;

update pilkarze set pesel = 25339746801 where id = 1;
update pilkarze set pesel = 91844146146 where id = 3;
update pilkarze set pesel = 24137835471 where id = 4;
update pilkarze set pesel = 21641531841 where id = 2;
update pilkarze set pesel = 21411261153 where id = 5;

ALTER TABLE pilkarze MODIFY PESEL CHAR(11) NOT NULL;
ALTER TABLE pilkarze MODIFY DRUZYNA_ID INT(11) NOT NULL;
ALTER TABLE pilkarze MODIFY NUMER INT(11) NOT NULL;
ALTER TABLE pilkarze MODIFY POZYCJA VARCHAR(13) NOT NULL;

UPDATE kibice set pesel = 36592672078 where id = 1;
UPDATE kibice set pesel = 38485347272 where id = 2;
UPDATE kibice set pesel = 75620572067 where id = 3;
UPDATE kibice set pesel = 95746338362 where id = 4;
UPDATE kibice set pesel = 20518395475 where id = 5;
UPDATE kibice set pesel = 89474726723 where id = 6;