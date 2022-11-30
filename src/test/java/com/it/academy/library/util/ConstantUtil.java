package com.it.academy.library.util;

import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.order.OrderStatus;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import com.it.academy.library.service.dto.read.book.BookGenreReadDto;
import com.it.academy.library.service.dto.read.book.BookLanguageReadDto;
import com.it.academy.library.service.dto.read.book.BookSeriesReadDto;
import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;

@UtilityClass
public class ConstantUtil {
    public static final Integer BOOK_STATUS_ID_1 = 1;
    public static final Integer BOOK_STATUS_ID_3 = 3;
    public static final String BOOK_STATUS_NAME_READING_ROOM = "Reading room";

    public static final Integer BOOK_SERIES_ID_1 = 1;
    public static final Integer BOOK_SERIES_ID_2 = 2;
    public static final Integer BOOK_SERIES_ID_3 = 3;
    public static final Integer BOOK_SERIES_ID_5 = 5;
    public static final Integer BOOK_SERIES_ID_99 = 99;
    public static final String BOOK_SERIES_NAME_DARK_TOWN = "Dark town";
    public static final String BOOK_SERIES_NAME_MILLENNIUM = "Millennium";

    public static final Integer BOOK_PUBLISHING_HOUSE_ID_1 = 1;
    public static final Integer BOOK_PUBLISHING_HOUSE_ID_10 = 10;
    public static final Integer BOOK_PUBLISHING_HOUSE_ID_12 = 12;
    public static final Integer BOOK_PUBLISHING_HOUSE_ID_13 = 13;
    public static final Integer BOOK_PUBLISHING_HOURS_ID_15 = 15;
    public static final Integer BOOK_PUBLISHING_HOUSE_ID_99 = 99;
    public static final String BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS = "Books";
    public static final String BOOK_PUBLISHING_HOUSE_NAME_POGUE_PRESS = "Pogue Press";
    public static final String BOOK_PUBLISHING_HOUSE_NAME_APRESS = "Apress";
    public static final String BOOK_PUBLISHING_HOUSE_NAME_CROWN = "Crown";

    public static final Integer BOOK_FORMAT_ID_4 = 4;
    public static final Integer BOOK_FORMAT_ID_9 = 9;
    public static final String BOOK_FORMAT_NAME_EBOOK = "Ebook";
    public static final String BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK = "Mass Market Paperback";
    public static final String BOOK_FORMAT_FRAGMENT_NAME_VER = "ver";

    public static final Integer BOOK_GENRE_ID_1 = 1;
    public static final Integer BOOK_GENRE_ID_2 = 2;
    public static final Integer BOOK_GENRE_ID_4 = 4;
    public static final Integer BOOK_GENRE_ID_8 = 8;
    public static final Integer BOOK_GENRE_ID_11 = 11;
    public static final String BOOK_GENRE_NAME_CORRUPTION = "Corruption";
    public static final String BOOK_GENRE_NAME_ROMANCE = "Romance";
    public static final String BOOK_GENRE_FRAGMENT_NAME_SS = "ss";

    public static final int BOOK_GENRE_ID_6 = 6;
    public static final Integer BOOK_GENRE_ID_7 = 7;
    public static final Integer BOOK_GENRE_ID_10 = 10;
    public static final Integer BOOK_GENRE_ID_99 = 99;

    public static final Integer BOOK_LANGUAGE_ID_10 = 10;
    public static final Integer BOOK_LANGUAGE_ID_13 = 13;
    public static final String BOOK_LANGUAGE_FRAGMENT_NAME_IAN = "ian";
    public static final String BOOK_LANGUAGE_NAME_POLISH = "Polish";

    public static final Long BOOK_ID_1 = 1L;
    public static final Long BOOK_ID_4 = 4L;
    public static final Long BOOK_ID_6 = 6L;
    public static final Long BOOK_ID_8 = 8L;
    public static final Long BOOK_ID_17 = 17L;
    public static final Long BOOK_ID_18 = 18L;
    public static final Long BOOK_ID_21 = 22L;
    public static final Long BOOK_ID_99 = 99L;
    public static final String BOOK_TITLE_DARK_TOWER_I = "Dark Tower I";
    public static final String BOOK_TITLE_TWILIGHT = "Twilight";
    public static final String BOOK_TITLE_FRAGMENT_PHP = "PHP";
    public static final Integer BOOK_YEAR_2016 = 2016;
    public static final Integer BOOK_YEAR_2023 = 2023;
    public static final String BOOK_DARK_TOWN_ISBN_10 = "1501161806";
    public static final String BOOK_ISBN_10 = "5699383719";
    public static final String BOOK_DARK_TOWN_ISBN_13 = "9781501161803";
    public static final String BOOK_ISBN_13 = "9785699383719";
    public static final Long BOOK_QUANTITY_4 = 4L;
    public static final String BOOK_COVER_18 = "cover_18.jpg";
    public static final String BOOK_DARK_TOWN_COVER = "cover_22.jpeg";
    public static final String BOOK_COVER_IMAGE_NAME_0 = "cover_0.jpg";

    public static final Long ORDER_ID_2 = 2L;
    public static final Long ORDER_ID_3 = 3L;
    public static final Long ORDER_ID_4 = 4L;
    public static final LocalDateTime ORDER_DATE_2022_10_11_T_14_35 = LocalDateTime.of(
            2022, 10, 11, 14, 35
    );
    public static final LocalDateTime ORDER_DATE_2022_10_23_T_14_35 = LocalDateTime.of(
            2022, 10, 23, 14, 35
    );
    public static final LocalDateTime ORDER_DATE_2022_10_25_T_18_00 = LocalDateTime.of(
            2022, 10, 25, 18, 0
    );
    public static final LocalDateTime ORDER_RETURN_DATE_2022_10_21_T_18_05 = LocalDateTime.of(
            2022, 10, 21, 18, 5
    );

    public static final Integer ORDER_STATUS_ID_1 = 1;
    public static final Integer ORDER_STATUS_ID_2 = 2;
    public static final Integer ORDER_STATUS_ID_3 = 3;
    public static final String ORDER_STATUS_NAME_UNCONFIRMED = "Unconfirmed";
    public static final String ORDER_STATUS_NAME_APPROVED = "Approved";

    public static final Long USER_ID_3 = 3L;
    public static final Long USER_ID_4 = 4L;
    public static final Long USER_ID_5 = 5L;
    public static final Long USER_ID_99 = 99L;
    public static final LocalDate USER_BIRTHDAY = LocalDate.of(2000, 1, 10);
    public static final String USER_FIRST_NAME_SVETA = "Sveta";
    public static final String USER_FIRST_NAME_SERGEY = "Sergey";
    public static final String USER_EMAIL_EMAIL_EXAMPLE_COM = "email@example.com";
    public static final String USER_EMAIL_TEST_GMAIL_COM = "test@gmail.com";

    public static final Integer USER_ROLE_ID_1 = 1;
    public static final Integer USER_ROLE_ID_2 = 2;
    public static final Integer USER_ROLE_ID_4 = 4;
    public static final String USER_ROLE_NAME_READER = "Reader";

    public static final Integer USER_STATUS_ID_1 = 1;
    public static final Integer USER_STATUS_ID_2 = 2;
    public static final Integer USER_STATUS_ID_3 = 3;
    public static final Integer USER_STATUS_ID_4 = 4;
    public static final String USER_STATUS_NAME_GUEST = "Guest";

    public static final String NAME_IMAGE_TEST = "test";

    public static final String ADMIN_NAME_STELL = "stell";

    public static final LocalDateTime CREATE_AT = LocalDateTime.of(
            2022, 11, 1, 0, 0, 0
    );

    public static final Long AUTHOR_ID_2 = 2L;
    public static final long AUTHOR_ID_8 = 8L;
    public static final Long AUTHOR_ID_9 = 9L;
    public static final Long AUTHOR_ID_15 = 15L;
    public static final Long AUTHOR_ID_19 = 19L;
    public static final Long AUTHOR_ID_99 = 99L;
    public static final LocalDate AUTHOR_BIRTHDAY = LocalDate.of(2000, 1, 1);
    public static final LocalDate AUTHOR_DATE_DEATH = LocalDate.of(3000, 1, 1);
    public static final String AUTHOR_FIRST_NAME_STIEG = "Stieg";
    public static final String AUTHOR_FIRST_NAME_JAMES = "James";
    public static final String AUTHOR_IMAGE_AVATAR_1 = "avatar_1.jpg";
    public static final String AUTHOR_FIRST_NAME_STEPHEN = "Stephen";
    public static final String AUTHOR_LAST_NAME_KING = "King";
    public static final String AUTHOR_IMAGE_9 = "author_9.jpeg";
    public static final LocalDate AUTHOR_STEPHEN_KING_BIRTHDAY = LocalDate.of(1947, 9, 21);
    public static final String AUTHOR_LAST_NAME_PETERS = "Peters";

    public static final Integer PAGE_SIZE = 20;

    public static final String NEW = "NEW ";
    public static final String UPDATE = "UPDATE";
    public static final String SAVE = "SAVE";


    public static BookStatus getBookStatus() {
        return BookStatus.builder()
                .id(BOOK_STATUS_ID_1)
                .build();
    }

    public static BookLanguage getBookLanguage() {
        return BookLanguage.builder()
                .id(BOOK_LANGUAGE_ID_13)
                .build();
    }

    public static BookFormat getBookFormat() {
        return BookFormat.builder()
                .id(BOOK_FORMAT_ID_4)
                .build();
    }

    public static BookPublishingHouse getBookPublishingHouse() {
        return BookPublishingHouse.builder()
                .id(BOOK_PUBLISHING_HOUSE_ID_1)
                .build();
    }

    public static BookSeries getBookSeries() {
        return BookSeries.builder()
                .id(BOOK_SERIES_ID_3)
                .build();
    }

    public static Order getOrder() {
        return Order.builder()
                .id(ORDER_ID_4)
                .build();
    }

    public static OrderStatus getOrderStatus() {
        return OrderStatus.builder()
                .id(ORDER_STATUS_ID_1)
                .build();
    }

    public static User getUser() {
        return User.builder()
                .id(USER_ID_5)
                .build();
    }

    public static UserStatus getUserStatus() {
        return UserStatus.builder()
                .id(USER_STATUS_ID_2)
                .build();
    }

    public static UserRole getUserRole() {
        return UserRole.builder()
                .id(USER_ROLE_ID_2)
                .build();
    }

    public static BookSeriesReadDto getBookSeriesDarkTown() {
        return BookSeriesReadDto.builder()
                .name(BOOK_SERIES_NAME_DARK_TOWN)
                .id(BOOK_SERIES_ID_3)
                .build();
    }

    public static BookGenreReadDto getBookGenre() {
        return BookGenreReadDto.builder()
                .id(BOOK_GENRE_ID_6)
                .build();
    }

    public static AuthorReadDto getAuthor() {
        return AuthorReadDto.builder()
                .id(ConstantUtil.AUTHOR_ID_9)
                .build();
    }

    public static BookLanguageReadDto getBookLanguage(Integer id) {
        return BookLanguageReadDto.builder()
                .id(id)
                .build();
    }
}
