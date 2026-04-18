# 💸 FinanceApp
### API de gestão financeira pessoal com autenticação JWT e isolamento de dados

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring_Security-6DB33F?style=for-the-badge&logo=Spring-Security&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-4169E1?style=for-the-badge&logo=postgresql&logoColor=white)
![Flyway](https://img.shields.io/badge/Flyway-CC0200?style=for-the-badge&logo=flyway&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23C1272D?style=for-the-badge&logo=swagger&logoColor=white)

</div>

<br>

## 👥 Sobre o projeto

O **FinanceApp** é uma API REST desenvolvida como desafio técnico para Desenvolvedor Backend Junior. O objetivo principal do sistema é proteger nossa API de gestão financeira pessoal, garantindo um isolamento completo e seguro de dados.

Os usuários devem se registrar e fazer login para obter um token JWT. **A segurança é inegociável:** nenhum endpoint de dados pode ser acessado sem autenticação válida.

Cada usuário só pode ver e manipular seus próprios dados:
- 💸 lançamentos
- 🏷️ categorias
- 📊 resumo do mês

---

## 🚨 IMPORTANTE (antes de rodar)

> [!IMPORTANT]
> Você PRECISA:

### ☕ Java
Certifique-se de que possui o **Java 17+** instalado e configurado para o projeto.

### 🐘 PostgreSQL rodando
Você precisa criar o banco de dados da aplicação:

```sql
CREATE DATABASE financeapp_db;
```

> [!WARNING]
> 👉 Se não fizer isso, a aplicação **NÃO** sobe devido às validações de banco de dados e do Flyway.

---

## 🔐 Fluxo de Autenticação JWT

Entender esse fluxo é a base do sistema de segurança implementado.

### 1️⃣ Registro / Login
* `POST /auth/registro` → salva usuário com senha em BCrypt → retorna token JWT
* `POST /auth/login` → valida email + senha → retorna token JWT

### 2️⃣ Requisições Autenticadas
O cliente envia no cabeçalho: `Authorization: Bearer {token}`
1. **JwtFilter** intercepta a requisição.
2. Valida o token e sua expiração.
3. Configura o `SecurityContext`.
4. Acesso liberado (ou bloqueado com 401/403).

---

## 📡 Endpoints (Públicos vs Protegidos)

| Método | Endpoint | Status | Descrição |
| :--- | :--- | :--- | :--- |
| `POST` | `/auth/registro` | 🟢 Público | Cria um novo usuário |
| `POST` | `/auth/login` | 🟢 Público | Retorna o token JWT |
| `POST` | `/lancamentos` | 🔴 Protegido | Cria lançamento do usuário |
| `GET` | `/lancamentos` | 🔴 Protegido | Lista lançamentos do usuário |
| `GET` | `/lancamentos/{id}` | 🔴 Protegido | Busca lançamento específico |
| `PUT` | `/lancamentos/{id}` | 🔴 Protegido | Edita lançamento |
| `DELETE`| `/lancamentos/{id}` | 🔴 Protegido | Deleta lançamento |
| `POST` | `/categorias` | 🔴 Protegido | Cria categoria |
| `GET` | `/categorias` | 🔴 Protegido | Lista categorias do usuário |
| `GET` | `/resumo/mensal` | 🔴 Protegido | Soma receitas e despesas do mês |

---

## 🧱 Entidades Principais

* **Usuario:** `id`, `nome`, `email` (único), `senha` (hash), `role` (ADMIN, USER)
* **Lancamento:** `id`, `descricao`, `valor` (BigDecimal), `data`, `tipo` (RECEITA/DESPESA), `categoria`, `usuario`
* **Categoria:** `id`, `nome`, `usuario`

**Relacionamentos:**
* Lancamento `@ManyToOne` Usuario | Lancamento `@ManyToOne` Categoria
* Categoria `@ManyToOne` Usuario

---

## ⚖️ Regras de Negócio Obrigatórias

> [!CAUTION]
> **O isolamento de dados é fundamental:**
> * Cada usuário **só pode** ver, criar, editar e deletar seus próprios lançamentos e categorias.
> * Acessar dados de outro usuário retorna **403 Forbidden** — nunca `404`.

1. Token JWT expira em **24 horas**.
2. Token deve conter o **email** do usuário como `subject`.
3. Endpoints protegidos retornam **401 Unauthorized** se o token estiver ausente ou inválido.
4. Endpoints retornam **403 Forbidden** se o token for válido mas o recurso pertence a outro usuário.
5. Um lançamento **não pode** ter valor negativo ou zero.
6. A categoria do lançamento deve pertencer ao mesmo usuário do lançamento.
7. Resumo mensal retorna: *total de receitas, total de despesas e saldo do mês atual*.

---

## 🗃️ Banco de dados (Flyway)

📍 **Local:** `src/main/resources/db/migration`

O banco é gerido pelo Flyway. Não crie tabelas manualmente! O projeto contém as seguintes migrations:
```text
V1__create_table_usuarios.sql
V2__create_table_categorias.sql
V3__create_table_lancamentos.sql
V4__insert_dados_teste.sql
```
*A coluna `role` na tabela `usuarios` é `VARCHAR(20)` com valor padrão `'USER'`.*

---

## 🧠 Guia de Implementação (Arquitetura)

### 1. Autenticação (jjwt)
O projeto utiliza a biblioteca padrão de mercado para JWT (`jjwt-api`, `jjwt-impl` e `jjwt-jackson` versão `0.12.x`). A classe `Usuario` implementa a interface `UserDetails` para integração direta com o Spring Security.

### 2. JwtService & JwtAuthFilter
* **JwtService:** Responsável por criar (HMAC-SHA256, expiration=24h, subject=email), extrair dados e validar o JWT.
* **JwtAuthFilter:** Estende `OncePerRequestFilter`. Intercepta as chamadas, extrai do Header o `Bearer {token}` e se for válido configura o `SecurityContextHolder`.

### 3. SecurityConfig
Centraliza a segurança via `@EnableWebSecurity`:
* Desabilita CSRF (APIs REST são Stateless).
* SessionManagement configurada como `STATELESS`.
* Libera acesso aos endpoints `/auth/**` e Swagger.
* Registra `JwtAuthFilter` antes do `UsernamePasswordAuthenticationFilter`.

### 4. Isolamento Lógico (Service Layer)
Todos os serviços e repositórios buscam e validam os recursos com base no `SecurityContextHolder.getContext().getAuthentication().getPrincipal()`. O JPA e as Queries nativas/JPQL filtram proativamente dados pelo ID do usuário autenticado para garantir que os dados não vazem sob nenhuma hipótese.

### 5. Resumo Mensal Otimizado
O cálculo de receitas/despesas utiliza agregações otimizadas do banco (`SUM`) via banco de dados filtrando com funções como `MONTH()` e `YEAR()` via JPQL no Repository.

---

## 📚 Swagger com JWT Automático

Para facilitar os testes:
1. Suba a aplicação e acesse a rota padrão do Swagger-UI.
2. Acesse `/auth/login` para gerar seu Token JWT.
3. Clique no botão **"Authorize"** (Cadeado) no topo da interface.
4. Insira seu token usando o prefixo (ou apenas o hash, conforme configurado na API) e teste todos os endpoints protegidos diretamente pelo navegador de forma visual e simples.

---

> [!NOTE]
> 💡 Projeto desenvolvido para fins de avaliação técnica e estudo do framework Spring, focando nas melhores práticas de mercado envolvendo Spring Security 3, JWT e mapeamento objeto-relacional robusto.