# 🤝 Donation ONG Platform

> Plataforma de doações para ONGs desenvolvida com arquitetura de microserviços em Java Spring Boot.

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.4-brightgreen)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-16-blue)
![RabbitMQ](https://img.shields.io/badge/RabbitMQ-3.13-orange)
![Docker](https://img.shields.io/badge/Docker-Compose-blue)

---

## 📌 Sobre o Projeto

A **Donation ONG Platform** é uma API REST desenvolvida com arquitetura de microserviços que conecta doadores a ONGs, permitindo o cadastro de campanhas de arrecadação e o registro de doações em tempo real.

Quando uma doação é realizada, o sistema dispara automaticamente uma notificação via **RabbitMQ**, demonstrando comunicação assíncrona entre serviços independentes.

O projeto foi desenvolvido com foco em boas práticas de engenharia de software, separação de responsabilidades e tecnologias amplamente utilizadas no mercado.

---

## 🏗️ Arquitetura

```
                        ┌─────────────────┐
                        │   API Gateway   │
                        │  (futuro)       │
                        └────────┬────────┘
                                 │
          ┌──────────────────────┼──────────────────────┐
          │                      │                       │
          ▼                      ▼                       ▼
┌─────────────────┐   ┌─────────────────┐   ┌─────────────────┐
│  user-service   │   │campaign-service │   │donation-service │
│   porta 8081    │   │   porta 8082    │   │   porta 8083    │
│                 │   │                 │   │        │        │
│  userdb (PG)        │ campaigndb (PG) │   │ donationdb (PG) │
└─────────────────┘   └─────────────────┘   └────────┬────────┘
                                                      │
                                               RabbitMQ Queue
                                              donation.queue
                                                      │
                                                      ▼
                                          ┌─────────────────────┐
                                          │ notification-service│
                                          │     porta 8084      │
                                          └─────────────────────┘
```

---

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 4.0.4**
- **Spring Data JPA / Hibernate**
- **Spring AMQP (RabbitMQ)**
- **PostgreSQL 16**
- **RabbitMQ 3.13**
- **Docker e Docker Compose**
- **Lombok**
- **Maven**

---

## 📦 Microserviços

| Serviço | Porta | Responsabilidade | Banco |
|---|---|---|---|
| `user-service` | 8081 | Cadastro de doadores e ONGs | userdb |
| `campaign-service` | 8082 | Gestão de campanhas de arrecadação | campaigndb |
| `donation-service` | 8083 | Registro de doações + publicação de eventos | donationdb |
| `notification-service` | 8084 | Consumo de eventos e notificações | — |

---

## ▶️ Como Rodar o Projeto

### Pré-requisitos

- [Docker Desktop](https://www.docker.com/products/docker-desktop) instalado e rodando

### Passo 1 — Clone o repositório

```bash
git clone https://github.com/vitorfcgomes/donation-ong-platform.git
cd donation-ong-platform
```

### Passo 2 — Compile os serviços

```bash
cd user-service && mvn clean package -DskipTests && cd ..
cd campaign-service && mvn clean package -DskipTests && cd ..
cd donation-service && mvn clean package -DskipTests && cd ..
cd notification-service && mvn clean package -DskipTests && cd ..
```

### Passo 3 — Suba todos os serviços

```bash
docker-compose up --build
```

### Passo 4 — Crie os bancos de dados

```bash
docker exec -it postgres psql -U postgres -c "CREATE DATABASE userdb;"
docker exec -it postgres psql -U postgres -c "CREATE DATABASE campaigndb;"
docker exec -it postgres psql -U postgres -c "CREATE DATABASE donationdb;"
```

### Passo 5 — Acesse os serviços

| Serviço | URL |
|---|---|
| user-service | http://localhost:8081 |
| campaign-service | http://localhost:8082 |
| donation-service | http://localhost:8083 |
| notification-service | http://localhost:8084 |
| RabbitMQ Painel | http://localhost:15672 (guest/guest) |

---

## 🔎 Endpoints da API

### 👤 user-service — `http://localhost:8081`

#### Usuários
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/users` | Listar todos os usuários |
| GET | `/users/{id}` | Buscar usuário por id |
| POST | `/users` | Cadastrar novo usuário |
| PUT | `/users/{id}` | Atualizar usuário |
| DELETE | `/users/{id}` | Deletar usuário |

**Exemplo de requisição:**
```json
POST /users
{
    "name": "João Silva",
    "email": "joao@email.com",
    "phone": "31999999999",
    "type": "DONOR"
}
```

#### ONGs
| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/ongs` | Listar todas as ONGs |
| GET | `/ongs/{id}` | Buscar ONG por id |
| POST | `/ongs` | Cadastrar nova ONG |
| PUT | `/ongs/{id}` | Atualizar ONG |
| DELETE | `/ongs/{id}` | Deletar ONG |

**Exemplo de requisição:**
```json
POST /ongs
{
    "name": "ONG Esperança",
    "cnpj": "12.345.678/0001-99",
    "description": "ONG dedicada a ajudar famílias carentes",
    "category": "Assistência Social"
}
```

---

### 📢 campaign-service — `http://localhost:8082`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/campaigns` | Listar todas as campanhas |
| GET | `/campaigns/{id}` | Buscar campanha por id |
| POST | `/campaigns` | Criar nova campanha |
| PUT | `/campaigns/{id}` | Atualizar campanha |
| PATCH | `/campaigns/{id}/raised?amount=` | Atualizar valor arrecadado |
| DELETE | `/campaigns/{id}` | Deletar campanha |

**Exemplo de requisição:**
```json
POST /campaigns
{
    "title": "Campanha de Natal",
    "description": "Arrecadação de alimentos para famílias carentes",
    "goal": 5000.00,
    "raised": 0.00,
    "deadline": "2026-12-25",
    "status": "ACTIVE",
    "ongId": 1
}
```

---

### 💸 donation-service — `http://localhost:8083`

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/donations` | Listar todas as doações |
| GET | `/donations/{id}` | Buscar doação por id |
| GET | `/donations/campaign/{id}` | Listar doações por campanha |
| GET | `/donations/user/{id}` | Listar doações por usuário |
| POST | `/donations` | Registrar nova doação |
| DELETE | `/donations/{id}` | Deletar doação |

**Exemplo de requisição:**
```json
POST /donations
{
    "amount": 500.00,
    "message": "Feliz Natal a todos!",
    "userId": 1,
    "campaignId": 1
}
```

---

## 🔄 Fluxo de uma Doação

```
POST /donations
    → donation-service salva no banco
    → publica DonationEvent no RabbitMQ (donation.exchange)
    → notification-service consome da donation.queue
    → loga a notificação no console
```

---

## 🗂️ Estrutura do Projeto

```
donation-ong-platform/
├── user-service/
│   └── src/main/java/com/doacaoong/user_service/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── entities/
│       │   └── enums/
│       └── exceptions/
├── campaign-service/
│   └── src/main/java/com/doacaoong/campaign_service/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── entities/
│       │   └── enums/
│       └── exceptions/
├── donation-service/
│   └── src/main/java/com/doacaoong/donation_service/
│       ├── controller/
│       ├── service/
│       ├── repository/
│       ├── entities/
│       ├── event/
│       ├── config/
│       └── exceptions/
├── notification-service/
│   └── src/main/java/com/doacaoong/notification_service/
│       ├── consumer/
│       ├── event/
│       └── config/
├── docker-compose.yml
└── README.md
```

---

## 👨‍💻 Autor

Desenvolvido por **Vitor Carvalho**

🎓 Estudante de Desenvolvimento de Software
💻 Foco em Back-End com Java e Spring Boot
🚀 Em constante evolução e aprendizado

[![LinkedIn](https://img.shields.io/badge/LinkedIn-Vitor%20Carvalho-0A66C2?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/vitorfcgomes/)
[![GitHub](https://img.shields.io/badge/GitHub-vitorfcgomes-181717?style=for-the-badge&logo=github&logoColor=white)](https://github.com/vitorfcgomes)
