CREATE DATABASE IF NOT EXISTS cineplay;

CREATE TABLE IF NOT EXISTS usuario (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    cpf CHAR(14) NOT NULL,
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
    comentario VARCHAR(500) NOT NULL,
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

-- Usuário admin
INSERT IGNORE INTO usuario (cpf, nome, email, senha)
VALUES ('00000000000', 'Administrador', 'admin@cineplay.com', 'admin123');

-- Filmes de exemplo
INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'Interestelar', 'Um grupo de astronautas viaja através de um buraco de minhoca em busca de um novo lar para a humanidade.', 169, 'L12', 'FICCAO', '2014-11-06', 'https://image.tmdb.org/t/p/original/gEU2QniE6E77NI6lCU6MxlNBvIx.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'Interestelar');

INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'O Poderoso Chefão', 'A saga da família Corleone, uma das mais poderosas famílias da máfia italiana nos EUA.', 175, 'L14', 'DRAMA', '1972-03-24', 'https://image.tmdb.org/t/p/original/3bhkrj58Vtu7enYsRolD1fZdja1.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'O Poderoso Chefão');

INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'Toy Story', 'Um cowboy de brinquedo teme perder seu lugar favorito quando um novo boneco espacial chega.', 81, 'L10', 'ANIMACAO', '1995-11-22', 'https://image.tmdb.org/t/p/original/uXDfjJbdP4ijW5hWSBrPrlKpxab.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'Toy Story');

INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'O Iluminado', 'Um escritor aceita o cargo de zelador de inverno em um hotel isolado e começa a enlouquecer.', 146, 'L14', 'TERROR', '1980-05-23', 'https://image.tmdb.org/t/p/original/b6ko0IKC8MdYBBPkkA1aBPLe2yz.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'O Iluminado');

INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'De Repente 30', 'Uma garota de 13 anos deseja se tornar adulta e acorda no corpo de uma mulher de 30 anos.', 117, 'L10', 'COMEDIA', '2004-01-23', 'https://image.tmdb.org/t/p/original/8kSerFAtT7qxUnksHTz1c9xzoLo.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'De Repente 30');

INSERT INTO filme (titulo, sinopse, duracao, classificacao, genero, dt_lancamento, img_url)
SELECT 'Diário de uma Paixão', 'Um casal se apaixona nos anos 40 mas é separado pela guerra e diferenças sociais.', 123, 'L12', 'ROMANCE', '2004-06-25', 'https://image.tmdb.org/t/p/original/rNzQyW4f8B8cQeg7Dgj3n6eT5k9.jpg'
    WHERE NOT EXISTS (SELECT 1 FROM filme WHERE titulo = 'Diário de uma Paixão');