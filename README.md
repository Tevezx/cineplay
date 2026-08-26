# 🎬 CinePlay

Sistema web para gerenciamento e avaliação de filmes. Administradores podem cadastrar filmes na plataforma, enquanto usuários podem se cadastrar, avaliar filmes com nota e deixar comentários.

## 📋 Sobre o projeto

O CinePlay permite que:

- **Administradores** cadastrem, editem e removam filmes do catálogo (título, sinopse, gênero, ano, elenco, pôster, etc.);
- **Usuários** criem uma conta, façam login e avaliem os filmes com **nota** (ex: de 0 a 5 ou 0 a 10) e **comentário**;
- Todos os usuários visualizem o catálogo de filmes, suas avaliações e a nota média calculada a partir das avaliações da comunidade.

## 🚀 Tecnologias utilizadas

**Back-end**
- Java
- Spring Boot
- Spring JDBC

**Front-end**
- React

**Banco de dados**
- MySQL

**Infraestrutura**
- Docker / Docker Compose

## 🗂️ Estrutura do projeto

```
cineplay/
├── backend/          # API REST em Java + Spring Boot
├── frontend/          # Aplicação React
├── docker-compose.yml
└── README.md
```

## ⚙️ Funcionalidades

### Administrador
- [ ] Login como administrador
- [ ] Cadastrar novo filme
- [ ] Editar informações de um filme
- [ ] Remover filme do catálogo
- [ ] Listar todos os filmes cadastrados

### Usuário
- [ ] Cadastro de novo usuário
- [ ] Login de usuário
- [ ] Listar/buscar filmes disponíveis
- [ ] Visualizar detalhes de um filme
- [ ] Avaliar um filme (nota + comentário)
- [ ] Editar/excluir sua própria avaliação
- [ ] Visualizar nota média e comentários de outros usuários

## 🐳 Como executar o projeto com Docker

### Pré-requisitos
- [Docker](https://www.docker.com/) e [Docker Compose](https://docs.docker.com/compose/) instalados

### Passo a passo

1. Clone o repositório:
```bash
git clone https://github.com/Tevezx/cineplay.git
cd cineplay
```

2. Configure as variáveis de ambiente (crie um arquivo `.env` na raiz, se necessário):
```env
MYSQL_DATABASE=cineplay
MYSQL_ROOT_PASSWORD=sua_senha
MYSQL_USER=cine_user
MYSQL_PASSWORD=sua_senha
```

3. Suba os containers:
```bash
docker-compose up -d --build
```

4. Acesse a aplicação:
- Front-end: `http://localhost:5173`
- Back-end (API): `http://localhost:8080`
- MySQL: `localhost:3306`

> Certifique-se de que o MySQL esteja rodando localmente e configurado no `application.properties`/`application.yml` do back-end.

> Ajuste esta tabela conforme os endpoints reais implementados no seu projeto.

## 🗃️ Modelo de dados (resumo)

- **Usuário**: id, cpf, nome, email, senha, role (`ADMIN` / `USER`)
- **Filme**: id, título, sinopse, duracao, classificacao, genero, dt_lancamento, img_url
- **Avaliação**: id, nota, comentário, usuário (FK), filme (FK), data

## 🤝 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas alterações (`git commit -m 'Adiciona nova funcionalidade'`)
4. Faça o push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request
