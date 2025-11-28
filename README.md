# Backend SoftWave - Sistema Principal

API REST principal do sistema SoftWave desenvolvida em Spring Boot, responsável pela gestão completa de clientes, advogados, processos e documentos jurídicos.

## Tecnologias Utilizadas

![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![RabbitMQ](https://img.shields.io/badge/Rabbitmq-FF6600?style=for-the-badge&logo=rabbitmq&logoColor=white)

### Dependências Principais

- **Spring Boot 3.4.3** - Framework principal
- **Spring Data JPA** - Persistência de dados
- **Spring Security** - Autenticação e autorização
- **Spring Boot Mail** - Envio de emails
- **MySQL Connector** - Driver do banco de dados
- **JWT (jjwt) 0.11.5** - Tokens de autenticação
- **Swagger/OpenAPI 2.3.0** - Documentação da API
- **Spring AMQP** - Integração com RabbitMQ
- **Lombok** - Redução de boilerplate

## Requisitos do Sistema

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)

- **Java** >= 21
- **Maven** >= 3.8.0
- **MySQL** >= 8.0
- **Docker** (opcional)

## Instalação e Configuração

### 1. Clone o Repositório

```bash
git clone <repository-url>
cd backend-softwave
```

### 2. Configuração do Banco de Dados

Crie o banco de dados MySQL único do projeto:

```sql
CREATE DATABASE softwave_db;
CREATE USER 'softwave'@'localhost' IDENTIFIED BY 'softwave123';
GRANT ALL PRIVILEGES ON softwave_db.* TO 'softwave'@'localhost';
FLUSH PRIVILEGES;
```

### 3. Configuração de Ambiente

Crie um arquivo `application-local.yml` em `src/main/resources/`:

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/softwave_db
    username: softwave
    password: softwave123
    driver-class-name: com.mysql.cj.jdbc.Driver
  
  jpa:
    hibernate:
      ddl-auto: update
    properties:
      hibernate:
        dialect: org.hibernate.dialect.MySQL8Dialect
        format_sql: true
    show-sql: true
  
  mail:
    host: smtp.gmail.com
    port: 587
    username: ${MAIL_USERNAME:your-email@gmail.com}
    password: ${MAIL_PASSWORD:your-app-password}
    properties:
      mail:
        smtp:
          auth: true
          starttls:
            enable: true

jwt:
  secret: ${JWT_SECRET:softwave-super-secret-key-2025}
  validity: ${JWT_VALIDITY:3600}

server:
  port: 8080

cors:
  allowed-origins: ${CORS_ALLOWED_ORIGINS:http://localhost:5173}

# RabbitMQ Configuration
rabbitmq:
  host: ${RABBITMQ_HOST:localhost}
  port: ${RABBITMQ_PORT:5672}
  username: ${RABBITMQ_USERNAME:guest}
  password: ${RABBITMQ_PASSWORD:guest}

# AWS Configuration
aws:
  access-key: ${AWS_ACCESS_KEY:your-access-key}
  secret-key: ${AWS_SECRET_KEY:your-secret-key}
  region: ${AWS_REGION:us-east-1}
  s3:
    bucket: ${AWS_S3_BUCKET:softwave-arquivos}

# Microservices URLs
microservice:
  s3:
    url: ${MICROSERVICE_S3_URL:http://localhost:8091}
  auth:
    url: ${MICROSERVICE_AUTH_URL:http://localhost:8083}
  gemini:
    url: ${MICROSERVICE_GEMINI_URL:http://localhost:8092}
  consultas:
    url: ${MICROSERVICE_CONSULTAS_URL:http://localhost:8084}
```

### 4. Variáveis de Ambiente

Configure as seguintes variáveis de ambiente:

```bash
export JWT_SECRET=seu-jwt-secret-super-seguro
export MAIL_USERNAME=seu-email@gmail.com
export MAIL_PASSWORD=sua-senha-app-gmail
export AWS_ACCESS_KEY=sua-chave-acesso-aws
export AWS_SECRET_KEY=sua-chave-secreta-aws
export RABBITMQ_HOST=localhost
export RABBITMQ_USERNAME=guest
export RABBITMQ_PASSWORD=guest
```

### 5. Instalação das Dependências

```bash
mvn clean install
```

### 6. Executar a Aplicação

#### Modo Desenvolvimento

```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

#### Build e Execução

```bash
mvn clean package
java -jar target/backend-SoftWave-0.0.2-SNAPSHOT.jar
```

#### Docker (Opcional)

```bash
docker-compose up -d
```

A aplicação estará disponível em: http://localhost:8080

## Estrutura do Projeto

```
src/
├── main/
│   ├── java/com/project/softwave/
│   │   ├── config/          # Configurações (Security, CORS, etc.)
│   │   ├── controller/      # Controllers REST
│   │   ├── dto/            # Data Transfer Objects
│   │   ├── entity/         # Entidades JPA
│   │   ├── repository/     # Repositórios JPA
│   │   ├── service/        # Regras de negócio
│   │   ├── security/       # Configurações de segurança
│   │   ├── exception/      # Tratamento de exceções
│   │   └── util/          # Utilitários
│   └── resources/
│       ├── application.yml          # Configuração principal
│       ├── application-docker.yml   # Configuração Docker
│       └── application-prod.yml     # Configuração produção
└── test/                   # Testes unitários e integração
```

## Endpoints Principais

### Autenticação
- `POST /auth/login` - Login de usuários
- `POST /auth/register` - Cadastro de usuários
- `POST /auth/refresh` - Renovação de token

### Usuários
- `GET /usuarios` - Listar usuários
- `GET /usuarios/{id}` - Buscar usuário por ID
- `PUT /usuarios/{id}` - Atualizar usuário
- `DELETE /usuarios/{id}` - Deletar usuário

### Clientes
- `GET /clientes` - Listar clientes
- `POST /clientes` - Criar cliente
- `PUT /clientes/{id}` - Atualizar cliente
- `DELETE /clientes/{id}` - Deletar cliente

### Processos
- `GET /processos` - Listar processos
- `POST /processos` - Criar processo
- `PUT /processos/{id}` - Atualizar processo
- `GET /processos/cliente/{clienteId}` - Processos por cliente

### Documentos
- `GET /documentos` - Listar documentos
- `POST /documentos/upload` - Upload de documento
- `GET /documentos/{id}/download` - Download de documento

## Documentação da API

A documentação completa da API está disponível via Swagger UI:

- **Desenvolvimento**: http://localhost:8080/swagger-ui.html
- **JSON**: http://localhost:8080/v3/api-docs

## Integração com Microserviços

### Auth Service (Porta 8083)
- Autenticação e autorização
- Envio de emails
- Recuperação de senha

### S3 Service (Porta 8091)
- Upload de arquivos
- Gerenciamento de documentos
- Integração AWS S3

### Gemini Service (Porta 8092)
- Análise de documentos com IA
- Processamento de texto
- Insights jurídicos

### Consultas Service (Porta 8084)
- Consultas processuais
- Integração com APIs externas
- Processamento assíncrono

## Configuração do RabbitMQ

Para mensageria assíncrona, configure o RabbitMQ:

```bash
# Instalar via Docker
docker run -d --hostname rabbitmq --name rabbitmq-server \
  -p 5672:5672 -p 15672:15672 \
  -e RABBITMQ_DEFAULT_USER=softwave \
  -e RABBITMQ_DEFAULT_PASS=softwave123 \
  rabbitmq:3-management

# Acessar painel: http://localhost:15672
# User: softwave / Pass: softwave123
```

## Testes

### Executar Testes

```bash
# Todos os testes
mvn test

# Testes de integração
mvn test -Dtest=**/*IntegrationTest

# Cobertura de código
mvn jacoco:report
```

### Testes com H2 (Banco em memória)

Os testes utilizam H2 automaticamente. Configuração em `application-test.yml`.

## Profiles de Execução

### Local Development
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=local
```

### Docker
```bash
mvn spring-boot:run -Dspring-boot.run.profiles=docker
```

### Production
```bash
java -jar app.jar --spring.profiles.active=prod
```

## Troubleshooting

### Problemas Comuns

1. **Erro de conexão MySQL**: Verifique se o MySQL está rodando e as credenciais estão corretas
2. **Porta 8080 ocupada**: Altere a porta em `application.yml`
3. **Erro JWT**: Verifique se a variável `JWT_SECRET` está configurada
4. **CORS Error**: Configure `cors.allowed-origins` com a URL do frontend

### Logs de Debug

Para logs detalhados, adicione em `application.yml`:

```yaml
logging:
  level:
    com.project.softwave: DEBUG
    org.springframework.security: DEBUG
    org.hibernate.SQL: DEBUG
```

## Contribuição

1. Faça fork do projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-funcionalidade`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova funcionalidade'`)
4. Push para a branch (`git push origin feature/nova-funcionalidade`)
5. Abra um Pull Request

## Licença

Este projeto é propriedade da SoftWave SPTech e destina-se ao uso exclusivo do escritório Lauriano & Leão Sociedade de Advogados.

---

**Desenvolvido por:** SoftWave SPTech  
**Versão:** 0.0.2-SNAPSHOT  
**Data:** 2025