--liquibase formatted sql

--changeset stell:1
INSERT INTO author_role (id, name, created_at, created_by)
VALUES (1, 'Adapter', '2022-11-1T00:00', 'stell'),
       (2, 'Annotator', '2022-11-1T00:00', 'stell'),
       (3, 'Arranger', '2022-11-1T00:00', 'stell'),
       (4, 'Artist', '2022-11-1T00:00', 'stell'),
       (5, 'Associated Name', '2022-11-1T00:00', 'stell'),
       (6, 'Author', '2022-11-1T00:00', 'stell'),
       (7, 'Author of Quotations', '2022-11-1T00:00', 'stell'),
       (8, 'Author of Afterword', '2022-11-1T00:00', 'stell'),
       (9, 'Author of Introduction', '2022-11-1T00:00', 'stell'),
       (10, 'Bibliographic Antecedent', '2022-11-1T00:00', 'stell'),
       (11, 'Book Producer', '2022-11-1T00:00', 'stell'),
       (12, 'Collaborator', '2022-11-1T00:00', 'stell'),
       (13, 'Commentator', '2022-11-1T00:00', 'stell'),
       (14, 'Compiler', '2022-11-1T00:00', 'stell'),
       (15, 'Designer', '2022-11-1T00:00', 'stell'),
       (16, 'Editor', '2022-11-1T00:00', 'stell'),
       (17, 'Illustrator', '2022-11-1T00:00', 'stell'),
       (18, 'Lyricist', '2022-11-1T00:00', 'stell'),
       (19, 'Metadata Contact', '2022-11-1T00:00', 'stell'),
       (20, 'Musician', '2022-11-1T00:00', 'stell'),
       (21, 'Narrator', '2022-11-1T00:00', 'stell'),
       (22, 'Other', '2022-11-1T00:00', 'stell'),
       (23, 'Photographer', '2022-11-1T00:00', 'stell'),
       (24, 'Printer', '2022-11-1T00:00', 'stell'),
       (25, 'Reviewer', '2022-11-1T00:00', 'stell'),
       (26, 'Sponsor', '2022-11-1T00:00', 'stell'),
       (27, 'Thesis Advisor', '2022-11-1T00:00', 'stell'),
       (28, 'Transcriber', '2022-11-1T00:00', 'stell'),
       (29, 'Translator', '2022-11-1T00:00', 'stell');

--changeset stell:2
SELECT SETVAL('author_role_id_seq', (SELECT MAX(id) FROM author_role));

--changeset stell:3
INSERT INTO author (id, first_name, last_name, author_role_id, created_at, created_by)
VALUES (1, 'Stig', 'Larsson', 6, '2022-11-1T00:00', 'stell'),
       (2, 'Douglas', 'Adams', null, '2022-11-1T00:00', 'stell'),
       (3, 'Peter', 'Peter', null, '2022-11-1T00:00', 'stell'),
       (4, 'Dan', 'Brown', 6, '2022-11-1T00:00', 'stell'),
       (5, 'Lewis', 'Carroll', 6, '2022-11-1T00:00', 'stell'),
       (6, 'Paulo', 'Coelho', 6, '2022-11-1T00:00', 'stell'),
       (7, 'W', 'Gilmore', null, '2022-11-1T00:00', 'stell'),
       (8, 'W', 'Jason Gilmore', null, '2022-11-1T00:00', 'stell'),
       (9, 'Stephen', 'King', 6, '2022-11-1T00:00', 'stell'),
       (10, 'James', 'Luceno', null, '2022-11-1T00:00', 'stell'),
       (11, 'Stephenie', 'Meyer', 6, '2022-11-1T00:00', 'stell'),
       (12, 'Margaret', 'Mitchell', 6, '2022-11-1T00:00', 'stell'),
       (13, 'Kjelle A', 'Nordstrom', null, '2022-11-1T00:00',
        'stell'),
       (14, 'Tom', 'Peters', null, '2022-11-1T00:00', 'stell'),
       (15, 'David', 'Pogue', null, '2022-11-1T00:00', 'stell'),
       (16, 'David', 'Reynolds', null, '2022-11-1T00:00', 'stell'),
       (17, 'Jonas', 'Ridderstrale', null, '2022-11-1T00:00',
        'stell'),
       (18, 'J', 'Rowling', 6, '2022-11-1T00:00', 'stell'),
       (19, 'J', 'Tolkien', 6, '2022-11-1T00:00', 'stell');

--changeset stell:4
SELECT SETVAL('author_id_seq', (SELECT MAX(id) FROM author));

--changeset stell:5
INSERT INTO book_language (id, name, created_at, created_by)
VALUES (1, 'English', '2022-11-1T00:00', 'stell'),
       (2, 'German', '2022-11-1T00:00', 'stell'),
       (3, 'French', '2022-11-1T00:00', 'stell'),
       (4, 'Engels', '2022-11-1T00:00', 'stell'),
       (5, 'Afrikaans', '2022-11-1T00:00', 'stell'),
       (6, 'Albanian', '2022-11-1T00:00', 'stell'),
       (7, 'Arabic', '2022-11-1T00:00', 'stell'),
       (8, 'Armenian', '2022-11-1T00:00', 'stell'),
       (9, 'Azerbaijani', '2022-11-1T00:00', 'stell'),
       (10, 'Basque', '2022-11-1T00:00', 'stell'),
       (11, 'Belarusian', '2022-11-1T00:00', 'stell'),
       (12, 'Bulgarian', '2022-11-1T00:00', 'stell'),
       (13, 'Catalan', '2022-11-1T00:00', 'stell'),
       (14, 'Chinese', '2022-11-1T00:00', 'stell'),
       (15, 'Croatian', '2022-11-1T00:00', 'stell'),
       (16, 'Czech', '2022-11-1T00:00', 'stell'),
       (17, 'Danish', '2022-11-1T00:00', 'stell'),
       (18, 'Dutch', '2022-11-1T00:00', 'stell'),
       (19, 'Estonian', '2022-11-1T00:00', 'stell'),
       (20, 'Filipino', '2022-11-1T00:00', 'stell'),
       (21, 'Finnish', '2022-11-1T00:00', 'stell'),
       (22, 'Galician', '2022-11-1T00:00', 'stell'),
       (23, 'Georgian', '2022-11-1T00:00', 'stell'),
       (24, 'Greek', '2022-11-1T00:00', 'stell'),
       (25, 'Hebrew', '2022-11-1T00:00', 'stell'),
       (26, 'Hindi', '2022-11-1T00:00', 'stell'),
       (27, 'Hungarian', '2022-11-1T00:00', 'stell'),
       (28, 'Icelandic', '2022-11-1T00:00', 'stell'),
       (29, 'Indonesian', '2022-11-1T00:00', 'stell'),
       (30, 'Irish', '2022-11-1T00:00', 'stell'),
       (31, 'Italian', '2022-11-1T00:00', 'stell'),
       (32, 'Japanese', '2022-11-1T00:00', 'stell'),
       (33, 'Korean', '2022-11-1T00:00', 'stell'),
       (34, 'Latin', '2022-11-1T00:00', 'stell'),
       (35, 'Latvian', '2022-11-1T00:00', 'stell'),
       (36, 'Lithuanian', '2022-11-1T00:00', 'stell'),
       (37, 'Macedonian', '2022-11-1T00:00', 'stell'),
       (38, 'Malay', '2022-11-1T00:00', 'stell'),
       (39, 'Maltese', '2022-11-1T00:00', 'stell'),
       (40, 'Norwegian', '2022-11-1T00:00', 'stell'),
       (41, 'Persian', '2022-11-1T00:00', 'stell'),
       (42, 'Polish', '2022-11-1T00:00', 'stell'),
       (43, 'Portuguese', '2022-11-1T00:00', 'stell'),
       (44, 'Romanian', '2022-11-1T00:00', 'stell'),
       (45, 'Russian', '2022-11-1T00:00', 'stell'),
       (46, 'Serbian', '2022-11-1T00:00', 'stell'),
       (47, 'Slovak', '2022-11-1T00:00', 'stell'),
       (48, 'Slovenian', '2022-11-1T00:00', 'stell'),
       (49, 'Spanish', '2022-11-1T00:00', 'stell'),
       (50, 'Swahili', '2022-11-1T00:00', 'stell'),
       (51, 'Swedish', '2022-11-1T00:00', 'stell'),
       (52, 'Thai', '2022-11-1T00:00', 'stell'),
       (53, 'Turkish', '2022-11-1T00:00', 'stell'),
       (54, 'Ukrainian', '2022-11-1T00:00', 'stell'),
       (55, 'Urdu', '2022-11-1T00:00', 'stell'),
       (56, 'Vietnamese', '2022-11-1T00:00', 'stell'),
       (57, 'Welsh', '2022-11-1T00:00', 'stell'),
       (58, 'Yiddish', '2022-11-1T00:00', 'stell');

--changeset stell:6
SELECT SETVAL('book_language_id_seq', (SELECT MAX(id) FROM book_language));

--changeset stell:7
INSERT INTO book_format (id, name, created_at, created_by)
VALUES (1, 'Mass Market Paperback', '2022-11-1T00:00', 'stell'),
       (2, 'Hardcover', '2022-11-1T00:00', 'stell'),
       (3, 'Paperback', '2022-11-1T00:00', 'stell'),
       (4, 'Ebook', '2022-11-1T00:00', 'stell'),
       (5, 'Softcover', '2022-11-1T00:00', 'stell'),
       (6, 'Kindle', '2022-11-1T00:00', 'stell'),
       (7, 'Magazine', '2022-11-1T00:00', 'stell'),
       (8, 'Turtleback', '2022-11-1T00:00', 'stell'),
       (9, 'Audible', '2022-11-1T00:00', 'stell');

--changeset stell:8
SELECT SETVAL('book_format_id_seq', (SELECT MAX(id) FROM book_format));

--changeset stell:9
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

--changeset stell:10
SELECT SETVAL('book_publishing_house_id_seq', (SELECT MAX(id) FROM book_publishing_house));

--changeset stell:11
INSERT INTO book_series (id, name, created_at, created_by)
VALUES (1, 'The Experts Voice', '2022-11-1T00:00', 'stell'),
       (2, 'Millennium', '2022-11-1T00:00', 'stell'),
       (3, 'Dark town', '2022-11-1T00:00', 'stell');

--changeset stell:12
SELECT SETVAL('book_series_id_seq', (SELECT MAX(id) FROM book_series));

--changeset stell:13
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

--changeset stell:14
SELECT SETVAL('book_genre_id_seq', (SELECT MAX(id) FROM book_genre));

--changeset stell:15
INSERT INTO book_status (id, name, created_at, created_by)
VALUES (1, 'Unconfirmed', '2022-11-1T00:00', 'stell'),
       (2, 'Reading room', '2022-11-1T00:00', 'stell'),
       (3, 'Home', '2022-11-1T00:00', 'stell');

--changeset stell:16
SELECT SETVAL('book_status_id_seq', (SELECT MAX(id) FROM book_status));

--changeset stell:17
INSERT INTO user_role (id, name, created_at, created_by)
VALUES (1, 'Guest', '2022-11-1T00:00', 'stell'),
       (2, 'Admin', '2022-11-1T00:00', 'stell'),
       (3, 'Reader', '2022-11-1T00:00', 'stell'),
       (4, 'Librarian', '2022-11-1T00:00', 'stell');

--changeset stell:18
SELECT SETVAL('user_role_id_seq', (SELECT MAX(id) FROM user_role));

--changeset stell:19
INSERT INTO user_status (id, name, created_at, created_by)
VALUES (1, 'Guest', '2022-11-1T00:00', 'stell'),
       (2, 'Unconfirmed', '2022-11-1T00:00', 'stell'),
       (3, 'Active', '2022-11-1T00:00', 'stell'),
       (4, 'Locked', '2022-11-1T00:00', 'stell');

--changeset stell:20
SELECT SETVAL('user_status_id_seq', (SELECT MAX(id) FROM user_status));

--changeset stell:21
INSERT INTO users (id, username, first_name, last_name, email, password, user_role_id, user_status_id, birthday, image,
                   created_at, created_by)
VALUES (1, 'guest', 'guest', 'guest', 'guest@gmil.com',
        '{noop}guest', 1, 1, '2000-1-1', 'avatar_1.jpg',
        '2022-11-1T00:00', 'stell'),
       (2, 'librarian', 'Sveta', 'Svetikova', 'sveta@gmail.com',
        '{noop}123456', 4, 3, '1987-4-5', 'avatar_2.jpg',
        '2022-11-1T00:00', 'stell'),
       (3, 'reader', 'Petr', 'Petrov', 'petr@gmail.com', '{noop}456789',
        3, 2, '1999-11-18', 'avatar_3.jpg', '2022-11-1T00:00',
        'reader'),
       (4, 'serge', 'Sergey', 'Serge', 'sergey@gmail.com', '{noop}qwerty',
        3, 3, '2003-4-9', 'avatar_4.jpg', '2022-11-1T00:00',
        'serge'),
       (5, 'admin', 'Ivan', 'Ivanov', 'ivan@gmail.com', '{noop}123456',
        2, 3, '1989-7-7', 'avatar_5.jpg', '2022-11-1T00:00',
        'stell');

--changeset stell:22
SELECT SETVAL('users_id_seq', (SELECT MAX(id) FROM users));

--changeset stell:23
INSERT INTO order_status (id, name, created_at, created_by)
VALUES (1, 'Unconfirmed', '2022-11-1T00:00', 'stell'),
       (2, 'Approved', '2022-11-1T00:00', 'stell'),
       (3, 'Blocked', '2022-11-1T00:00', 'stell');

--changeset stell:24
SELECT SETVAL('order_status_id_seq', (SELECT MAX(id) FROM order_status));

--changeset stell:25
INSERT INTO order_type (id, name, created_at, created_by)
VALUES (1, 'Unconfirmed', '2022-11-1T00:00', 'stell'),
       (2, 'Cash', '2022-11-1T00:00', 'stell'),
       (3, 'Online', '2022-11-1T00:00', 'stell'),
       (4, 'Card', '2022-11-1T00:00', 'stell');

--changeset stell:26
SELECT SETVAL('order_type_id_seq', (SELECT MAX(id) FROM order_type));

--changeset stell:27
INSERT INTO orders (id, user_id, order_status_id, order_type_id, order_date, return_date, created_at, created_by)
VALUES (1, 3, 3, 3, '2022-10-23T10:00', '2022-10-17T10:00',
        '2022-10-23T10:00', 'reader'),
       (2, 2, 1, 2, '2022-10-23T14:35', '2022-11-23T14:35',
        '2022-10-23T14:35', 'librarian'),
       (3, 3, 3, 1, '2022-10-24T14:35', '2022-11-1T15:00',
        '2022-10-24T14:35', 'reader'),
       (4, 4, 2, 1, '2022-10-25T18:00', '2022-10-29T11:45',
        '2022-10-25T18:00', 'serge');

--changeset stell:28
SELECT SETVAL('orders_id_seq', (SELECT MAX(id) FROM orders));

--changeset stell:29
INSERT INTO book (id, title, year, page, isbn_10, isbn_13, book_language_id, book_format_id,
                  book_publishing_house_id, book_series_id, order_id, book_status_id, created_at, created_by)
VALUES (1, 'Twilight', 2008, 544, 0316038377, 9780316038379, 1,
        1, 10, null, 1, 3, '2022-11-1T00:00',
        'stell'),
       (2, 'Alice In Wonderland', 2008, 94, '144042909X', 9781440429095,
        1, 3, 3, null, null, 1,
        '2022-11-1T00:00', 'stell'),
       (3, 'Jaws', 2005, 320, 1400064562, 978140064564, 1, 2,
        13, null, 2, 3, '2022-11-1T00:00',
        'stell'),
       (4, 'Gone with the Wind', 2007, 960, 1416548890, 9781416548898, 1,
        3, 14, null, 1, 3, '2022-11-1T00:00',
        'stell'),
       (5, 'The Lord of the Rings', 2005, 1216, 0618640150, 9780618640157,
        1, 3, 11, null, null, 1,
        '2022-11-1T00:00', 'stell'),
       (6, 'Code Da Vinci', 2004, 574, 2709624931, 9782709624930, 3,
        3, 9, null, null, 1, '2022-11-1T00:00',
        'stell'),
       (7, 'Duma Key', 2001, 1104, '073949015X', 9780739490150, 1,
        2, 14, null, null, 1, '2022-11-1T00:00',
        'stell'),
       (8, 'Re-imagine!', 2004, 352, 3831005796, 9783831005796, 2,
        2, 6, null, null, 1, '2022-11-1T00:00',
        'stell'),
       (9, 'Funky Business', 2002, 288, 0273659073, 9780273659075, 1,
        3, 7, null, null, 1, '2022-11-1T00:00',
        'stell'),
       (10, 'iPhone: The Missing Manual', 2008, 376, 0596521677, 9780596521677,
        1, 3, 12, null, null, 1,
        '2022-11-1T00:00', 'stell'),
       (11, 'Star Wars: The Complete Visual Dictionary', 2006, 272, 0756622387,
        9780756622381, 1, 2, 5, null, null,
        1, '2022-11-1T00:00', 'stell'),
       (12, 'Harry Potter and the Order of the Phoenix', 2003, 896, '043935806X',
        9780439358064, null, 2, 2, null, null,
        1, '2022-11-1T00:00', 'stell'),
       (13, 'The Alchemist', 2006, 192, 0060887966, 9780060887964, 1,
        2, 8, null, null, 1, '2022-11-1T00:00',
        'stell'),
       (14, 'The Hitchhiker''s Guide to the Galaxy', 2004, 224, 1400052920,
        9781400052929, 1, 2, 4, null, null,
        1, '2022-11-1T00:00', 'stell'),
       (15, 'Beginning PHP 5 and MySQL 5', 2006, 900, 1590595521, 9781590595527,
        4, 3, 1, 1, null, 1,
        '2022-11-1T00:00', 'stell'),
       (16, 'Mężczyźni, którzy nienawidzą object', 2008, 633, 8375540595, 9788375540598,
        42, null, null, 2, null, 1,
        '2022-11-1T00:00', 'stell'),
       (17, 'Dziewczyna, która igrała z ogniem', 2009, 699, 8375540900, 9788375540901,
        42, null, null, 2, null, 1,
        '2022-11-1T00:00', 'stell');

--changeset stell:30
SELECT SETVAL('book_id_seq', (SELECT MAX(id) FROM book));

--changeset stell:31
INSERT INTO books_authors (id, book_id, author_id, created_at, created_by)
VALUES (1, (SELECT b.id FROM book b WHERE b.id = 1), (SELECT a.id FROM author a WHERE a.id = 11),
        '2022-11-1T00:00', 'stell'),
       (2, (SELECT b.id FROM book b WHERE b.id = 2), (SELECT a.id FROM author a WHERE a.id = 5),
        '2022-11-1T00:00', 'stell'),
       (3, (SELECT b.id FROM book b WHERE b.id = 3), (SELECT a.id FROM author a WHERE a.id = 3),
        '2022-11-1T00:00', 'stell'),
       (4, (SELECT b.id FROM book b WHERE b.id = 4), (SELECT a.id FROM author a WHERE a.id = 12),
        '2022-11-1T00:00', 'stell'),
       (5, (SELECT b.id FROM book b WHERE b.id = 5), (SELECT a.id FROM author a WHERE a.id = 19),
        '2022-11-1T00:00', 'stell'),
       (6, (SELECT b.id FROM book b WHERE b.id = 6), (SELECT a.id FROM author a WHERE a.id = 4),
        '2022-11-1T00:00', 'stell'),
       (7, (SELECT b.id FROM book b WHERE b.id = 7), (SELECT a.id FROM author a WHERE a.id = 9),
        '2022-11-1T00:00', 'stell'),
       (8, (SELECT b.id FROM book b WHERE b.id = 8), (SELECT a.id FROM author a WHERE a.id = 14),
        '2022-11-1T00:00', 'stell'),
       (9, (SELECT b.id FROM book b WHERE b.id = 9), (SELECT a.id FROM author a WHERE a.id = 17),
        '2022-11-1T00:00', 'stell'),
       (10, (SELECT b.id FROM book b WHERE b.id = 9), (SELECT a.id FROM author a WHERE a.id = 13),
        '2022-11-1T00:00', 'stell'),
       (11, (SELECT b.id FROM book b WHERE b.id = 10), (SELECT a.id FROM author a WHERE a.id = 15),
        '2022-11-1T00:00', 'stell'),
       (12, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT a.id FROM author a WHERE a.id = 16),
        '2022-11-1T00:00', 'stell'),
       (13, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT a.id FROM author a WHERE a.id = 10),
        '2022-11-1T00:00', 'stell'),
       (14, (SELECT b.id FROM book b WHERE b.id = 12), (SELECT a.id FROM author a WHERE a.id = 18),
        '2022-11-1T00:00', 'stell'),
       (15, (SELECT b.id FROM book b WHERE b.id = 13), (SELECT a.id FROM author a WHERE a.id = 6),
        '2022-11-1T00:00', 'stell'),
       (16, (SELECT b.id FROM book b WHERE b.id = 14), (SELECT a.id FROM author a WHERE a.id = 2),
        '2022-11-1T00:00', 'stell'),
       (17, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT a.id FROM author a WHERE a.id = 8),
        '2022-11-1T00:00', 'stell'),
       (18, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT a.id FROM author a WHERE a.id = 7),
        '2022-11-1T00:00', 'stell'),
       (19, (SELECT b.id FROM book b WHERE b.id = 16), (SELECT a.id FROM author a WHERE a.id = 1),
        '2022-11-1T00:00', 'stell'),
       (20, (SELECT b.id FROM book b WHERE b.id = 17), (SELECT a.id FROM author a WHERE a.id = 1),
        '2022-11-1T00:00', 'stell');

--changeset stell:32
SELECT SETVAL('books_authors_id_seq', (SELECT MAX(id) FROM books_authors));

--changeset stell:33
INSERT INTO book_additional (id, book_id, volume, serial_no, price, link, created_at, created_by)
VALUES (1, 1, null, null, 7.99, null, '2022-11-1T00:00', 'stell'),
       (2, 2, null, null, 6.95, null, '2022-11-1T00:00', 'stell'),
       (3, 3, null, null, 20.00, null, '2022-11-1T00:00',
        'stell'),
       (4, 4, null, null, 18.00, null, '2022-11-1T00:00',
        'stell'),
       (5, 5, null, null, 20.00, null, '2022-11-1T00:00',
        'stell'),
       (6, 6, null, null, 35.00, null, '2022-11-1T00:00',
        'stell'),
       (7, 7, null, null, 28.00, null, '2022-11-1T00:00',
        'stell'),
       (8, 8, null, null, 20.00, null, '2022-11-1T00:00',
        'stell'),
       (9, 9, null, null, 28.50, null, '2022-11-1T00:00',
        'stell'),
       (10, 10, null, null, 24.99, null, '2022-11-1T00:00',
        'stell'),
       (11, 11, null, null, 40.00, null, '2022-11-1T00:00',
        'stell'),
       (12, 12, null, null, 29.99, null, '2022-11-1T00:00',
        'stell'),
       (13, 13, null, null, 30.00, null, '2022-11-1T00:00',
        'stell'),
       (14, 14, null, null, 15.00, null, '2022-11-1T00:00',
        'stell'),
       (15, 15, null, null, 44.99, null, '2022-11-1T00:00',
        'stell');

--changeset stell:34
SELECT SETVAL('book_additional_id_seq', (SELECT MAX(id) FROM book_additional));

--changeset stell:35
INSERT INTO books_genres (id, book_id, genre_id, created_at, created_by)
VALUES (1, (SELECT b.id FROM book b WHERE b.id = 1), (SELECT g.id FROM book_genre g WHERE g.id = 8),
        '2022-11-1T00:00', 'stell'),
       (2, (SELECT b.id FROM book b WHERE b.id = 2), (SELECT g.id FROM book_genre g WHERE g.id = 2),
        '2022-11-1T00:00', 'stell'),
       (3, (SELECT b.id FROM book b WHERE b.id = 2), (SELECT g.id FROM book_genre g WHERE g.id = 3),
        '2022-11-1T00:00', 'stell'),
       (4, (SELECT b.id FROM book b WHERE b.id = 3), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (5, (SELECT b.id FROM book b WHERE b.id = 3), (SELECT g.id FROM book_genre g WHERE g.id = 7),
        '2022-11-1T00:00', 'stell'),
       (6, (SELECT b.id FROM book b WHERE b.id = 4), (SELECT g.id FROM book_genre g WHERE g.id = 3),
        '2022-11-1T00:00', 'stell'),
       (7, (SELECT b.id FROM book b WHERE b.id = 4), (SELECT g.id FROM book_genre g WHERE g.id = 8),
        '2022-11-1T00:00', 'stell'),
       (8, (SELECT b.id FROM book b WHERE b.id = 17), (SELECT g.id FROM book_genre g WHERE g.id = 10),
        '2022-11-1T00:00', 'stell'),
       (9, (SELECT b.id FROM book b WHERE b.id = 16), (SELECT g.id FROM book_genre g WHERE g.id = 11),
        '2022-11-1T00:00', 'stell'),
       (10, (SELECT b.id FROM book b WHERE b.id = 5), (SELECT g.id FROM book_genre g WHERE g.id = 3),
        '2022-11-1T00:00', 'stell'),
       (11, (SELECT b.id FROM book b WHERE b.id = 5), (SELECT g.id FROM book_genre g WHERE g.id = 4),
        '2022-11-1T00:00', 'stell'),
       (12, (SELECT b.id FROM book b WHERE b.id = 6), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (13, (SELECT b.id FROM book b WHERE b.id = 7), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (14, (SELECT b.id FROM book b WHERE b.id = 7), (SELECT g.id FROM book_genre g WHERE g.id = 7),
        '2022-11-1T00:00', 'stell'),
       (15, (SELECT b.id FROM book b WHERE b.id = 8), (SELECT g.id FROM book_genre g WHERE g.id = 1),
        '2022-11-1T00:00', 'stell'),
       (16, (SELECT b.id FROM book b WHERE b.id = 9), (SELECT g.id FROM book_genre g WHERE g.id = 1),
        '2022-11-1T00:00', 'stell'),
       (17, (SELECT b.id FROM book b WHERE b.id = 10), (SELECT g.id FROM book_genre g WHERE g.id = 5),
        '2022-11-1T00:00', 'stell'),
       (18, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT g.id FROM book_genre g WHERE g.id = 4),
        '2022-11-1T00:00', 'stell'),
       (19, (SELECT b.id FROM book b WHERE b.id = 11), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (20, (SELECT b.id FROM book b WHERE b.id = 12), (SELECT g.id FROM book_genre g WHERE g.id = 2),
        '2022-11-1T00:00', 'stell'),
       (21, (SELECT b.id FROM book b WHERE b.id = 12), (SELECT g.id FROM book_genre g WHERE g.id = 4),
        '2022-11-1T00:00', 'stell'),
       (22, (SELECT b.id FROM book b WHERE b.id = 13), (SELECT g.id FROM book_genre g WHERE g.id = 3),
        '2022-11-1T00:00', 'stell'),
       (23, (SELECT b.id FROM book b WHERE b.id = 13), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (24, (SELECT b.id FROM book b WHERE b.id = 14), (SELECT g.id FROM book_genre g WHERE g.id = 6),
        '2022-11-1T00:00', 'stell'),
       (25, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT g.id FROM book_genre g WHERE g.id = 5),
        '2022-11-1T00:00', 'stell'),
       (26, (SELECT b.id FROM book b WHERE b.id = 15), (SELECT g.id FROM book_genre g WHERE g.id = 9),
        '2022-11-1T00:00', 'stell');

--changeset stell:36
SELECT SETVAL('books_genres_id_seq', (SELECT MAX(id) FROM books_genres));