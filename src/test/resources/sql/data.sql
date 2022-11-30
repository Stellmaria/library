DROP TABLE IF EXISTS author CASCADE;
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

DROP TABLE IF EXISTS book_language CASCADE;
CREATE TABLE IF NOT EXISTS book_language
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS book_format CASCADE;
CREATE TABLE IF NOT EXISTS book_format
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS book_publishing_house CASCADE;
CREATE TABLE IF NOT EXISTS book_publishing_house
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

DROP TABLE IF EXISTS book_series CASCADE;
CREATE TABLE IF NOT EXISTS book_series
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

DROP TABLE IF EXISTS book_genre CASCADE;
CREATE TABLE IF NOT EXISTS book_genre
(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR(64) UNIQUE NOT NULL,
    created_at  TIMESTAMP WITHOUT TIME ZONE,
    modified_at TIMESTAMP WITHOUT TIME ZONE,
    created_by  VARCHAR(64),
    modified_by VARCHAR(64)
);

DROP TABLE IF EXISTS book_status CASCADE;
CREATE TABLE IF NOT EXISTS book_status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS user_role CASCADE;
CREATE TABLE IF NOT EXISTS user_role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS user_status CASCADE;
CREATE TABLE IF NOT EXISTS user_status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS users CASCADE;
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

DROP TABLE IF EXISTS order_status CASCADE;
CREATE TABLE IF NOT EXISTS order_status
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(64) UNIQUE NOT NULL
);

DROP TABLE IF EXISTS orders CASCADE;
CREATE TABLE IF NOT EXISTS orders
(
    id              BIGSERIAL PRIMARY KEY,
    user_id         BIGINT  DEFAULT 1 NOT NULL,
    order_status_id INTEGER DEFAULT 1 NOT NULL,
    order_date      TIMESTAMP         NOT NULL,
    return_date     TIMESTAMP         NOT NULL,
    created_at      TIMESTAMP WITHOUT TIME ZONE,
    modified_at     TIMESTAMP WITHOUT TIME ZONE,
    created_by      VARCHAR(64),
    modified_by     VARCHAR(64),
    FOREIGN KEY (user_id) REFERENCES users (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT,
    FOREIGN KEY (order_status_id) REFERENCES order_status (id)
        ON UPDATE CASCADE ON DELETE SET DEFAULT
);

DROP TABLE IF EXISTS book CASCADE;
CREATE TABLE IF NOT EXISTS book
(
    id                       BIGSERIAL PRIMARY KEY,
    title                    VARCHAR(255)      NOT NULL,
    subtitle                 VARCHAR(255),
    year                     INTEGER,
    quantity                 BIGINT            NOT NULL,
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

DROP TABLE IF EXISTS books_genres CASCADE;
CREATE TABLE IF NOT EXISTS books_genres
(
    id       BIGSERIAL PRIMARY KEY,
    book_id  BIGINT REFERENCES book (id) ON DELETE CASCADE,
    genre_id INTEGER REFERENCES book_genre (id) ON DELETE CASCADE
);

DROP TABLE IF EXISTS books_authors CASCADE;
CREATE TABLE IF NOT EXISTS books_authors
(
    id        BIGSERIAL PRIMARY KEY,
    book_id   BIGINT REFERENCES book (id) ON DELETE CASCADE ON UPDATE CASCADE,
    author_id BIGINT REFERENCES author (id) ON DELETE CASCADE ON UPDATE CASCADE
);

INSERT INTO author (id, first_name, last_name, created_at, created_by, birthday, date_death, image)
VALUES (1, 'Stieg', 'Larsson', '2022-11-1T00:00', 'stell', '1954-9-15',
        '2004-11-9', 'author_1.jpg'),

       (2, 'Douglas', 'Adams', '2022-11-1T00:00', 'stell', '1952-3-11',
        '2001-5-11', 'author_2.jpg'),

       (3, 'Peter', 'Benchley', '2022-11-1T00:00', 'stell', '1940-5-8',
        '2006-2-11', 'author_3.jpg'),

       (4, 'Dan', 'Brown', '2022-11-1T00:00', 'stell', '1964-6-22',
        null, 'author_4.jpg'),

       (5, 'Lewis', 'Carroll', '2022-11-1T00:00', 'stell', '1832-1-27',
        '1898-1-14', 'author_5.jpg'),

       (6, 'Paulo', 'Coelho', '2022-11-1T00:00', 'stell', '1947-8-24',
        null, 'author_6.jpg'),

       (7, 'William', 'Gilmore Simms', '2022-11-1T00:00', 'stell',
        '1806-4-17', '1870-06-11', 'author_7.jpeg'),

       (8, 'W', 'Jason Gilmore', '2022-11-1T00:00', 'stell', null,
        null, 'avatar_1.jpg'),

       (9, 'Stephen', 'King', '2022-11-1T00:00', 'stell', '1947-9-21',
        null, 'author_9.jpeg'),

       (10, 'James', 'Luceno', '2022-11-1T00:00', 'stell', null,
        null, 'avatar_1.jpg'),

       (11, 'Stephenie', 'Meyer', '2022-11-1T00:00', 'stell',
        '1973-12-24', null, 'author_11.jpg'),

       (12, 'Margaret', 'Mitchell', '2022-11-1T00:00', 'stell',
        '1900-11-8', '1949-9-16', 'author_12.jpeg'),

       (13, 'Kjelle A', 'Nordstrom', '2022-11-1T00:00', 'stell',
        '1958-2-26', null, 'avatar_1.jpg'),

       (14, 'Tom', 'Peters', '2022-11-1T00:00', 'stell', '1942-11-7',
        null, 'avatar_1.jpg'),

       (15, 'David', 'Pogue', '2022-11-1T00:00', 'stell', '1963-3-9',
        null, 'author_15.jpg'),

       (16, 'David', 'Reynolds', '2022-11-1T00:00', 'stell', null,
        null, 'avatar_1.jpg'),

       (17, 'Jonas', 'Ridderstråle', '2022-11-1T00:00', 'stell',
        '1966-9-26', null, 'avatar_1.jpg'),

       (18, 'J', 'Rowling', '2022-11-1T00:00', 'stell', '1965-7-31',
        null, 'author_18.jpg'),

       (19, 'J', 'Tolkien', '2022-11-1T00:00', 'stell', '1892-2-3',
        '1973-9-2', 'author_19.jpg');

SELECT SETVAL('author_id_seq', (SELECT MAX(id) FROM author));

INSERT INTO book_language (id, name)
VALUES (1, 'English'),
       (2, 'German'),
       (3, 'French'),
       (4, 'Engels'),
       (5, 'Afrikaans'),
       (6, 'Albanian'),
       (7, 'Arabic'),
       (8, 'Armenian'),
       (9, 'Azerbaijani'),
       (10, 'Basque'),
       (11, 'Belarusian'),
       (12, 'Bulgarian'),
       (13, 'Catalan'),
       (14, 'Chinese'),
       (15, 'Croatian'),
       (16, 'Czech'),
       (17, 'Danish'),
       (18, 'Dutch'),
       (19, 'Estonian'),
       (20, 'Filipino'),
       (21, 'Finnish'),
       (22, 'Galician'),
       (23, 'Georgian'),
       (24, 'Greek'),
       (25, 'Hebrew'),
       (26, 'Hindi'),
       (27, 'Hungarian'),
       (28, 'Icelandic'),
       (29, 'Indonesian'),
       (30, 'Irish'),
       (31, 'Italian'),
       (32, 'Japanese'),
       (33, 'Korean'),
       (34, 'Latin'),
       (35, 'Latvian'),
       (36, 'Lithuanian'),
       (37, 'Macedonian'),
       (38, 'Malay'),
       (39, 'Maltese'),
       (40, 'Norwegian'),
       (41, 'Persian'),
       (42, 'Polish'),
       (43, 'Portuguese'),
       (44, 'Romanian'),
       (45, 'Russian'),
       (46, 'Serbian'),
       (47, 'Slovak'),
       (48, 'Slovenian'),
       (49, 'Spanish'),
       (50, 'Swahili'),
       (51, 'Swedish'),
       (52, 'Thai'),
       (53, 'Turkish'),
       (54, 'Ukrainian'),
       (55, 'Urdu'),
       (56, 'Vietnamese'),
       (57, 'Welsh'),
       (58, 'Yiddish');

SELECT SETVAL('book_language_id_seq', (SELECT MAX(id) FROM book_language));

INSERT INTO book_format (id, name)
VALUES (1, 'Mass Market Paperback'),
       (2, 'Hardcover'),
       (3, 'Paperback'),
       (4, 'Ebook'),
       (5, 'Softcover'),
       (6, 'Kindle'),
       (7, 'Magazine'),
       (8, 'Turtleback'),
       (9, 'Audible');

SELECT SETVAL('book_format_id_seq', (SELECT MAX(id) FROM book_format));

INSERT INTO book_publishing_house (id, name, created_at, created_by)
VALUES (1, 'Apress', '2022-11-1T00:00', 'stell'),
       (2, 'Arthur A. Levine Books', '2022-11-1T00:00', 'stell'),
       (3, 'CreateSpace', '2022-11-1T00:00', 'stell'),
       (4, 'Crown', '2022-11-1T00:00', 'stell'),
       (5, 'DK CHILDREN', '2022-11-1T00:00', 'stell'),
       (6, 'Dorling Kindersley Verlag', '2022-11-1T00:00', 'stell'),
       (7, 'Financial Times Management', '2022-11-1T00:00', 'stell'),
       (8, 'HarperOne', '2022-11-1T00:00', 'stell'),
       (9, 'Jean-Claude Lattès', '2022-11-1T00:00', 'stell'),
       (10, 'Little, Brown Books for Young Readers', '2022-11-1T00:00', 'stell'),
       (11, 'Mariner Books', '2022-11-1T00:00', 'stell'),
       (12, 'Pogue Press', '2022-11-1T00:00', 'stell'),
       (13, 'Random House', '2022-11-1T00:00', 'stell'),
       (14, 'Scribner', '2022-11-1T00:00', 'stell'),
       (15, 'ACT', '2022-11-1T00:00', 'stell');

SELECT SETVAL('book_publishing_house_id_seq', (SELECT MAX(id) FROM book_publishing_house));

INSERT INTO book_series (id, name, created_at, created_by)
VALUES (1, 'The Experts Voice', '2022-11-1T00:00', 'stell'),
       (2, 'Millennium', '2022-11-1T00:00', 'stell'),
       (3, 'Dark town', '2022-11-1T00:00', 'stell'),
       (4, 'Harry Potter', '2022-11-1T00:00', 'stell'),
       (5, 'The Twilight Saga', '2022-11-1T00:00', 'stell');

SELECT SETVAL('book_series_id_seq', (SELECT MAX(id) FROM book_series));

INSERT INTO book_genre (id, name, created_at, created_by)
VALUES (1, 'Business', '2022-11-1T00:00', 'stell'),
       (2, 'Children', '2022-11-1T00:00', 'stell'),
       (3, 'Classic', '2022-11-1T00:00', 'stell'),
       (4, 'Fantasy', '2022-11-1T00:00', 'stell'),
       (5, 'Computing', '2022-11-1T00:00', 'stell'),
       (6, 'Fiction', '2022-11-1T00:00', 'stell'),
       (7, 'Horror', '2022-11-1T00:00', 'stell'),
       (8, 'Romance', '2022-11-1T00:00', 'stell'),
       (9, 'Web Programming', '2022-11-1T00:00', 'stell'),
       (10, 'Computer hackers', '2022-11-1T00:00', 'stell'),
       (11, 'Corruption', '2022-11-1T00:00', 'stell');

SELECT SETVAL('book_genre_id_seq', (SELECT MAX(id) FROM book_genre));

INSERT INTO book_status (id, name)
VALUES (1, 'Unconfirmed'),
       (2, 'Reading room'),
       (3, 'Home');

SELECT SETVAL('book_status_id_seq', (SELECT MAX(id) FROM book_status));

INSERT INTO user_role (id, name)
VALUES (1, 'ROLE_GUEST'),
       (2, 'ROLE_ADMIN'),
       (3, 'ROLE_READER'),
       (4, 'ROLE_LIBRARIAN');

SELECT SETVAL('user_role_id_seq', (SELECT MAX(id) FROM user_role));

INSERT INTO user_status (id, name)
VALUES (1, 'Guest'),
       (2, 'Unconfirmed'),
       (3, 'Active'),
       (4, 'Locked');

SELECT SETVAL('user_status_id_seq', (SELECT MAX(id) FROM user_status));

INSERT INTO users (id, username, first_name, last_name, email, password, user_role_id, user_status_id, birthday, image,
                   created_at, created_by)
VALUES (1, 'guest', 'Guest', 'Guest', 'guest@gmail.com',
        '{bcrypt}$2a$12$fT/yEHr2gj7NHKvsOYh2KOcySYodV/.q656tJQDhf9uzphegNZiCy',
        1, 1, '2000-1-1', 'avatar_1.jpg', '2022-11-1T00:00',
        'stell'),

       (2, 'librarian', 'Sveta', 'Svetikova', 'sveta@gmail.com',
        '{bcrypt}$2a$12$rypMBywDDflUnbErMUO0A.JdZUbu/SK6hkAdsBZmjQTTwVFl00dzG', 4, 3,
        '1987-4-5', 'avatar_2.jpg', '2022-11-1T00:00', 'stell'),

       (3, 'reader', 'Petr', 'Petrov', 'petr@gmail.com',
        '{bcrypt}$2a$12$t0LpCTQb7NsIA/SY1GKaDewJFzoZmsE8Otog1lD67Vo1UXPeSUwq6', 3, 2,
        '1999-11-18', 'avatar_3.jpg', '2022-11-1T00:00', 'stell'),

       (4, 'serge', 'Sergey', 'Serge', 'sergey@gmail.com',
        '{bcrypt}$2a$12$Hl8wKbYspJ4EPcYFCr6gnuGKbjx7Hcg/AdvQn5QRb7RJsO9jccg0e', 3, 3,
        '2003-4-9', 'avatar_4.jpg', '2022-11-1T00:00', 'stell'),

       (5, 'admin', 'Ivan', 'Ivanov', 'ivan@gmail.com',
        '{bcrypt}$2a$12$arbqMqs83k2rc6QDCb5sH.h.ASaEmenOXnB9b1YOUIKbf1rPiinLi', 2, 3,
        '1989-7-7', 'avatar_5.jpg', '2022-11-1T00:00', 'stell');

SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

INSERT INTO order_status (id, name)
VALUES (1, 'Unconfirmed'),
       (2, 'Approved'),
       (3, 'Blocked');

SELECT SETVAL('order_status_id_seq', (SELECT MAX(id) FROM order_status));

INSERT INTO orders (id, user_id, order_status_id, order_date, return_date, created_at, created_by)
VALUES (1, 3, 3, '2022-10-23T10:00', '2022-10-17T10:00',
        '2022-10-23T10:00', 'stell'),

       (2, 2, 1, '2022-10-23T14:35', '2022-11-23T14:35',
        '2022-10-23T14:35', 'stell'),

       (3, 3, 3, '2022-10-24T14:35', '2022-11-1T15:00',
        '2022-10-24T14:35', 'stell'),

       (4, 4, 2, '2022-10-25T18:00', '2022-10-29T11:45',
        '2022-10-25T18:00', 'stell');

SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));

INSERT INTO book (id, title, year, isbn_10, isbn_13, book_language_id, book_format_id,
                  book_publishing_house_id, book_series_id, order_id, book_status_id, created_at, created_by, image,
                  quantity)
VALUES (1, 'Twilight', 2008, '0316038377', '9780316038379', 1, 1,
        10, 5, 1, 3, '2022-11-1T00:00', 'stell',
        'cover_1.jpg', 3),

       (2, 'Alice In Wonderland', 2008, '144042909X', '9781440429095', 1,
        3, 3, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_2.jpg', 4),

       (3, 'Jaws', 2005, '1400064562', '9781400064564', 1, 2,
        13, null, 2, 3, '2022-11-1T00:00', 'stell',
        'cover_3.jpg', 1),

       (4, 'Gone with the Wind', 2007, '1416548890', '9781416548898', 1,
        3, 14, null, 1, 3, '2022-11-1T00:00',
        'stell', 'cover_4.jpg', 4),

       (5, 'The Lord of the Rings', 2005, '0618640150', '9780618640157', 1,
        3, 11, null, 3, 1, '2022-11-1T00:00',
        'stell', 'cover_5.jpeg', 5),

       (6, 'Code Da Vinci', 2004, '2709624931', '9782709624930', 3,
        3, 9, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_6.jpeg', 9),

       (7, 'Duma Key', 2001, '073949015X', '9780739490150', 1, 2,
        14, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_7.jpg', 4),

       (8, 'Re-imagine!', 2004, '3831005796', '9783831005796', 2,
        2, 6, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_8.jpg', 1),

       (9, 'Funky Business', 2002, '0273659073', '9780273659075', 1,
        3, 7, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_9.png', 3),

       (10, 'iPhone: The Missing Manual', 2008, '0596521677', '9780596521677',
        1, 3, 12, null, 3, 1,
        '2022-11-1T00:00', 'stell', 'cover_10.jpg', 7),

       (11, 'Star Wars: The Complete Visual Dictionary', 2006, '0756622387', '9780756622381',
        1, 2, 5, null, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_11.jpg', 4),

       (12, 'Harry Potter and the Order of the Phoenix', 2003, '043935806X', '9780439358064',
        null, 2, 2, 4, 4, 1,
        '2022-11-1T00:00', 'stell', 'cover_12.jpg', 4),

       (13, 'The Alchemist', 2006, '0060887966', '9780060887964', 1,
        2, 8, null, null, 1, '2022-11-1T00:00',
        'stell', 'cover_13.jpg', 10),

       (14, 'The Hitchhiker''s Guide to the Galaxy', 2004, '1400052920', '9781400052929',
        1, 2, 4, null, null,
        1, '2022-11-1T00:00', 'stell', 'cover_14.png', 11),

       (15, 'Beginning PHP 5 and MySQL 5', 2006, '1590595521', '9781590595527',
        4, 3, 1, 1, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_15.jpg', 1),

       (16, 'Mężczyźni, którzy nienawidzą object', 2008, '8375540595', '9788375540598',
        42, null, null, 2, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_16.jpg', 3),

       (17, 'Dziewczyna, która igrała z ogniem', 2009, '8375540900', '9788375540901',
        42, null, null, 2, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_17.jpg', 5),

       (18, 'Затмение', 2009, '9851666432', '9789851666436', 6,
        null, 15, 5, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_18.jpg', 3),

       (19, 'Новолуние', 2008, null, '9785170485659', 6, null,
        15, 5, null, 1, '2022-11-1T00:00',
        'stell', 'cover_19.jpeg', 4),

       (20, 'The Girl Who Kicked the Hornet''s Nest', 2009, null, '9781906694173',
        1, 3, null, 2, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_20.jpg', 7),

       (21, 'The Girl in the Spider''s Web', null, null, '9781848667785',
        1, null, null, 2, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_21.jpg', 5),

       (22, 'Dark Tower I', 2016, '1501161806', '9781501161803', 1,
        null, null, 3, null, 1,
        '2022-11-1T00:00', 'stell', 'cover_22.jpeg', 4);

SELECT SETVAL('book_id_seq', (SELECT MAX(id) FROM book));

INSERT INTO books_authors (id, book_id, author_id)
VALUES (1, 1, 11),
       (2, 2, 5),
       (3, 3, 3),
       (4, 4, 12),
       (5, 5, 19),
       (6, 6, 4),
       (7, 7, 9),
       (8, 8, 14),
       (9, 9, 17),
       (10, 9, 13),
       (11, 10, 15),
       (12, 11, 16),
       (13, 11, 10),
       (14, 12, 18),
       (15, 13, 6),
       (16, 14, 2),
       (17, 15, 8),
       (18, 15, 7),
       (19, 16, 1),
       (20, 17, 1),
       (21, 18, 11),
       (22, 19, 11),
       (23, 20, 1),
       (24, 21, 1),
       (25, 22, 9);

SELECT SETVAL('books_authors_id_seq', (SELECT MAX(id) FROM books_authors));

INSERT INTO books_genres (id, book_id, genre_id)
VALUES (1, 1, 8),
       (2, 2, 2),
       (3, 2, 3),
       (4, 3, 6),
       (5, 3, 7),
       (6, 4, 3),
       (7, 4, 8),
       (8, 17, 10),
       (9, 16, 11),
       (10, 5, 3),
       (11, 5, 4),
       (12, 6, 6),
       (13, 7, 6),
       (14, 7, 7),
       (15, 8, 1),
       (16, 9, 1),
       (17, 10, 5),
       (18, 11, 4),
       (19, 11, 6),
       (20, 12, 2),
       (21, 12, 4),
       (22, 13, 3),
       (23, 13, 6),
       (24, 14, 6),
       (25, 15, 5),
       (26, 15, 9),
       (27, 22, 6);

SELECT SETVAL('books_genres_id_seq', (SELECT MAX(id) FROM books_genres));