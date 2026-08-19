# Library

A Spring Boot MVC application for managing a small library. Readers can browse and filter the catalogue, add books to a session cart and create orders. Librarians manage catalogue and order data, while administrators can also manage users and destructive operations.

## Features

- book catalogue with filtering and pagination;
- authors, genres, series and publishing houses;
- reader cart and checkout flow;
- order history and order management;
- user registration, login and role-based access control;
- image storage for books, authors and users;
- PostgreSQL schema and seed data managed by Liquibase;
- QueryDSL-based repository filters;
- repository, service and MVC integration tests with Testcontainers;
- tracked-secret scanning and Gradle CI in GitHub Actions.

## Roles

| Role | Main capabilities |
| --- | --- |
| `ROLE_GUEST` | Authenticated guest access provided by existing seed data |
| `ROLE_READER` | Browse books, use the cart, checkout and view personal orders |
| `ROLE_LIBRARIAN` | Manage catalogue entries and orders |
| `ROLE_ADMIN` | Manage users, catalogue data and destructive operations |

Server-side authorization is enforced in addition to Thymeleaf visibility rules. CSRF protection is enabled for state-changing application requests.

## Tech stack

- Java 17
- Spring Boot 2.7.5
- Spring MVC and Thymeleaf
- Spring Security
- Spring Data JPA / Hibernate
- QueryDSL
- Liquibase
- PostgreSQL 15
- Gradle 7.5.1 Wrapper
- JUnit 5, Spring Test and Testcontainers

## Run locally

### 1. Start PostgreSQL

Docker Compose is included for local development:

```bash
docker compose up -d
```

It starts PostgreSQL on `localhost:5432` with database `library`, user `postgres` and the local-only password `change_me`.

### 2. Start the application

```bash
./gradlew bootRun
```

On Windows:

```powershell
.\gradlew.bat bootRun
```

The application is available at `http://localhost:8080`.

Liquibase creates the schema and loads the bundled development data on startup.

## Configuration

The application can be configured with environment variables:

| Variable | Default | Purpose |
| --- | --- | --- |
| `DB_URL` | `jdbc:postgresql://localhost:5432/library` | JDBC connection URL |
| `DB_USERNAME` | `postgres` | Database user |
| `DB_PASSWORD` | `change_me` | Database password |
| `IMAGES_DIR` | `./images` | Directory used for uploaded and seeded images |

Do not reuse the local development password outside a local environment.

## Tests

Integration tests use Testcontainers and start their own PostgreSQL container, so Docker must be available.

```bash
./gradlew clean test
```

To run the full verification lifecycle:

```bash
./gradlew clean build
```

## Project structure

```text
src/main/java/com/it/academy/library
├── config        Spring MVC, Security and auditing configuration
├── http          MVC controllers
├── mapper        entity/DTO conversion
├── model         JPA entities and repositories
├── querydsl      predicate helpers
└── service       DTOs, business services and image storage

src/main/resources
├── db/changelog  Liquibase schema, seed data and integrity migrations
├── static        CSS and static images
└── templates     Thymeleaf pages
```

## Data integrity

Checkout validates and locks requested book rows before an order is created. Database constraints prevent negative stock quantities and return dates earlier than order dates.

## Original task

The project models a library where a reader can search for and order books, and library staff can manage lending and catalogue data.
