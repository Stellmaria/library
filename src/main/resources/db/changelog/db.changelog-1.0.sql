--liquibase formatted sql

--changeset stell:2
CREATE TABLE IF NOT EXISTS author
(
    id          BIGSERIAL PRIMARY KEY,
    first_name  VARCHAR(64) NOT NULL,
    last_name   VARCHAR(64) NOT NULL,
    image       VARCHAR(255),
    birthday    DATE,
    date_death  DATE,
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE author;

--changeset stell:3
CREATE TABLE IF NOT EXISTS book_language
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_language;

--changeset stell:4
CREATE TABLE IF NOT EXISTS book_format
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_format;

--changeset stell:5
CREATE TABLE IF NOT EXISTS book_publishing_house
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_publishing_house;

--changeset stell:6
CREATE TABLE IF NOT EXISTS book_series
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_series;

--changeset stell:7
CREATE TABLE IF NOT EXISTS book_genre
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    description VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_genre;

--changeset stell:8
CREATE TABLE IF NOT EXISTS book_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE book_status;

--changeset stell:9
CREATE TABLE IF NOT EXISTS user_role
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE user_role;

--changeset stell:10
CREATE TABLE IF NOT EXISTS user_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE user_status;

--changeset stell:11
CREATE TABLE IF NOT EXISTS users
(
    id             BIGSERIAL PRIMARY KEY,
    username       VARCHAR(50) UNIQUE NOT NULL,
    first_name     VARCHAR(99)        NOT NULL,
    last_name      VARCHAR(99)        NOT NULL,
    email          VARCHAR(50) UNIQUE NOT NULL,
    password       VARCHAR(128)       NOT NULL DEFAULT '{noop}111111',
    user_role_id   INTEGER DEFAULT 1  NOT NULL,
    user_status_id INTEGER DEFAULT 1  NOT NULL,
    birthday       DATE,
    image          VARCHAR(255),
    created_at     TIMESTAMP WITHOUT TIME ZONE,
    modified_at    TIMESTAMP WITHOUT TIME ZONE,
    created_by     VARCHAR(64),
    modified_by    VARCHAR(64),
    FOREIGN KEY (user_role_id) REFERENCES user_role (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT,
    FOREIGN KEY (user_status_id) REFERENCES user_status (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT
);
--rollback DROP TABLE users;

--changeset stell:12
CREATE TABLE IF NOT EXISTS order_status
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE order_status;

--changeset stell:13
CREATE TABLE IF NOT EXISTS order_type
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);
--rollback DROP TABLE order_type;

--changeset stell:14
CREATE TABLE IF NOT EXISTS orders
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT  DEFAULT 1 NOT NULL,
    order_status_id INTEGER DEFAULT 1 NOT NULL,
    order_type_id   INTEGER DEFAULT 1 NOT NULL,
    order_date      TIMESTAMP         NOT NULL,
    return_date     TIMESTAMP         NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    modified_by     VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT,
    FOREIGN KEY (order_status_id) REFERENCES order_status (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT,
    FOREIGN KEY (order_type_id) REFERENCES order_type (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT
);
--rollback DROP TABLE orders;

--changeset stell:15
CREATE TABLE IF NOT EXISTS book
(
    id                       BIGSERIAL PRIMARY KEY,
    title                    VARCHAR(255)      NOT NULL,
    subtitle                 VARCHAR(255),
    year                     INTEGER,
    pages                    SMALLINT,
    isbn_10                  VARCHAR(10),
    isbn_13                  VARCHAR(15),
    image                    VARCHAR(255),
    book_status_id           INTEGER DEFAULT 1 NOT NULL,
    book_language_id         INTEGER,
    book_format_id           INTEGER,
    book_publishing_house_id INTEGER,
    book_series_id           INTEGER,
    order_id                 BIGINT,
    created_at               TIMESTAMP WITHOUT TIME ZONE,
    modified_at              TIMESTAMP WITHOUT TIME ZONE,
    created_by               VARCHAR(64),
    modified_by              VARCHAR(64),
    FOREIGN KEY (book_status_id) REFERENCES book_status (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT,
    FOREIGN KEY (book_language_id) REFERENCES book_language (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (book_format_id) REFERENCES book_format (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (book_publishing_house_id) REFERENCES book_publishing_house (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (book_series_id) REFERENCES book_series (id)
        ON UPDATE CASCADE ON DELETE SET NULL,
    FOREIGN KEY (order_id) REFERENCES orders (id)
        ON DELETE SET NULL
);
--rollback DROP TABLE book;

--changeset stell:16
CREATE TABLE IF NOT EXISTS books_genres
(
    id       BIGSERIAL PRIMARY KEY,
    book_id  BIGINT REFERENCES book (id) ON DELETE CASCADE,
    genre_id INTEGER REFERENCES book_genre (id) ON DELETE CASCADE
);
--rollback DROP TABLE books_genres;

--changeset stell:17
CREATE TABLE IF NOT EXISTS books_authors
(
    id        BIGSERIAL PRIMARY KEY,
    book_id   BIGINT REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id BIGINT REFERENCES author (id) ON DELETE CASCADE ON UPDATE CASCADE
);
--rollback DROP TABLE books_authors;

--changeset stell:18
CREATE TABLE IF NOT EXISTS book_additional
(
    id          BIGSERIAL PRIMARY KEY,
    book_id     BIGINT UNIQUE,
    volume      SMALLINT,
    serial_no   INTEGER,
    price       DECIMAL,
    link        VARCHAR(255),
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64),
    FOREIGN KEY (book_id) REFERENCES book (id)
        ON UPDATE CASCADE ON DELETE SET NULL
);
--rollback DROP TABLE book_additional;