# [🎥 Explicação em Vídeo - Como Usar a API](https://youtu.be/m_ATTcD1sqc)

# 🚀 Desafio Backend - Sistema de Contas a Pagar

## 📄 Descrição

Este projeto é uma implementação de uma API REST para um sistema simples de contas a pagar. O sistema foi desenvolvido como parte de um desafio técnico e tem como objetivo fornecer uma API para realizar o CRUD de contas.

## ⚙️ Funcionalidades

- **CRUD de Contas a Pagar:**
   - 📥 Cadastrar nova conta
   - ✏️ Atualizar uma conta existente
   - 🔄 Alterar a situação da conta (Pendente, Pago, etc.)
   - 🔍 Consultar lista de contas com filtros (Data de Vencimento, Descrição)
   - 🔎 Consultar conta por ID
   - 💰 Consultar valor total pago por período

- **Importação de Contas via CSV:**
   - 📂 Importar lote de contas a pagar via upload de arquivo CSV

- **Autenticação e Segurança:**
   - 🔐 Mecanismo de autenticação para proteger as APIs

- **Estrutura de Banco de Dados:**
   - 🗄️ Utilização do **PostgreSQL** com migrações gerenciadas pelo **Flyway**

- **Persistência e Resiliência:**
   - 🛡️ Para garantir a persistência dos dados em caso de indisponibilidade do sistema, foi utilizado um mecanismo de enfileiramento de mensagens assíncronas, assegurando que as contas pagas não sejam perdidas.

- **Testes Unitários:**
   - 🧪 Implementação de testes unitários para garantir a qualidade do código

## 🔗 Endpoints

### **Autenticação**
- **POST `/auth/login`** - Gera tokens de acesso e refresh tokens.
- **POST `/auth/refresh`** - Gera um novo token de acesso usando um refresh token.

### **Contas a Pagar**

#### **Endpoints Obrigatórios**
- **POST `/contas/manage`** - Cadastrar uma nova conta.
- **PUT `/contas/manage/{id}`** - Atualizar uma conta existente.
- **PATCH `/contas/manage/{id}/situacao`** - Alterar a situação de uma conta.
- **GET `/contas/manage`** - Obter lista de contas com filtros (Data de Vencimento, Descrição).
- **GET `/contas/manage/{id}`** - Obter conta por ID.
- **GET `/contas/manage/total-pago`** - Obter valor total pago por período.

#### **Endpoints Adicionais**
- **DELETE `/contas/manage/{id}`** - Deletar uma conta.
- **GET `/contas/manage/contagem-de-contas`** - Obter contagem total de contas.
- **GET `/contas/manage/lista-de-contas-por-situacao`** - Obter lista de contas filtradas por situação.

### **Importação de Contas**
- **POST `/contas/import/upload`** - Importar contas via arquivo CSV.

## 🛠️ Tecnologias Utilizadas

- **☕ Java 21**
- **Spring Framework:**
   - 🥾 Spring Boot
   - 📊 Spring Data JPA
   - 🔒 Spring Security
   - 📨 Spring Kafka
- **🐘 PostgreSQL**
- **🚀 Flyway**
- **🧪 JUnit 5** e **Mockito** para testes unitários
- **📜 Lombok**
- **🔑 JWT (Json Web Token)** para autenticação
- **📄 OpenCSV** para manipulação de arquivos CSV

## 🧰 Requisitos para Execução

- **Java 21** ou superior
- **Maven**
- **Docker** e **Docker Compose**

## 🚀 Como Executar o Projeto

### 1. Clonar o repositório

### 1. Clonar o repositório

```bash
git clone https://github.com/FelipeAumannRS/totvs-desafio-felipe-aumann.git
cd totvs-desafio-felipe-aumann
```

### 2. Construir o projeto e criar a imagem Docker

```bash
mvn clean install
docker build -t totvs-app .
```

### 3. Subir a aplicação com Docker Compose
```bash
docker-compose up --build
```

Isso iniciará a aplicação, o banco de dados PostgreSQL e os outros serviços necessários em containers Docker.

## 💬 Considerações Finais

Este projeto foi desenvolvido com a intenção de aproximar a aplicação da realidade, onde serviços intermitentes são comuns. Para garantir a integridade dos dados, foi implementado um mecanismo de enfileiramento de mensagens assíncronas, assegurando que as operações de salvar e editar contas sejam realizadas de forma confiável, mesmo em caso de indisponibilidade temporária dos serviços.