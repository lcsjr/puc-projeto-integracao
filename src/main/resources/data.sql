-- DROP TABLE IF EXISTS pessoa;
-- DROP TABLE IF EXISTS dead;

-- CREATE TABLE pessoa (
--                         id INT AUTO_INCREMENT  PRIMARY KEY,
--                         name VARCHAR(250) NOT NULL,
--                         age INT NOT NULL,
--                         career VARCHAR(250) DEFAULT NULL
-- );

-- CREATE TABLE dead (
--   id INT AUTO_INCREMENT  PRIMARY KEY,
--   file VARCHAR(250) NOT NULL,
--   error VARCHAR(250) NOT NULL
-- );


DROP TABLE IF EXISTS produtos;

CREATE TABLE produtos (
                        id INT AUTO_INCREMENT PRIMARY KEY,
                        codigo VARCHAR(250) NOT NULL,
                        descricao VARCHAR(250) NOT NULL,
                        valor int NOT NULL
);

insert into produtos (codigo, descricao, valor) values ('hw001', 'test produto 1', 10);
insert into produtos (codigo, descricao, valor) values ('hw007', 'test produto 7', 70);
insert into produtos (codigo, descricao, valor) values ('hw012', 'test produto 12', 12);
insert into produtos (codigo, descricao, valor) values ('hw257', 'test produto 257', 257);

