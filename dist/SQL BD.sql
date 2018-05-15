-- -----------------------------------------------------
-- Table pessoa
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS pessoa (
  id SERIAL NOT NULL,
  cpf VARCHAR(14) NOT NULL UNIQUE,
  nome VARCHAR(30) NOT NULL,
  sobrenome VARCHAR(60) NOT NULL,
  rg VARCHAR(11) NOT NULL,
  email VARCHAR(150) NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table telefone
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS telefone (
  id SERIAL NOT NULL,
  pessoa INT NOT NULL,
  numero VARCHAR(9) NOT NULL,
  ddd VARCHAR(2) NOT NULL,
  PRIMARY KEY (id, pessoa),
  CONSTRAINT fk_telefone_pessoa
    FOREIGN KEY (pessoa)
    REFERENCES pessoa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table usuario
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS usuario (
  pessoa INT NOT NULL,
  senha VARCHAR(20) NOT NULL,
  tipo VARCHAR(20) NOT NULL,
  ativo BOOLEAN NOT NULL,
  PRIMARY KEY (pessoa),
  CONSTRAINT fk_usuario_pessoa1
    FOREIGN KEY (pessoa)
    REFERENCES pessoa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS genero (
  id SERIAL NOT NULL,
  nome VARCHAR(45) NOT NULL UNIQUE,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table autor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS autor (
  id SERIAL NOT NULL,
  nome VARCHAR(30) NOT NULL,
  sobrenome VARCHAR(60) NOT NULL,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table idioma
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS idioma (
  id SERIAL NOT NULL,
  nome VARCHAR(45) NOT NULL UNIQUE,
  sigla VARCHAR(6) NOT NULL UNIQUE,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table editora
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS editora (
  id SERIAL NOT NULL,
  nome VARCHAR(50) NOT NULL UNIQUE,
  PRIMARY KEY (id));


-- -----------------------------------------------------
-- Table livro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro (
  id SERIAL NOT NULL,
  titulo VARCHAR(100) NOT NULL,
  publicacao DATE NOT NULL,
  paginas INT NULL,
  edicao VARCHAR(8) NULL,
  localizacao VARCHAR(35) NOT NULL,
  capa VARCHAR(150) NULL,
  idioma INT NOT NULL,
  editora INT NOT NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_livro_idioma1
    FOREIGN KEY (idioma)
    REFERENCES idioma (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_livro_editora1
    FOREIGN KEY (editora)
    REFERENCES editora (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table movimentacao
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS movimentacao (
  id SERIAL NOT NULL,
  pessoa INT NOT NULL,
  status VARCHAR(15) NOT NULL,
  data_reserva DATE NULL,
  data_retirada DATE NULL,
  data_devolver DATE NULL,
  observacao TEXT NULL,
  valor DECIMAL(7,2) NULL,
  PRIMARY KEY (id),
  CONSTRAINT fk_movimentacao_pessoa1
    FOREIGN KEY (pessoa)
    REFERENCES pessoa (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table copia_livro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS copia_livro (
  livro INT NOT NULL,
  identificador SERIAL NOT NULL,
  estado VARCHAR(45) NOT NULL,
  observacao TEXT NULL,
  PRIMARY KEY (livro, identificador),
  CONSTRAINT fk_copia_livro_livro1
    FOREIGN KEY (livro)
    REFERENCES livro (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table movimentacao_has_livro
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS movimentacao_has_livro (
  movimentacao INT NOT NULL,
  livro INT NOT NULL,
  identificador INT NOT NULL,
  status VARCHAR(15) NULL,
  data_devolucao DATE NULL,
  observacao TEXT NULL,
  multa DECIMAL(7,2) NULL,
  PRIMARY KEY (movimentacao, livro, identificador),
  CONSTRAINT fk_movimentacao_has_livro_movimentacao1
    FOREIGN KEY (movimentacao)
    REFERENCES movimentacao (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_movimentacao_has_livro_copia_livro1
    FOREIGN KEY (livro , identificador)
    REFERENCES copia_livro (livro , identificador)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table valor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS valor (
  diaria DECIMAL(5,2) NOT NULL,
  multa DECIMAL(5,2) NOT NULL);


-- -----------------------------------------------------
-- Table livro_has_autor
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro_has_autor (
  livro INT NOT NULL,
  autor INT NOT NULL,
  PRIMARY KEY (livro, autor),
  CONSTRAINT fk_livro_has_autor_livro1
    FOREIGN KEY (livro)
    REFERENCES livro (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_livro_has_autor_autor1
    FOREIGN KEY (autor)
    REFERENCES autor (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- -----------------------------------------------------
-- Table livro_has_genero
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS livro_has_genero (
  livro INT NOT NULL,
  genero INT NOT NULL,
  PRIMARY KEY (livro, genero),
  CONSTRAINT fk_livro_has_genero_livro1
    FOREIGN KEY (livro)
    REFERENCES livro (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT fk_livro_has_genero_genero1
    FOREIGN KEY (genero)
    REFERENCES genero (id)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);


-- Criação usuário admin

INSERT INTO pessoa VALUES (DEFAULT, '028.012.280-28', 'Ramon', 'Hentges', '1104888522', 'r.hentges@hotmail.com');
INSERT INTO usuario VALUES(1, '123', 'Administrador', true);
INSERT INTO valor VALUES (0, 1);