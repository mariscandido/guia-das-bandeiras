# Guia das Bandeiras

Centralize informações dos manuais públicos das principais bandeiras de cartão (Visa, MasterCard, Amex). Pesquise termos como "chargeback", "MCC", "autorização" e obtenha trechos relevantes dos manuais oficiais.

## 🏗️ Arquitetura

- **Frontend**: Angular 17 com Angular Material
- **Backend**: Spring Boot 3 + Java 17
- **Cache**: Caffeine (cache local)
- **Containerização**: Docker Compose

## 📁 Estrutura do Projeto

```
guia-das-bandeiras/
├── backend/                 # Spring Boot API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/guia/bandeiras/
│   │   │   │   ├── config/
│   │   │   │   ├── controller/
│   │   │   │   ├── dto/
│   │   │   │   ├── exception/
│   │   │   │   └── service/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── Dockerfile
│   └── README.md
├── frontend/                # Angular Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/
│   │   │   ├── services/
│   │   │   └── ...
│   ├── angular.json
│   ├── package.json
│   ├── proxy.conf.json
│   ├── Dockerfile
│   ├── nginx.conf
│   └── README.md
├── docs/
│   └── arquitetura.md
├── docker-compose.yml
├── .gitignore
└── README.md
```

## 🚀 Como Executar

### Pré-requisitos

- Java 17+
- Node.js 18+
- Maven 3.9+
- Docker e Docker Compose (opcional)

### Executar Localmente

#### Backend

```bash
cd backend
mvn spring-boot:run
```

O backend estará disponível em `http://localhost:8080`

#### Frontend

```bash
cd frontend
npm install
npm start
```

O frontend estará disponível em `http://localhost:4200`

### Executar com Docker Compose

```bash
docker-compose up --build
```

- Frontend: `http://localhost:4200`
- Backend: `http://localhost:8080`

## 🔌 Endpoints da API

### GET /api/search
Busca termos nos manuais das bandeiras.

**Parâmetros:**
- `query` (obrigatório): termo de busca
- `cardBrand` (opcional): filtro por bandeira (visa, mastercard, amex)

**Exemplo:**
```bash
curl "http://localhost:8080/api/search?query=chargeback"
```

### GET /api/faq
Retorna perguntas frequentes.

**Exemplo:**
```bash
curl "http://localhost:8080/api/faq"
```

## 🎨 Funcionalidades

- **Busca unificada**: Pesquise termos e veja resultados de todas as bandeiras
- **Resumo automático**: Trechos relevantes dos manuais
- **Links diretos**: Acesso ao manual oficial
- **FAQ inteligente**: Perguntas frequentes organizadas por categoria
- **UI responsiva**: Funciona em desktop e mobile
- **Cache**: Respostas em cache para performance

## 🔮 Evoluções Futuras

- [ ] Autenticação para usuários internos
- [ ] Chatbot embutido com base nos manuais
- [ ] Exportar respostas em PDF
- [ ] Persistência em banco de dados
- [ ] Deploy em nuvem (Azure/AWS)
- [ ] Web scraping real das APIs das bandeiras

## 📖 Documentação

- [Arquitetura](docs/arquitetura.md) - Detalhes técnicos e diagramas
- [Backend README](backend/README.md) - Documentação do backend
- [Frontend README](frontend/README.md) - Documentação do frontend

## 🛠️ Tecnologias

### Backend
- Java 17
- Spring Boot 3.2.0
- Maven
- Caffeine Cache
- Lombok
- JSoup

### Frontend
- Angular 17
- Angular Material
- TypeScript
- RxJS
- HttpClient

## 📝 Licença

Este projeto é para fins educacionais e demonstrativos.
