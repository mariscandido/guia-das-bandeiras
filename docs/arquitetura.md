# Arquitetura do Guia das Bandeiras

## Visão Geral

O Guia das Bandeiras é uma aplicação web monorepo composta por:
- **Frontend**: Aplicação Angular 17
- **Backend**: API REST Spring Boot 3
- **Infraestrutura**: Docker Compose para orquestração

## Arquitetura do Sistema

```
┌─────────────────────────────────────────────────────────┐
│                     Navegador                           │
│                  (http://localhost:4200)                │
└────────────────────┬────────────────────────────────────┘
                     │
                     │ HTTP/HTTPS
                     │
┌────────────────────▼────────────────────────────────────┐
│              Frontend (Angular)                         │
│  ┌──────────────────────────────────────────────────┐  │
│  │  Components:                                     │  │
│  │  - SearchBarComponent                            │  │
│  │  - ResultsListComponent                          │  │
│  │  - FaqComponent                                  │  │
│  │  - HomeComponent                                │  │
│  └──────────────────────────────────────────────────┘  │
│                      │                                   │
│                      │ HttpClient                        │
│                      │                                   │
└──────────────────────▼───────────────────────────────────┘
                     │
                     │ /api/* (via proxy ou nginx)
                     │
┌────────────────────▼────────────────────────────────────┐
│              Backend (Spring Boot)                      │
│  ┌──────────────────────────────────────────────────┐  │
│  │  Controller Layer                                │  │
│  │  - SearchController                              │  │
│  └──────────────────────────────────────────────────┘  │
│                      │                                   │
│  ┌────────────────────▼──────────────────────────────┐  │
│  │  Service Layer                                   │  │
│  │  - SearchService                                │  │
│  │    - searchVisaManual()                          │  │
│  │    - searchMastercardManual()                    │  │
│  │    - searchAmexManual()                          │  │
│  │    - getFaq()                                    │  │
│  └──────────────────────────────────────────────────┘  │
│                      │                                   │
│  ┌────────────────────▼──────────────────────────────┐  │
│  │  Cache Layer (Caffeine)                          │  │
│  │  - searchResults (1h TTL)                        │  │
│  │  - faqCache (1h TTL)                             │  │
│  └──────────────────────────────────────────────────┘  │
└─────────────────────────────────────────────────────────┘
```

## Fluxo de Dados

### 1. Busca de Termos

```
Usuário → SearchBar → HomeComponent → HttpClient
    → Backend (SearchController) → SearchService
    → Cache (Caffeine) → Dados Mockados
    → Response → ResultsListComponent → UI
```

### 2. Acesso ao FAQ

```
Usuário → FaqComponent → HttpClient
    → Backend (SearchController) → SearchService.getFaq()
    → Cache (Caffeine) → Lista de FAQs
    → Response → FaqComponent → UI (Accordion)
```

## Componentes do Backend

### Controller Layer
- **SearchController**: Expõe endpoints REST `/api/search` e `/api/faq`
- Validação de parâmetros com Jakarta Validation
- Tratamento de erros com @ControllerAdvice

### Service Layer
- **SearchService**: Lógica de negócio
- Busca simulada nos manuais das bandeiras (Visa, MasterCard, Amex)
- Cache com @Cacheable para performance

### DTOs
- **SearchRequest**: Parâmetros de busca
- **SearchResponse**: Resposta com resultados
- **FaqItem**: Item de FAQ

### Configuration
- **CorsConfig**: Configuração de CORS
- **CacheConfig**: Configuração do Caffeine

## Componentes do Frontend

### Components
- **AppComponent**: Layout principal com header e footer
- **HomeComponent**: Página inicial com busca e resultados
- **SearchBarComponent**: Campo de busca com validação
- **ResultsListComponent**: Exibição de resultados em cards
- **FaqComponent**: FAQ com accordion por categoria

### Services
- **HttpClient**: Comunicação com backend
- **Router**: Navegação entre rotas

### Routing
- `/home` → HomeComponent
- `/faq` → FaqComponent
- Rota padrão → `/home`

## Estrutura de Dados

### SearchResponse
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

### FaqItem
```json
{
  "question": "O que é chargeback?",
  "answer": "Descrição...",
  "category": "Conceitos Básicos"
}
```

## Cache Strategy

O backend utiliza Caffeine Cache com:
- **searchResults**: Cache de buscas por 1 hora
- **faqCache**: Cache de FAQ por 1 hora
- Chave composta: query + cardBrand (para buscas)

## Comunicação Frontend-Backend

### Desenvolvimento Local
- Angular CLI proxy (`proxy.conf.json`) redireciona `/api/*` para `http://localhost:8080`

### Produção (Docker)
- Nginx no container frontend faz proxy pass para backend
- Rede Docker interna para comunicação

## Segurança

- CORS configurado para origens permitidas
- Validação de entrada no backend
- Sem autenticação implementada (futuro)

## Escalabilidade

### Futuras Melhorias
1. **Banco de Dados**: Persistência de histórico de buscas
2. **Redis**: Cache distribuído para múltiplas instâncias
3. **Message Queue**: Processamento assíncrono de scraping
4. **CDN**: Distribuição de assets estáticos
5. **Load Balancer**: Múltiplas instâncias do backend

## Deploy

### Docker Compose
- Dois serviços: backend e frontend
- Rede interna para comunicação
- Health check no backend
- Frontend depende do backend estar saudável

### Produção Sugerida
- Kubernetes ou AWS ECS
- Banco de dados PostgreSQL
- Redis para cache
- CI/CD pipeline
- Monitoramento com Prometheus/Grafana
