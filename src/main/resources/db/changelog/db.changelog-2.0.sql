--liquibase formatted sql

--changeset stell:1
INSERT INTO author_role (id, name)
VALUES (1, 'Adapter'),
       (2, 'Annotator'),
       (3, 'Arranger'),
       (4, 'Artist'),
       (5, 'Associated Name'),
       (6, 'Author'),
       (7, 'Author of Quotations'),
       (8, 'Author of Afterword'),
       (9, 'Author of Introduction'),
       (10, 'Bibliographic Antecedent'),
       (11, 'Book Producer'),
       (12, 'Collaborator'),
       (13, 'Commentator'),
       (14, 'Compiler'),
       (15, 'Designer'),
       (16, 'Editor'),
       (17, 'Illustrator'),
       (18, 'Lyricist'),
       (19, 'Metadata Contact'),
       (20, 'Musician'),
       (21, 'Narrator'),
       (22, 'Other'),
       (23, 'Photographer'),
       (24, 'Printer'),
       (25, 'Reviewer'),
       (26, 'Sponsor'),
       (27, 'Thesis Advisor'),
       (28, 'Transcriber'),
       (29, 'Translator');

--changeset stell:2
SELECT SETVAL('author_role_id_seq', (SELECT MAX(id) FROM author_role));

--changeset stell:3
INSERT INTO author (id, first_name, last_name, author_role_id)
VALUES (1, 'Stig', 'Larsson', 6),
       (2, 'Douglas', 'Adams', null),
       (3, 'Peter', 'Peter', null),
       (4, 'Dan', 'Brown', 6),
       (5, 'Lewis', 'Carroll', 6),
       (6, 'Paulo', 'Coelho', 6),
       (7, 'W', 'Gilmore', null),
       (8, 'W', 'Jason Gilmore', null),
       (9, 'Stephen', 'King', 6),
       (10, 'James', 'Luceno', null),
       (11, 'Stephenie', 'Meyer', 6),
       (12, 'Margaret', 'Mitchell', 6),
       (13, 'Kjelle A', 'Nordstrom', null),
       (14, 'Tom', 'Peters', null),
       (15, 'David', 'Pogue', null),
       (16, 'David', 'Reynolds', null),
       (17, 'Jonas', 'Ridderstrale', null),
       (18, 'J', 'Rowling', 6),
       (19, 'J', 'Tolkien', 6);

--changeset stell:4
SELECT SETVAL('author_id_seq', (SELECT MAX(id) FROM author));

--changeset stell:5
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

--changeset stell:6
SELECT SETVAL('book_language_id_seq', (SELECT MAX(id) FROM book_language));

--changeset stell:7
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

--changeset stell:8
SELECT SETVAL('book_format_id_seq', (SELECT MAX(id) FROM book_format));

--changeset stell:9
INSERT INTO book_publishing_house (id, name)
VALUES (1, 'Apress'),
       (2, 'Arthur A. Levine Books'),
       (3, 'CreateSpace'),
       (4, 'Crown'),
       (5, 'DK CHILDREN'),
       (6, 'Dorling Kindersley Verlag'),
       (7, 'Financial Times Management'),
       (8, 'HarperOne'),
       (9, 'Jean-Claude Lattès'),
       (10, 'Little, Brown Books for Young Readers'),
       (11, 'Mariner Books'),
       (12, 'Pogue Press'),
       (13, 'Random House'),
       (14, 'Scribner'),
       (15, 'ACT');

--changeset stell:10
SELECT SETVAL('book_publishing_house_id_seq', (SELECT MAX(id) FROM book_publishing_house));

--changeset stell:11
INSERT INTO book_series (id, name)
VALUES (1, 'The Experts Voice'),
       (2, 'Millennium'),
       (3, 'Dark town');

--changeset stell:12
SELECT SETVAL('book_series_id_seq', (SELECT MAX(id) FROM book_series));

--changeset stell:13
INSERT INTO book_genre (id, name)
VALUES (1, 'Business'),
       (2, 'Children'),
       (3, 'Classic'),
       (4, 'Fantasy'),
       (5, 'Computing'),
       (6, 'Fiction'),
       (7, 'Horror'),
       (8, 'Romance'),
       (9, 'Web Programming'),
       (10, 'Computer hackers'),
       (11, 'Corruption');

--changeset stell:14
SELECT SETVAL('book_genre_id_seq', (SELECT MAX(id) FROM book_genre));

--changeset stell:15
INSERT INTO book_status (id, name)
VALUES (1, 'Default'),
       (2, 'Reading room'),
       (3, 'Home'),
       (4, 'Deleted');

--changeset stell:16
SELECT SETVAL('book_status_id_seq', (SELECT MAX(id) FROM book_status));

--changeset stell:17
INSERT INTO user_role (id, name)
VALUES (1, 'Default'),
       (2, 'Admin'),
       (3, 'Reader'),
       (4, 'Guest'),
       (5, 'Librarian');

--changeset stell:18
SELECT SETVAL('user_role_id_seq', (SELECT MAX(id) FROM user_role));

--changeset stell:19
INSERT INTO user_status (id, name)
VALUES (1, 'Default'),
       (2, 'Unconfirmed'),
       (3, 'Active'),
       (4, 'Locked');

--changeset stell:20
SELECT SETVAL('user_status_id_seq', (SELECT MAX(id) FROM user_status));

--changeset stell:21
INSERT INTO users (id, login, first_name, last_name, email, password, user_role_id, user_status_id, birthday, image)
VALUES (1, 'default', 'default', 'default', 'default@gmil.com',
        '{noop}default', 1, 1, '2000-1-1', 'avatar_1.jpg'),
       (2, 'librarian', 'Sveta', 'Svetikova', 'sveta@gmail.com',
        '{noop}123456', 5, 3, '1987-4-5', 'avatar_2.jpg'),
       (3, 'reader', 'Petr', 'Petrov', 'petr@gmail.com', '{noop}456789',
        3, 2, '1999-11-18', 'avatar_3.jpg'),
       (4, 'guest', 'Sergey', 'Serge', 'sergey@gmail.com', '{noop}qwerty',
        3, 3, '2003-4-9', 'avatar_4.jpg'),
       (5, 'admin', 'Ivan', 'Ivanov', 'ivan@gmail.com', '{noop}123456',
        2, 3, '1989-7-7', 'avatar_5.jpg');

--changeset stell:22
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

--changeset stell:23
INSERT INTO order_status (id, name)
VALUES (1, 'Unconfirmed'),
       (2, 'Approved'),
       (3, 'Blocked');

--changeset stell:24
SELECT SETVAL('order_status_id_seq', (SELECT MAX(id) FROM order_status));

--changeset stell:25
INSERT INTO order_type (id, name)
VALUES (1, 'Default'),
       (2, 'Cash'),
       (3, 'Online'),
       (4, 'Card');

--changeset stell:26
SELECT SETVAL('order_type_id_seq', (SELECT MAX(id) FROM order_type));

--changeset stell:27
INSERT INTO orders (id, user_id, order_status_id, order_type_id, order_date, return_date)
VALUES (1, 3, 3, 3, '2022-10-23T10:00', '2022-10-17T10:00'),
       (2, 2, 1, 2, '2022-10-23T14:35', '2022-11-23T14:35'),
       (3, 3, 3, 1, '2022-10-24T14:35', '2022-11-1T15:00'),
       (4, 4, 2, 1, '2022-10-25T18:00', '2022-10-29T11:45');

--changeset stell:28
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));

--changeset stell:29
INSERT INTO book (id, title, year, page, isbn_10, isbn_13, image_path, book_language_id, book_format_id,
                  book_publishing_house_id, book_series_id, order_id, book_status_id)
VALUES (1, 'Twilight', 2008, 544, 0316038377, 9780316038379, null,
        1, 1, 10, null, 1, 3),
       (2, 'Alice In Wonderland', 2008, 94, '144042909X', 9781440429095, null,
        1, 3, 3, null, null, 1),
       (3, 'Jaws', 2005, 320, 1400064562, 978140064564, null, 1,
        2, 13, null, 2, 3),
       (4, 'Gone with the Wind', 2007, 960, 1416548890, 9781416548898, null,
        1, 3, 14, null, 1, 3),
       (5, 'The Lord of the Rings', 2005, 1216, 0618640150, 9780618640157,
        null, 1, 3, 11, null, null,
        1),
       (6, 'Code Da Vinci', 2004, 574, 2709624931, 9782709624930, null,
        3, 3, 9, null, null, 1),
       (7, 'Duma Key', 2001, 1104, '073949015X', 9780739490150, null,
        1, 2, 14, null, null, 1),
       (8, 'Re-imagine!', 2004, 352, 3831005796, 9783831005796, null,
        2, 2, 6, null, null, 1),
       (9, 'Funky Business', 2002, 288, 0273659073, 9780273659075, null,
        1, 3, 7, null, null, 1),
       (10, 'iPhone: The Missing Manual', 2008, 376, 0596521677, 9780596521677,
        null, 1, 3, 12, null, null,
        1),
       (11, 'Star Wars: The Complete Visual Dictionary', 2006, 272, 0756622387,
        9780756622381, null, 1, 2, 5, null,
        null, 1),
       (12, 'Harry Potter and the Order of the Phoenix', 2003, 896, '043935806X',
        9780439358064, null, null, 2, 2,
        null, null, 1),
       (13, 'The Alchemist', 2006, 192, 0060887966, 9780060887964, null,
        1, 2, 8, null, null, 1),
       (14, 'The Hitchhiker''s Guide to the Galaxy', 2004, 224, 1400052920,
        9781400052929, null, 1, 2, 4, null,
        null, 1),
       (15, 'Beginning PHP 5 and MySQL 5', 2006, 900, 1590595521, 9781590595527,
        null, 4, 3, 1, 1,
        null, 1),
       (16, 'Mężczyźni, którzy nienawidzą object', 2008, 633, 8375540595,
        9788375540598, null, 42, null, null,
        2, null, 1),
       (17, 'Dziewczyna, która igrała z ogniem', 2009, 699, 8375540900, 9788375540901,
        null, 42, null, null, 2, null,
        1);

--changeset stell:30
SELECT SETVAL('book_id_seq', (SELECT MAX(id) FROM book));

--changeset stell:31
INSERT INTO books_authors (id, book_id, author_id)
VALUES (1, (SELECT b.id FROM book b WHERE b.id = 1), (SELECT a.id FROM author a WHERE a.id = 11)),
       (2, (SELECT b.id FROM book b WHERE b.id = 2), (SELECT a.id FROM author a WHERE a.id = 5)),
       (3, (SELECT b.id FROM book b WHERE b.id = 3), (SELECT a.id FROM author a WHERE a.id = 3)),
       (4, (SELECT b.id FROM book b WHERE b.id = 4), (SELECT a.id FROM author a WHERE a.id = 12)),
       (5, (SELECT b.id FROM book b WHERE b.id = 5), (SELECT a.id FROM author a WHERE a.id = 19)),
       (6, (SELECT b.id FROM book b WHERE b.id = 6), (SELECT a.id FROM author a WHERE a.id = 4)),
       (7, (SELECT b.id FROM book b WHERE b.id = 7), (SELECT a.id FROM author a WHERE a.id = 9)),
       (8, (SELECT b.id FROM book b WHERE b.id = 8), (SELECT a.id FROM author a WHERE a.id = 14)),
       (9, (SELECT b.id FROM book b WHERE b.id = 9), (SELECT a.id FROM author a WHERE a.id = 17)),
       (10, (SELECT b.id FROM book b WHERE b.id = 9), (SELECT a.id FROM author a WHERE a.id = 13)),
       (11, (SELECT b.id FROM book b WHERE b.id = 10), (SELECT a.id FROM author a WHERE a.id = 15)),
       (12, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT a.id FROM author a WHERE a.id = 16)),
       (13, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT a.id FROM author a WHERE a.id = 10)),
       (14, (SELECT b.id FROM book b WHERE b.id = 12), (SELECT a.id FROM author a WHERE a.id = 18)),
       (15, (SELECT b.id FROM book b WHERE b.id = 13), (SELECT a.id FROM author a WHERE a.id = 6)),
       (16, (SELECT b.id FROM book b WHERE b.id = 14), (SELECT a.id FROM author a WHERE a.id = 2)),
       (17, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT a.id FROM author a WHERE a.id = 8)),
       (18, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT a.id FROM author a WHERE a.id = 7)),
       (19, (SELECT b.id FROM book b WHERE b.id = 16), (SELECT a.id FROM author a WHERE a.id = 1)),
       (20, (SELECT b.id FROM book b WHERE b.id = 17), (SELECT a.id FROM author a WHERE a.id = 1));

--changeset stell:32
SELECT SETVAL('books_authors_id_seq', (SELECT MAX(id) FROM books_authors));

--changeset stell:33
INSERT INTO book_additional (id, book_id, volume, serial_no, price, link)
VALUES (1, 1, null, null, 7.99, null),
       (2, 2, null, null, 6.95, null),
       (3, 3, null, null, 20.00, null),
       (4, 4, null, null, 18.00, null),
       (5, 5, null, null, 20.00, null),
       (6, 6, null, null, 35.00, null),
       (7, 7, null, null, 28.00, null),
       (8, 8, null, null, 20.00, null),
       (9, 9, null, null, 28.50, null),
       (10, 10, null, null, 24.99, null),
       (11, 11, null, null, 40.00, null),
       (12, 12, null, null, 29.99, null),
       (13, 13, null, null, 30.00, null),
       (14, 14, null, null, 15.00, null),
       (15, 15, null, null, 44.99, null);

--changeset stell:34
SELECT SETVAL('book_additional_id_seq', (SELECT MAX(id) FROM book_additional));

--changeset stell:35
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
       (26, 15, 9);

--changeset stell:36
SELECT SETVAL('books_genres_id_seq', (SELECT MAX(id) FROM books_genres));