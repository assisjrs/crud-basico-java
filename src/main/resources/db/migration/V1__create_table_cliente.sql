
CREATE TABLE CLIENTE (
  id BIGINT IDENTITY NOT NULL,
  nome varchar(255) NOT NULL,
  cpf varchar(11) NOT NULL,
  nascimento DATE NOT NULL,
  PRIMARY KEY (id)
);
