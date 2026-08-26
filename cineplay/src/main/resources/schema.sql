CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    cpf CHAR(11) NOT NULL,
    nome VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL,
    senha VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_usuario),
    UNIQUE INDEX cpf_UNIQUE (cpf ASC),
    UNIQUE INDEX email_UNIQUE (email ASC)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS filme (
    id_filme INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(45) NOT NULL,
    sinopse VARCHAR(255) NULL,
    duracao INT NULL,
    classificacao VARCHAR(45) NULL,
    genero VARCHAR(45) NULL,
    dt_lancamento DATE NULL,
    img_url VARCHAR(500) NULL,
    PRIMARY KEY (id_filme)
) ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS avaliacao (
    usuario_id_usuario INT NOT NULL,
    filme_id_filme INT NOT NULL,
    nota FLOAT NOT NULL,
    PRIMARY KEY (usuario_id_usuario, filme_id_filme),
    INDEX fk_avaliacao_usuario_idx (usuario_id_usuario ASC),
    INDEX fk_avaliacao_filme_idx (filme_id_filme ASC),
    CONSTRAINT fk_avaliacao_usuario
        FOREIGN KEY (usuario_id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE
        ON UPDATE CASCADE,
    CONSTRAINT fk_avaliacao_filme
        FOREIGN KEY (filme_id_filme)
        REFERENCES filme (id_filme)
        ON DELETE CASCADE
        ON UPDATE CASCADE
) ENGINE = InnoDB;