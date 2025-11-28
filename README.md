# 🚀 FLUX API - Expectativa Final

<div align="center">

![Java](https://img.shields.io/badge/Java-17-orange?style=for-the-badge&logo=java)
![Quarkus](https://img.shields.io/badge/Quarkus-3.x-blue?style=for-the-badge&logo=quarkus)
![Oracle](https://img.shields.io/badge/Oracle-SQL-red?style=for-the-badge&logo=oracle)
![JWT](https://img.shields.io/badge/JWT-Auth-green?style=for-the-badge&logo=jsonwebtokens)

**API REST para o sistema FLUX - Plataforma de recomendação inteligente de carreiras**

[Documentação](#-documentação) • [Instalação](#-instalação) • [Endpoints](#-endpoints) • [Arquitetura](#-arquitetura)

</div>

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#-pré-requisitos)
- [Instalação](#-instalação)
- [Configuração](#-configuração)
- [Executando o Projeto](#-executando-o-projeto)
- [Estrutura de Pastas](#-estrutura-de-pastas)
- [Endpoints da API](#-endpoints-da-api)
- [Modelo de Dados](#-modelo-de-dados)
- [Autenticação](#-autenticação)
- [Testes](#-testes)
- [Deploy](#-deploy)
- [Contribuindo](#-contribuindo)
- [Equipe](#-equipe)
- [Licença](#-licença)

---

## 💡 Sobre o Projeto

O **FLUX** é uma plataforma que funciona como um "GPS de carreira", mapeando competências profissionais em tempo real, identificando carreiras emergentes compatíveis e gerando trilhas personalizadas de aprendizado.

### Problema que Resolve
Profissionais navegam suas carreiras sem dados concretos sobre o mercado. O FLUX elimina essa assimetria de informação através de:
- **Matching inteligente** entre skills do usuário e carreiras emergentes
- **Análise de gaps** automática
- **Recomendações personalizadas** via IA
- **Currículo vivo** que evolui com o profissional

### Diferenciais
- ✅ Extração automática de competências (GitHub/LinkedIn)
- ✅ Algoritmo de matching com Machine Learning
- ✅ Sistema de decay temporal (skills não usadas perdem proficiência)
- ✅ Chatbot contextual com IA generativa

---

## 🛠 Tecnologias

### Core
- **[Java 17](https://openjdk.org/projects/jdk/17/)** - Linguagem principal
- **[Quarkus 3.x](https://quarkus.io/)** - Framework supersônico para Java
- **[Oracle Database](https://www.oracle.com/database/)** - Banco de dados relacional
- **[Maven](https://maven.apache.org/)** - Gerenciador de dependências

### Bibliotecas Principais
- **Quarkus REST (JAX-RS)** - Criação de endpoints REST
- **Quarkus JDBC** - Conexão com Oracle
- **Quarkus Security** - Autenticação e autorização
- **JWT (SmallRye JWT)** - Tokens de autenticação
- **BCrypt** - Hash de senhas
- **Jackson** - Serialização JSON

### Ferramentas de Desenvolvimento
- **Maven Wrapper** - Versionamento do Maven
- **Postman/Insomnia** - Testes de API
- **Git** - Controle de versão

---

## ✅ Pré-requisitos

Antes de começar, certifique-se de ter instalado:

- **Java JDK 17+** - [Download](https://adoptium.net/)
- **Maven 3.8+** - [Download](https://maven.apache.org/download.cgi)
- **Oracle Database 11g+** (ou Oracle Cloud) - [Download](https://www.oracle.com/database/technologies/oracle-database-software-downloads.html)
- **Git** - [Download](https://git-scm.com/)

### Verificar Instalações
```bash
java -version
# Deve retornar: openjdk version "17.x.x"

mvn -version
# Deve retornar: Apache Maven 3.x.x

git --version
# Deve retornar: git version 2.x.x
```

---

## 📥 Instalação

### 1. Clone o Repositório
```bash
git clone https://github.com/seu-usuario/flux-api.git
cd flux-api
```

### 2. Instale as Dependências
```bash
./mvnw clean install
```

### 3. Configure o Banco de Dados

Execute os scripts SQL na ordem:
```bash
# 1. Criar estrutura (DDL)
sqlplus usuario/senha@database < src/main/resources/db/ddl-schema.sql

# 2. Popular dados iniciais (DML)
sqlplus usuario/senha@database < src/main/resources/db/dml-seed-data.sql
```

---

## ⚙️ Configuração

### Arquivo `application.properties`

Crie/edite o arquivo `src/main/resources/application.properties`:
```properties
# ============================================
# CONFIGURAÇÕES DO SERVIDOR
# ============================================
quarkus.http.port=8080
quarkus.http.host=0.0.0.0

# ============================================
# CONFIGURAÇÕES DO BANCO DE DADOS ORACLE
# ============================================
quarkus.datasource.db-kind=oracle
quarkus.datasource.username=SEU_USUARIO
quarkus.datasource.password=SUA_SENHA
quarkus.datasource.jdbc.url=jdbc:oracle:thin:@localhost:1521:XE

# Pool de conexões
quarkus.datasource.jdbc.min-size=5
quarkus.datasource.jdbc.max-size=20

# ============================================
# CONFIGURAÇÕES DE SEGURANÇA (JWT)
# ============================================
mp.jwt.verify.publickey.location=META-INF/resources/publicKey.pem
mp.jwt.verify.issuer=https://flux-api.com
smallrye.jwt.sign.key.location=META-INF/resources/privateKey.pem

# Tempo de expiração do token (7 dias)
jwt.duration=604800

# ============================================
# CONFIGURAÇÕES DE CORS
# ============================================
quarkus.http.cors=true
quarkus.http.cors.origins=http://localhost:5173,https://flux-app.vercel.app
quarkus.http.cors.methods=GET,POST,PUT,DELETE,OPTIONS
quarkus.http.cors.headers=accept,authorization,content-type,x-requested-with

# ============================================
# CONFIGURAÇÕES DE LOG
# ============================================
quarkus.log.level=INFO
quarkus.log.category."br.com.flux".level=DEBUG
quarkus.log.console.format=%d{HH:mm:ss} %-5p [%c{2.}] (%t) %s%e%n

# ============================================
# CONFIGURAÇÕES DE DESENVOLVIMENTO
# ============================================
# Live Reload (hot reload automático)
quarkus.live-reload.instrumentation=true

# Swagger UI (apenas em dev)
quarkus.swagger-ui.always-include=true
quarkus.swagger-ui.path=/swagger-ui
```

### Variáveis de Ambiente (Produção)

Para produção, use variáveis de ambiente:
```bash
export QUARKUS_DATASOURCE_USERNAME=prod_user
export QUARKUS_DATASOURCE_PASSWORD=prod_password
export QUARKUS_DATASOURCE_JDBC_URL=jdbc:oracle:thin:@prod-server:1521:ORCL
export JWT_SECRET=seu-secret-super-seguro-aqui
```

---

## 🏃 Executando o Projeto

### Modo Desenvolvimento (com Live Reload)
```bash
./mvnw quarkus:dev
```

A API estará disponível em: **http://localhost:8080**

**Features em Dev Mode:**
- ✅ Live Reload automático ao salvar arquivos
- ✅ Dev UI: http://localhost:8080/q/dev/
- ✅ Swagger UI: http://localhost:8080/q/swagger-ui/

### Modo Produção
```bash
# 1. Compilar o projeto
./mvnw clean package

# 2. Executar o JAR
java -jar target/quarkus-app/quarkus-run.jar
```

### Executar com Docker (Opcional)
```bash
# Build da imagem
./mvnw clean package -Dquarkus.container-image.build=true

# Executar container
docker run -i --rm -p 8080:8080 flux-api:latest
```

---

## 📁 Estrutura de Pastas
```
flux-api/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── br/com/flux/
│   │   │       ├── model/           # TOs (Transfer Objects)
│   │   │       │   ├── UsuarioTO.java
│   │   │       │   ├── CarreiraTO.java
│   │   │       │   ├── CompetenciaTO.java
│   │   │       │   └── MatchCarreiraTO.java
│   │   │       │
│   │   │       ├── dao/             # Data Access Objects
│   │   │       │   ├── UsuarioDAO.java
│   │   │       │   ├── CarreiraDAO.java
│   │   │       │   ├── CompetenciaDAO.java
│   │   │       │   └── MatchCarreiraDAO.java
│   │   │       │
│   │   │       ├── resource/        # Controllers REST
│   │   │       │   ├── AuthResource.java
│   │   │       │   ├── UsuarioResource.java
│   │   │       │   ├── CarreiraResource.java
│   │   │       │   ├── CompetenciaResource.java
│   │   │       │   └── MatchCarreiraResource.java
│   │   │       │
│   │   │       ├── service/         # Lógica de Negócio
│   │   │       │   ├── AuthService.java
│   │   │       │   ├── MatchingService.java
│   │   │       │   └── JwtService.java
│   │   │       │
│   │   │       ├── util/            # Utilitários
│   │   │       │   ├── ConnectionFactory.java
│   │   │       │   └── PasswordUtil.java
│   │   │       │
│   │   │       └── exception/       # Tratamento de Erros
│   │   │           ├── GlobalExceptionHandler.java
│   │   │           └── BusinessException.java
│   │   │
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── db/
│   │       │   ├── ddl-schema.sql
│   │       │   └── dml-seed-data.sql
│   │       └── META-INF/
│   │           └── resources/
│   │               ├── privateKey.pem
│   │               └── publicKey.pem
│   │
│   └── test/
│       └── java/
│           └── br/com/flux/
│               └── resource/
│                   ├── AuthResourceTest.java
│                   └── CarreiraResourceTest.java
│
├── .gitignore
├── pom.xml
├── README.md
└── mvnw
```

---

## 🔌 Endpoints da API

### Base URL
```
https://flux-api-moxp.onrender.com
```

### 🔐 Autenticação

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `POST` | `/auth/register` | Cadastrar novo usuário | ❌ |
| `POST` | `/auth/login` | Fazer login | ❌ |
| `GET` | `/auth/me` | Obter dados do usuário logado | ✅ |

**Exemplo de Request - Register:**
```json
POST /api/auth/register
Content-Type: application/json

{
  "nomeCompleto": "João Silva",
  "email": "joao@email.com",
  "senha": "senha123"
}
```

**Exemplo de Response:**
```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "usuario": {
    "id": 1,
    "nomeCompleto": "João Silva",
    "email": "joao@email.com"
  }
}
```

---

### 👤 Usuários

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `GET` | `/usuarios` | Listar todos usuários (admin) | ✅ |
| `GET` | `/usuarios/{id}` | Buscar usuário por ID | ✅ |
| `PUT` | `/usuarios/{id}` | Atualizar usuário | ✅ |
| `DELETE` | `/usuarios/{id}` | Desativar usuário | ✅ |

---

### 💼 Carreiras

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `GET` | `/carreiras` | Listar todas carreiras | ❌ |
| `GET` | `/carreiras/{id}` | Buscar carreira por ID | ❌ |
| `POST` | `/carreiras` | Criar nova carreira (admin) | ✅ |
| `PUT` | `/carreiras/{id}` | Atualizar carreira (admin) | ✅ |
| `DELETE` | `/carreiras/{id}` | Desativar carreira (admin) | ✅ |

---

### 🎯 Competências

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `GET` | `/competencias` | Listar competências do usuário logado | ✅ |
| `GET` | `/competencias/{id}` | Buscar competência por ID | ✅ |
| `POST` | `/competencias` | Adicionar nova competência | ✅ |
| `PUT` | `/competencias/{id}` | Atualizar competência | ✅ |
| `DELETE` | `/competencias/{id}` | Remover competência | ✅ |

**Exemplo de Request:**
```json
POST /api/competencias
Authorization: Bearer {token}
Content-Type: application/json

{
  "nomeCompetencia": "Python",
  "proficiencia": 75,
  "tipoVerificacao": "github",
  "provaUrl": "https://github.com/joao/projeto-python"
}
```

---

### 🎲 Matching de Carreiras

| Método | Endpoint | Descrição | Auth |
|--------|----------|-----------|------|
| `GET` | `/matches/usuario` | Buscar matches do usuário logado | ✅ |
| `GET` | `/matches/carreira/{id}` | Ver detalhes de match específico | ✅ |
| `POST` | `/matches/recalcular` | Forçar recálculo de matches | ✅ |

**Exemplo de Response:**
```json
GET /api/matches/usuario
Authorization: Bearer {token}

[
  {
    "carreira": {
      "id": 10,
      "nome": "Carbon Credit Analyst",
      "crescimento": 87,
      "salarioMedio": 13000.00
    },
    "matchScore": 78,
    "gaps": ["ESG Framework"],
    "competenciasComuns": ["Excel", "Financial Analysis"],
    "tempoEstimado": "4-6 meses"
  }
]
```

---

## 🗄️ Modelo de Dados

### Diagrama ER Simplificado
```
T_FLUX_USUARIO
├── id_usuario (PK)
├── nm_email (UNIQUE)
├── ds_senha_hash
├── nm_completo
└── dt_cadastro

T_FLUX_COMPETENCIA
├── id_competencia (PK)
├── id_usuario (FK)
├── nm_competencia
├── vl_proficiencia (0-100)
├── tp_verificacao
└── dt_verificacao

T_FLUX_CARREIRA
├── id_carreira (PK)
├── nm_carreira (UNIQUE)
├── vl_crescimento (0-100)
├── vl_salario_medio
└── dt_emergente_desde

T_FLUX_MATCH_CARREIRA
├── id_match (PK)
├── id_usuario (FK)
├── id_carreira (FK)
├── vl_compatibilidade (0-100)
├── ds_gaps (CLOB JSON)
└── dt_calculo
```

### Principais Relações

- `1 USUARIO` → `N COMPETENCIAS`
- `1 USUARIO` → `N MATCHES`
- `1 CARREIRA` → `N MATCHES`
- `1 CARREIRA` → `N CARREIRA_COMPETENCIAS` (skills requeridas)

---

## 🔒 Autenticação

### JWT (JSON Web Token)

A API utiliza JWT para autenticação stateless.

**Fluxo:**
1. Cliente faz `POST /auth/login` com email/senha
2. API valida credenciais e retorna token JWT
3. Cliente envia token no header `Authorization: Bearer {token}` em requisições protegidas
4. API valida token e extrai `id_usuario`

**Estrutura do Token:**
```json
{
  "sub": "joao@email.com",
  "userId": 1,
  "iss": "https://flux-api.com",
  "exp": 1700000000
}
```

**Tempo de Expiração:** 7 dias (604800 segundos)

### Proteção de Rotas
```java
@GET
@Path("/protegida")
@RolesAllowed({"USER"})
public Response rotaProtegida(@Context SecurityContext securityContext) {
    String email = securityContext.getUserPrincipal().getName();
    // ...
}
```

---

## 🧪 Testes

### Executar Todos os Testes
```bash
./mvnw test
```

### Executar Teste Específico
```bash
./mvnw test -Dtest=AuthResourceTest
```

### Cobertura de Testes
```bash
./mvnw verify jacoco:report
```

Relatório gerado em: `target/site/jacoco/index.html`

### Teste Manual com Postman

Importe a collection:
```
docs/postman/FLUX-API.postman_collection.json
```

---

## 🚀 Deploy

### Deploy no Railway
```bash
# 1. Instalar Railway CLI
npm install -g @railway/cli

# 2. Login
railway login

# 3. Inicializar projeto
railway init

# 4. Deploy
railway up
```

### Deploy no Heroku
```bash
# 1. Login
heroku login

# 2. Criar app
heroku create flux-api

# 3. Deploy
git push heroku main

# 4. Configurar variáveis
heroku config:set QUARKUS_DATASOURCE_USERNAME=prod_user
```

### Deploy com Docker
```dockerfile
# Dockerfile
FROM registry.access.redhat.com/ubi8/openjdk-17:1.18

ENV LANGUAGE='en_US:en'

COPY --chown=185 target/quarkus-app/lib/ /deployments/lib/
COPY --chown=185 target/quarkus-app/*.jar /deployments/
COPY --chown=185 target/quarkus-app/app/ /deployments/app/
COPY --chown=185 target/quarkus-app/quarkus/ /deployments/quarkus/

EXPOSE 8080
USER 185
ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-run.jar"

ENTRYPOINT [ "/opt/jboss/container/java/run/run-java.sh" ]
```
```bash
docker build -t flux-api .
docker run -p 8080:8080 flux-api
```

---

## 🤝 Contribuindo

Contribuições são bem-vindas! Siga os passos:

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/MinhaFeature`)
3. Commit suas mudanças (`git commit -m 'Add: nova feature'`)
4. Push para a branch (`git push origin feature/MinhaFeature`)
5. Abra um Pull Request

### Padrões de Commit
```
feat: Nova funcionalidade
fix: Correção de bug
docs: Atualização de documentação
style: Formatação de código
refactor: Refatoração de código
test: Adição/correção de testes
chore: Manutenção geral
```

---

## 👥 Equipe

| Nome | Função | LinkedIn |
|------|--------|----------|
| **Seu Nome** | Full Stack Developer | [linkedin.com/in/seu-perfil](https://linkedin.com) |
| **Colega A** | Backend Developer | [linkedin.com/in/colega-a](https://linkedin.com) |
| **Colega B** | Frontend Developer | [linkedin.com/in/colega-b](https://linkedin.com) |

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

---

## 📞 Contato

Para dúvidas ou sugestões:

- 📧 Email: contato@flux-api.com
- 🌐 Website: [flux-api.com](https://flux-api.com)
- 💬 Issues: [GitHub Issues](https://github.com/seu-usuario/flux-api/issues)

---

## 🙏 Agradecimentos

- **FIAP** - Pela formação em Análise e Desenvolvimento de Sistemas
- **Quarkus Community** - Pela excelente documentação
- **Oracle** - Pelo banco de dados robusto
- **Comunidade Open Source** - Por todas as bibliotecas utilizadas

---

<div align="center">

**Feito com ❤️ pela equipe FLUX**

⭐ Se este projeto te ajudou, deixe uma estrela!

</div>
