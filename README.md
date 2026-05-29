# Agendamento de Transferências Financeiras

API REST para agendamento de transferências financeiras com cálculo automático de taxas. Desenvolvida com Spring Boot 2.7 e Java 11, banco de dados H2 em memória.

## Tecnologias

- Java 11 / Spring Boot 2.7
- Spring Data JPA + H2
- Bean Validation
- Lombok
- Springdoc OpenAPI

## Decisões arquiteturais

A lógica de cálculo de taxas foi isolada em `TaxaService` para facilitar os testes unitários sem precisar subir o contexto do Spring. As validações de entrada ficam no DTO via Bean Validation, e os erros são tratados centralizadamente no `GlobalExceptionHandler`.

## Como executar

```bash
./mvnw spring-boot:run
```

A API sobe na porta `8080`.

## Endpoints

- `POST /api/transferencias`  (agendar uma transferência)
- `GET /api/transferencias` (listar todos os agendamentos)



## Banco de dados

Console H2: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:transferenciasdb`
- Usuário: `sa` / Senha: _(vazia)_

Swagger: `http://localhost:8080/swagger-ui/index.html`
