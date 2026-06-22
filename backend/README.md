# Guia das Bandeiras - Backend

Backend Spring Boot API para o projeto Guia das Bandeiras.

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Maven
- Caffeine Cache
- Lombok
- JSoup (para web scraping futuro)

## Estrutura do Projeto

```
backend/
├── src/
│   ├── main/
│   │   ├── java/com/guia/bandeiras/
│   │   │   ├── GuiaDasBandeirasApplication.java
│   │   │   ├── config/
│   │   │   ├── controller/
│   │   │   ├── dto/
│   │   │   ├── exception/
│   │   │   └── service/
│   │   └── resources/
│   │       └── application.properties
│   └── test/
└── pom.xml
```

## Endpoints

### GET /api/search
Busca termos nos manuais das bandeiras.

**Parâmetros:**
- `query` (obrigatório): termo de busca
- `cardBrand` (opcional): filtro por bandeira (visa, mastercard, amex)

**Resposta:**
```json
{
  "query": "chargeback",
  "results": [
    {
      "cardBrand": "Visa",
      "title": "Chargeback Rules",
      "excerpt": "Descrição...",
      "sourceUrl": "https://...",
      "section": "Disputes"
    }
  ],
  "totalResults": 1
}
```

### GET /api/faq
Retorna perguntas frequentes.

**Resposta:**
```json
[
  {
    "question": "O que é chargeback?",
    "answer": "Descrição...",
    "category": "Conceitos Básicos"
  }
]
```

## Executar Localmente

```bash
cd backend
mvn spring-boot:run
```

O servidor estará disponível em `http://localhost:8080`

## Build

```bash
mvn clean package
```

## Testes

```bash
mvn test
```
