package com.it.academy.library.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class ConstantUtil {
    public static final String AUTHOR_ROLE_NAME_AUTHOR = "Author";
    public final static Integer AUTHOR_ROLE_ID_1 = 1;
    public static final Integer BOOK_STATUS_ID_1 = 1;
    public static final String BOOK_STATUS_NAME_READING_ROOM = "Reading room";
    public static final Integer BOOK_SERIES_ID_3 = 3;
    public static final Integer BOOK_PUBLISHING_HOUSE_ID_1 = 1;
    public static final Integer BOOK_PUBLISHING_HOURS_ID_15 = 15;
    public static final String BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS = "Books";
    public static final Integer BOOK_FORMAT_ID_4 = 4;
    public static final String BOOK_GENRE_FRAGMENT_NAME_SS = "ss";
    public static final Integer BOOK_LANGUAGE_ID_10 = 10;
    public static final Integer BOOK_LANGUAGE_ID_13 = 13;
    public static final String BOOK_TITLE_FRAGMENT_PHP = "PHP";
    public static final Integer BOOK_YEAR_2023 = 2023;
    public static final Short BOOK_PAGE_200 = 200;
    public static final Long ORDER_ID_4 = 4L;
    public static final LocalDateTime ORDER_DATE_10 =
            LocalDateTime.of(2022, 10, 23, 14, 35);
    public static final String ORDER_TYPE_NAME_UNCONFIRMED = "Unconfirmed";
    public static final Long USER_ID_5 = 5L;
    public static final LocalDate BIRTHDAY = LocalDate.of(2000, 1, 10);
    public static final String USER_FIRST_NAME_SVETA = "Sveta";
    public static final String USER_ROLE_NAME_READER = "Reader";
    public static final String USER_STATUS_NAME_GUEST = "Guest";
    public static final String ISBN_13 = "978-5699383719";
    public static final String ISBN_10 = "5699383719";
    public static final String NEW = "NEW ";
    public static final String UPDATE = "UPDATE";
    public static final String SAVE = "SAVE";
}
