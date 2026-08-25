# 🎬 Sistema de Reserva de Assentos para Cinema

Sistema para gerenciamento de cinema com controle de acesso por perfil de usuário (**Administrador** e **Cliente**), permitindo o cadastro de filmes, salas e sessões, além da reserva de assentos pelos clientes.

## 📋 Sobre o Projeto

O sistema permite que administradores gerenciem o conteúdo do cinema (filmes, salas com seus assentos e sessões), enquanto clientes podem se cadastrar, visualizar as sessões disponíveis e reservar assentos específicos, evitando conflitos de reservas duplicadas.

## ✨ Funcionalidades

### 🔑 Autenticação
- Cadastro de usuário
- Login com diferenciação de perfil (Admin / Cliente)

### 👨‍💼 Administrador
- Cadastrar, editar e remover filmes
- Cadastrar salas e definir seus assentos
- Cadastrar sessões (filme + sala + data/horário)
- Visualizar reservas realizadas

### 🎟️ Cliente
- Visualizar filmes e sessões disponíveis
- Selecionar sessão e reservar assento(s) disponíveis
- Visualizar e cancelar suas próprias reservas

## 🛠️ Tecnologias Utilizadas

- **Java** – linguagem principal do sistema
- **JDBC** – conexão e manipulação do banco de dados
- **SQL** – modelagem e consultas ao banco de dados
- **React** – sistema visual
- **Docker** – imagem de tecnologias e build

## ⚙️ Pré-requisitos

- Java JDK21+
- Banco de dados relacional (MySQL)
- Driver JDBC correspondente ao banco utilizado

## 📄 Licença

Este projeto é de uso acadêmico/pessoal.
