# Backend Softwave

Este é o backend do projeto **Softwave**, desenvolvido com **Java** e **Spring Boot** para gerenciar e processar dados de forma eficiente. O projeto utiliza tecnologias modernas para garantir escalabilidade e desempenho.

## Funcionalidades

- Gerenciamento de usuários.
- Processamento de dados em tempo real.
- Integração com APIs externas.
- Autenticação e autorização seguras.

## Tecnologias Utilizadas
- **Java 21**: Linguagem de programação principal.
- **Spring Boot**: Framework para desenvolvimento de aplicações Java.
- **Spring Data JPA**: Para acesso a dados e persistência.
- **MySQL**: Banco de dados relacional para armazenamento de dados.
- **Maven**: Gerenciador de dependências e build do projeto.
- **JUnit**: Framework para testes automatizados.
- **Swagger**: Para documentação da API.
- **Git**: Controle de versão do código-fonte.
- **Insomnia**: Para testes de API.
- **IntelliJ IDEA**: IDE recomendada para desenvolvimento Java.

## Pré-requisitos

Antes de começar, verifique se você possui os seguintes pré-requisitos instalados em sua máquina:

- [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (versão 21 ou superior)
- [Maven](https://maven.apache.org/)
- **MySQL** para o banco de dados
- **Git** para controle de versão

## Instalação

1. Clone o repositório:
   ```bash
   git clone https://github.com/SoftWave-SPTech/backend-softwave.git
   cd backend-softwave
   ```

2. Configure as variáveis de ambiente:
   - Crie um arquivo `application.properties` ou `application.yml` na pasta `src/main/resources`.
   - Adicione as configurações necessárias, como credenciais do banco de dados e outras propriedades.

3. Compile o projeto:
   ```bash
   mvn clean install
   ```

## Execução

### Ambiente de Desenvolvimento

Para rodar o projeto em ambiente de desenvolvimento, utilize:

```bash
mvn spring-boot:run
```

### Ambiente de Produção

Para rodar o projeto em produção, gere o arquivo `.jar` e execute-o:

```bash
java -jar target/backend-softwave-0.0.1-SNAPSHOT.jar
```

## Estrutura do Projeto

```plaintext
backend-softwave/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── softwave/   # Código-fonte principal
│   │   ├── resources/
│   │       ├── application.properties  # Configurações do Spring Boot
│   │       └── static/                 # Arquivos estáticos (se aplicável)
│   └── test/                           # Testes automatizados
├── pom.xml                             # Configuração do Maven
└── README.md                           # Documentação do projeto
```

## Licença

Este projeto está licenciado sob a [MIT License](LICENSE).

## Contato

Caso tenha dúvidas ou sugestões, entre em contato:

- **Nome:** Softwave
- **Email:** suporte.softwave@gmail.com
