# Guia das Bandeiras - Frontend

Frontend Angular para o projeto Guia das Bandeiras.

## Tecnologias

- Angular 17
- Angular Material
- TypeScript
- RxJS
- HttpClient

## Estrutura do Projeto

```
frontend/
├── src/
│   ├── app/
│   │   ├── app.component.ts/html/css
│   │   ├── app-routing.module.ts
│   │   ├── app.module.ts
│   │   ├── home/
│   │   │   ├── home.component.ts/html/css
│   │   ├── search-bar/
│   │   │   ├── search-bar.component.ts/html/css
│   │   ├── results-list/
│   │   │   ├── results-list.component.ts/html/css
│   │   └── faq/
│   │       ├── faq.component.ts/html/css
│   ├── index.html
│   ├── main.ts
│   └── styles.css
├── angular.json
├── package.json
├── proxy.conf.json
├── tsconfig.json
├── Dockerfile
└── nginx.conf
```

## Componentes

### SearchBarComponent
Campo de busca com autocomplete e validação.

### ResultsListComponent
Lista de resultados em cards com informações da bandeira, trecho relevante e link para o manual oficial.

### FaqComponent
Perguntas frequentes organizadas por categoria com accordion.

### HomeComponent
Página inicial que integra busca e resultados.

## Rotas

- `/` → Redireciona para `/home`
- `/home` → Página inicial com busca
- `/faq` → Perguntas frequentes

## Executar Localmente

```bash
npm install
npm start
```

O servidor estará disponível em `http://localhost:4200`

## Build para Produção

```bash
npm run build
```

## Proxy Configuration

O `proxy.conf.json` configura o proxy para redirecionar requisições `/api/*` para o backend em `http://localhost:8080`.

## Docker

Para build e execução com Docker:

```bash
docker build -t guia-bandeiras-frontend .
docker run -p 4200:80 guia-bandeiras-frontend
```

## Estilos

O projeto utiliza Angular Material com o tema `indigo-pink`. Estilos customizados estão em `styles.css` e nos arquivos CSS de cada componente.
