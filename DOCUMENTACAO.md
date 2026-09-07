# 📖 Documentação Técnica — CinePlay

Este documento complementa o `README.md` com detalhes técnicos do projeto: arquitetura, modelo de dados, variáveis de ambiente e, principalmente, a referência completa da API REST (endpoints, métodos HTTP, payloads e códigos de status).

---

## 1. Arquitetura do projeto

O repositório é um monorepo com três módulos:

```
cineplay/
├── cineplay/         # Módulo principal — API REST (Spring Boot)
├── commons-core/     # Módulo compartilhado — exceptions e error handler global
├── frontend/         # Aplicação React (Vite)
└── compose.yaml      # Container do MySQL (Docker Compose)
```

- **`cineplay/`** — aplicação Spring Boot (`CineplayApplication`), organizada em camadas:
  `controller` → `service` → `validations`/`repository` → `model` (JDBC puro, sem JPA).
- **`commons-core/`** — módulo Maven separado, importado pelo `cineplay/pom.xml`, contendo as exceptions de domínio (`NotFoundException`, `EmailAlreadyExistsException`, `AvaliacaoAlreadyExistsException`) e o `GlobalErrorHandlerAdvice`, que traduz essas exceptions em respostas HTTP padronizadas.
- **`frontend/`** — SPA em React 19 + React Router 7, consumindo a API via Axios (`src/services/*.jsx`).

## 2. Tecnologias

| Camada | Tecnologias |
|---|---|
| Back-end | Java 21, Spring Boot 4.1.1 (`spring-boot-starter-webmvc`, `spring-boot-starter-data-jdbc`), Maven |
| Banco de dados | MySQL 9 (via `mysql-connector-j`), schema aplicado por `schema.sql` (`spring.sql.init.mode=always`) |
| Front-end | React 19, Vite 8, React Router DOM 7, Axios |
| Infraestrutura | Docker / Docker Compose (container do MySQL) |

## 3. Variáveis de ambiente

### Back-end (`cineplay/src/main/resources/.env`)
```env
ENV_MYSQL_USER=
ENV_MYSQL_PASSWORD=
```
Usadas em `application.properties` para montar a conexão JDBC:
`jdbc:mysql://localhost:3306/cineplay?createDatabaseIfNotExist=true`.

### Docker Compose (raiz do projeto)
```env
ENV_ROOT_PASSWORD=
ENV_MYSQL_USER=
ENV_MYSQL_PASSWORD=
```

### Front-end (`frontend/.env`)
```env
VITE_API_URL=http://localhost:8080/v1
```
> Importante: todos os controllers usam o prefixo `v1` (`@RequestMapping("v1/...")`), então `VITE_API_URL` **precisa** incluir `/v1` — sem isso, todas as chamadas do `axios` (`src/services/Api.jsx`) resultam em 404.

## 4. Executando localmente

1. Suba o MySQL: `docker-compose up -d` (usa `compose.yaml`).
2. Back-end: `cd cineplay && ./mvnw spring-boot:run` — sobe em `http://localhost:8080`.
3. Front-end: `cd frontend && npm install && npm run dev` — sobe em `http://localhost:5173`.

O CORS dos controllers está fixo em `@CrossOrigin(origins = "http://localhost:5173")` — se o front rodar em outra porta/host, as requisições serão bloqueadas pelo navegador.

## 5. Modelo de dados

### `usuario`
| Campo | Tipo | Regras |
|---|---|---|
| `id_usuario` | INT, PK, auto-increment | — |
| `cpf` | CHAR(14), único | formato `000.000.000-00` |
| `nome` | VARCHAR(45) | obrigatório |
| `email` | VARCHAR(45), único | formato de e-mail válido |
| `senha` | VARCHAR(45) | mínimo 7 caracteres (`length() > 6`) |

Existe um usuário administrador pré-cadastrado via `schema.sql`:
`email: admin@cineplay.com`, `senha: admin123`. **Não há campo de "role" no banco** — o front-end decide se o usuário é admin comparando o e-mail digitado com essa string fixa (`FormLogin.jsx`), o que é apenas um controle de navegação no cliente, não uma autorização real no back-end.

### `filme`
| Campo | Tipo | Regras |
|---|---|---|
| `id_filme` | INT, PK, auto-increment | — |
| `titulo` | VARCHAR(45) | obrigatório |
| `sinopse` | VARCHAR(255) | obrigatório |
| `duracao` | INT | > 0 |
| `classificacao` | ENUM (`L10`, `L12`, `L14`) | obrigatório |
| `genero` | ENUM (`ACAO`, `COMEDIA`, `DRAMA`, `TERROR`, `ROMANCE`, `FICCAO`, `DOCUMENTARIO`, `ANIMACAO`) | obrigatório |
| `dt_lancamento` | DATE | obrigatório |
| `img_url` | VARCHAR(500) | obrigatório |

### `avaliacao`
Chave composta `(usuario_id_usuario, filme_id_filme)` — **um usuário só pode ter uma avaliação por filme**.

| Campo | Tipo | Regras |
|---|---|---|
| `usuario_id_usuario` | INT, PK/FK → `usuario` | `ON DELETE/UPDATE CASCADE` |
| `filme_id_filme` | INT, PK/FK → `filme` | `ON DELETE/UPDATE CASCADE` |
| `nota` | FLOAT | entre 0 e 5 |
| `comentario` | VARCHAR(500) | obrigatório, não pode ser vazio |

## 6. Sobre os métodos HTTP usados na API

A API segue o padrão REST — cada operação usa o verbo HTTP semanticamente correto:

| Método | Uso na API | Idempotente? | Tem corpo de requisição? |
|---|---|---|---|
| **GET** | Ler um recurso (`findAll`). Nunca altera dados no servidor. | Sim | Não |
| **POST** | Criar um novo recurso. Cada chamada cria um registro novo. | Não | Sim |
| **PUT** | Atualizar um recurso **existente por completo** (substitui todos os campos). Chamar duas vezes com o mesmo corpo produz o mesmo resultado. | Sim | Sim |
| **DELETE** | Remover um recurso existente. | Sim | Não |

> A API não implementa `PATCH` (atualização parcial) — todo `PUT` espera o objeto completo no corpo, mesmo que apenas um campo tenha mudado.

### Códigos de status retornados

| Status | Quando ocorre |
|---|---|
| `200 OK` | `GET`, `PUT` e o `POST` de login bem-sucedidos |
| `201 Created` | `POST` de criação bem-sucedido (filme, usuário, avaliação) |
| `204 No Content` | `DELETE` bem-sucedido |
| `400 Bad Request` | Validação de negócio falhou (`IllegalArgumentException`), e-mail duplicado, avaliação duplicada, ou `idUsuario`/`idFilme` da URL não bate com o corpo (atualização de avaliação) |
| `404 Not Found` | Recurso não encontrado por id, ou login com e-mail/senha inválidos |

Todo erro (400/404) retorna o corpo padronizado definido em `commons-core`:
```json
{
  "status": 400,
  "message": "Dados do filme inválidos"
}
```

## 7. Endpoints da API

**Base URL:** `http://localhost:8080/v1`

### 🎬 Filmes — `/filmes`

| Método | Rota | Descrição | Corpo da requisição | Resposta |
|---|---|---|---|---|
| `GET` | `/filmes` | Lista todos os filmes | — | `200` — `FilmeResponseDto[]` |
| `POST` | `/filmes` | Cadastra um novo filme | `FilmeRequestDto` | `201` — `FilmeResponseDto` |
| `PUT` | `/filmes/{id}` | Atualiza um filme existente | `FilmeRequestDto` | `200` — `FilmeResponseDto` |
| `DELETE` | `/filmes/{id}` | Remove um filme | — | `204` |

**`FilmeRequestDto` / `FilmeResponseDto`:**
```json
{
  "titulo": "string",
  "sinopse": "string",
  "duracao": 120,
  "classificacao": "L10 | L12 | L14",
  "genero": "ACAO | COMEDIA | DRAMA | TERROR | ROMANCE | FICCAO | DOCUMENTARIO | ANIMACAO",
  "dataLancamento": "2024-01-01",
  "imagem_url": "string (URL)"
}
```
> A resposta inclui também o campo `id`. Validação: todos os campos são obrigatórios e `duracao` deve ser maior que zero.

### 👤 Usuários — `/usuarios`

| Método | Rota | Descrição | Corpo da requisição | Resposta |
|---|---|---|---|---|
| `GET` | `/usuarios` | Lista todos os usuários | — | `200` — `UsuarioResponseDto[]` |
| `POST` | `/usuarios` | Cadastra um novo usuário | `UsuarioRequestDto` | `201` — `UsuarioResponseDto` |
| `PUT` | `/usuarios/{id}` | Atualiza um usuário existente | `UsuarioRequestDto` | `200` — `UsuarioResponseDto` |
| `DELETE` | `/usuarios/{id}` | Remove um usuário | — | `204` |

**`UsuarioRequestDto`:**
```json
{
  "cpf": "000.000.000-00",
  "nome": "string",
  "email": "usuario@dominio.com",
  "senha": "string (mín. 7 caracteres)"
}
```

**`UsuarioResponseDto`** (a senha nunca é retornada):
```json
{
  "id": 1,
  "cpf": "000.000.000-00",
  "nome": "string",
  "email": "usuario@dominio.com"
}
```
> Validações: CPF e e-mail seguem regex fixo; e-mail deve ser único (`400` se já existir); senha deve ter mais de 6 caracteres.

### ⭐ Avaliações — `/avaliacoes`

| Método | Rota | Descrição | Corpo da requisição | Resposta |
|---|---|---|---|---|
| `GET` | `/avaliacoes` | Lista todas as avaliações | — | `200` — `AvaliacaoResponseDto[]` |
| `POST` | `/avaliacoes` | Cria uma avaliação (1 por par usuário/filme) | `AvaliacaoRequestDto` | `201` — `AvaliacaoResponseDto` |
| `PUT` | `/avaliacoes/{idUsuario}/{idFilme}` | Atualiza a avaliação de um usuário para um filme | `AvaliacaoRequestDto` | `200` — `AvaliacaoResponseDto` |
| `DELETE` | `/avaliacoes/{idUsuario}/{idFilme}` | Remove a avaliação | — | `204` |

**`AvaliacaoRequestDto` / `AvaliacaoResponseDto`:**
```json
{
  "id_usuario": 1,
  "id_filme": 1,
  "nota": 5,
  "comentario": "string"
}
```
> No `PUT`, os `id_usuario`/`id_filme` da URL devem bater com os do corpo — caso contrário, `400 Bad Request`. `nota` deve estar entre 0 e 5; `comentario` é obrigatório. Tentar criar uma segunda avaliação para o mesmo par usuário/filme retorna `400` (`AvaliacaoAlreadyExistsException`).

### 🔑 Login — `/login`

| Método | Rota | Descrição | Corpo da requisição | Resposta |
|---|---|---|---|---|
| `POST` | `/login` | Autentica um usuário por e-mail/senha | `LoginRequestDto` | `200` — `UsuarioResponseDto` |

**`LoginRequestDto`:**
```json
{
  "email": "usuario@dominio.com",
  "senha": "string"
}
```
> Retorna `404 Not Found` se o e-mail não existir ou a senha não conferir (mensagem genérica "Email ou senha inválidos" nos dois casos, para não indicar qual campo está errado). Não há geração de token/sessão — o front-end apenas guarda o `UsuarioResponseDto` retornado em `localStorage` (`FormLogin.jsx`) e usa isso como estado de "logado".

## 8. Front-end — rotas e páginas

| Rota | Componente | Acesso |
|---|---|---|
| `/` | `Home` | Público |
| `/Cadastro` | `Cadastro` (`FormCadastro`) | Público |
| `/Login` | `Login` (`FormLogin`) | Público |
| `/CadastrarFilme` | `CadastrarFilme` (`FilmesCadastrados`) | Pensado para o e-mail `admin@cineplay.com` (redirecionamento após login), mas a rota **não é protegida** — qualquer pessoa pode acessar a URL diretamente |
| `/AvaliarFilmes` | `AvaliarFilmes` (`FilmesAvaliacao`) | Destino padrão após login de usuário comum |

Um indicador de carregamento global (`components/Loading.jsx`, controlado por `context/LoadingContext.jsx`) é exibido durante as operações de login, cadastro e criação/edição/exclusão de filmes e avaliações.

## 9. Limitações conhecidas / pontos de atenção

- Não há autenticação real (token/JWT/sessão) nem hashing de senha — a senha é comparada em texto puro (`LoginService.login`) e as rotas de admin não são protegidas no back-end.
- `Classificacao` e `Genero` são enums fechados — cadastrar um filme com um valor fora da lista resulta em erro de desserialização (`400`), não na mensagem de validação customizada.
