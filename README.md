# Guia das Bandeiras

Centralize informações dos manuais públicos das principais bandeiras de cartão (Visa, MasterCard, Amex, Elo, Hipercard, Discover). Pesquise termos como "chargeback", "MCC", "autorização" e obtenha trechos relevantes dos manuais oficiais.

## 🏗️ Arquitetura

- **Frontend**: Angular 17 com Angular Material
- **Backend**: Spring Boot 3 + Java 17
- **Banco de Dados**: PostgreSQL
- **Cache**: Caffeine (cache local)
- **Autenticação**: JWT com Spring Security
- **Containerização**: Docker Compose
- **CI/CD**: GitHub Actions

## 📁 Estrutura do Projeto

```
guia-das-bandeiras/
├── backend/                 # Spring Boot API
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/guia/bandeiras/
│   │   │   │   ├── config/         # Security, JPA, Cache
│   │   │   │   ├── controller/     # REST endpoints
│   │   │   │   ├── dto/            # Data transfer objects
│   │   │   │   ├── entity/         # JPA entities
│   │   │   │   ├── exception/      # Error handling
│   │   │   │   ├── repository/     # JPA repositories
│   │   │   │   ├── security/       # JWT, filters
│   │   │   │   └── service/        # Business logic
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── Dockerfile
│   └── README.md
├── frontend/                # Angular Application
│   ├── src/
│   │   ├── app/
│   │   │   ├── components/     # Theme toggle, notifications, chatbot, comments, leaderboard
│   │   │   ├── pages/          # Glossary, Dashboard, About, Training
│   │   │   ├── services/       # Theme, PDF, notifications
│   │   │   └── ...
│   ├── assets/
│   │   └── i18n/              # Translation files (pt/en)
│   ├── angular.json
│   ├── package.json
│   ├── proxy.conf.json
│   ├── Dockerfile
│   ├── nginx.conf
│   └── README.md
├── .github/
│   └── workflows/
│       └── ci-cd.yml         # GitHub Actions pipeline
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
- PostgreSQL 15+ (ou Docker)
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
- PostgreSQL: `localhost:5432`

## 🔌 Endpoints da API

### Autenticação
- `POST /api/auth/login` - Login de usuário
- `POST /api/auth/register` - Registro de novo usuário

### Busca
- `GET /api/search` - Busca termos nos manuais das bandeiras
- `GET /api/faq` - Retorna perguntas frequentes

### Glossário
- `GET /api/glossary` - Lista todos os termos do glossário
- `GET /api/glossary/letter/{letter}` - Filtra termos por letra
- `GET /api/glossary/search?query={query}` - Busca termos

### Dashboard
- `GET /api/dashboard/stats` - Estatísticas de uso

### Treinamento
- `GET /api/training/quiz` - Lista perguntas de quiz
- `GET /api/training/flashcards` - Lista flashcards

### Comentários
- `POST /api/comments` - Criar comentário
- `GET /api/comments/result/{resultId}` - Listar comentários de um resultado
- `POST /api/comments/{commentId}/helpful` - Marcar comentário como útil

### Notificações
- `GET /api/notifications` - Lista todas notificações
- `GET /api/notifications/unread` - Lista notificações não lidas
- `POST /api/notifications/{id}/read` - Marcar como lida
- `POST /api/notifications/read-all` - Marcar todas como lidas

### Exportação
- `POST /api/export/excel` - Exportar resultados para Excel

### Gamificação
- `GET /api/gamification/leaderboard` - Ranking de usuários
- `GET /api/gamification/user-stats` - Estatísticas do usuário atual

## 🎨 Funcionalidades

### Core
- **Busca unificada**: Pesquise termos e veja resultados de todas as bandeiras (Visa, MasterCard, Amex, Elo, Hipercard, Discover)
- **Resumo automático**: Trechos relevantes dos manuais
- **Links diretos**: Acesso ao manual oficial
- **FAQ inteligente**: Perguntas frequentes organizadas por categoria
- **UI responsiva**: Funciona em desktop e mobile
- **Cache**: Respostas em cache para performance

### Autenticação e Segurança
- **JWT Authentication**: Login seguro com tokens JWT
- **Spring Security**: Proteção de endpoints
- **Perfis de usuário**: Sistema de roles e permissões
- **Registro de usuários**: Cadastro de novos usuários

### Banco de Dados e Persistência
- **PostgreSQL**: Banco de dados relacional
- **Histórico de buscas**: Registro de todas as buscas realizadas
- **Comentários**: Sistema de comentários nos resultados
- **Gamificação**: Pontos e ranking de usuários

### UI/UX Avançado
- **Tema Claro/Escuro**: Toggle para alternar entre temas com persistência
- **Tipografia Inter**: Fonte moderna e profissional
- **Animações**: Efeitos de fade-in e hover suaves
- **Hero Section**: Página inicial melhorada com cards das bandeiras e ações rápidas

### Novas Funcionalidades v2.0
- **Glossário**: 23+ termos técnicos com busca por letra e categoria
- **Dashboard**: Gráficos interativos com Chart.js (buscas por bandeira, termos mais buscados, tendências)
- **Área de Treinamento**: 
  - Quizzes interativos com feedback instantâneo
  - Flashcards com animação de virar carta
  - Ranking de usuários gamificado
- **Chatbot**: Assistente virtual para dúvidas comuns
- **Exportação PDF**: Exporte resultados de busca para PDF
- **Exportação Excel**: Exporte resultados de busca para Excel
- **Notificações**: Sistema de notificações para atualizações de manuais
- **Multi-idioma**: Suporte para Português e Inglês (com @ngx-translate)
- **Sistema de Comentários**: Comentários e feedback nos resultados de busca

### Infraestrutura e DevOps
- **Docker Compose**: Orquestração de containers com PostgreSQL
- **GitHub Actions**: Pipeline CI/CD automatizado
- **Logging Estruturado**: SLF4J + Logback com rotação de logs
- **Tratamento de Erros**: @ControllerAdvice para exceções globais

## 📄 Páginas

- **Home**: Busca principal com hero section e ações rápidas
- **FAQ**: Perguntas frequentes organizadas por categoria
- **Glossário**: Termos técnicos com busca e filtro por letra
- **Dashboard**: Estatísticas e gráficos de uso
- **Treinamento**: Quizzes, flashcards e ranking para aprendizado
- **Sobre**: Informações sobre o projeto e tecnologias

## 🔮 Evoluções Futuras

- [ ] Deploy em nuvem (Azure/AWS)
- [ ] Web scraping real das APIs das bandeiras
- [ ] Integração com mais bandeiras
- [ ] Chatbot com IA treinada nos manuais oficiais
- [ ] Sistema de comentários avançado com respostas
- [ ] Análise de dados e relatórios avançados

## 📖 Documentação

- [Arquitetura](docs/arquitetura.md) - Detalhes técnicos e diagramas
- [Backend README](backend/README.md) - Documentação do backend
- [Frontend README](frontend/README.md) - Documentação do frontend

## 🛠️ Tecnologias

### Backend
- Java 17
- Spring Boot 3.2.0
- Spring Security
- Spring Data JPA
- PostgreSQL
- JWT (jjwt)
- Maven
- Caffeine Cache
- Lombok
- JSoup
- Apache POI (Excel)
- SLF4J + Logback

### Frontend
- Angular 17
- Angular Material
- TypeScript
- RxJS
- HttpClient
- Chart.js (gráficos)
- jsPDF (exportação PDF)
- @ngx-translate (multi-idioma)
- Inter (tipografia)

### DevOps
- Docker
- Docker Compose
- GitHub Actions
- Nginx

## 📝 Licença

Este projeto é para fins educacionais e demonstrativos.
