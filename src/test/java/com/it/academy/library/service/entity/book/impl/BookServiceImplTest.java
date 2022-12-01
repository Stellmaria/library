package com.it.academy.library.service.entity.book.impl;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.read.book.BookSeriesReadMapper;
import com.it.academy.library.mapper.read.book.BookStatusReadMapper;
import com.it.academy.library.service.dto.create.book.BookCreateEditDto;
import com.it.academy.library.service.dto.filter.book.BookFilter;
import com.it.academy.library.service.dto.read.book.BookFormatReadDto;
import com.it.academy.library.service.dto.read.book.BookPublishingHouseReadDto;
import com.it.academy.library.service.dto.read.book.BookReadDto;
import com.it.academy.library.service.entity.book.BookService;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.data.domain.Pageable;
import org.springframework.mock.web.MockMultipartFile;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RequiredArgsConstructor
@DisplayName("Book service test.")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class BookServiceImplTest extends IntegrationTestBase {
    private final BookService bookService;

    private final BookStatusReadMapper bookStatusReadMapper;
    private final BookSeriesReadMapper bookSeriesReadMapper;

    @Test
    @DisplayName("Save book.")
    void create() {
        var book = BookCreateEditDto.builder()
                .title(ConstantUtil.NEW + ConstantUtil.SAVE)
                .subtitle(ConstantUtil.NEW + ConstantUtil.SAVE)
                .year(ConstantUtil.BOOK_YEAR_2023)
                .quantity(ConstantUtil.BOOK_QUANTITY_4)
                .isbn10(ConstantUtil.BOOK_ISBN_10)
                .isbn13(ConstantUtil.BOOK_ISBN_13)
                .image(new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0]))
                .bookLanguageId(ConstantUtil.BOOK_LANGUAGE_ID_10)
                .bookStatusId(ConstantUtil.BOOK_STATUS_ID_1)
                .bookFormatId(ConstantUtil.BOOK_FORMAT_ID_4)
                .bookPublishingHouseId(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1)
                .bookSeriesId(ConstantUtil.BOOK_SERIES_ID_3)
                .orderId(ConstantUtil.ORDER_ID_2)
                .authorsId(List.of(ConstantUtil.AUTHOR_ID_2, ConstantUtil.AUTHOR_ID_8, ConstantUtil.AUTHOR_ID_9))
                .genresId(List.of(ConstantUtil.USER_ROLE_ID_1, ConstantUtil.BOOK_GENRE_ID_2))
                .build();

        var actual = bookService.create(book);

        assertAll(
                () -> assertEquals(book.getTitle(), actual.getTitle(), "Book titles must match."),
                () -> assertEquals(book.getSubtitle(), actual.getSubtitle(), "Book subtitle must match."),
                () -> assertEquals(book.getYear(), actual.getYear(), "The year of the books must coincide."),
                () -> assertEquals(book.getIsbn10(), actual.getIsbn10()),
                () -> assertEquals(book.getIsbn13(), actual.getIsbn13()),
                () -> assertEquals(ConstantUtil.BOOK_COVER_IMAGE_NAME_0, actual.getImage()),
                () -> assertEquals(book.getBookStatusId(), actual.getBookStatus().getId()),
                () -> assertEquals(book.getBookLanguageId(), actual.getBookLanguage().getId()),
                () -> assertEquals(book.getBookFormatId(), actual.getBookFormat().getId()),
                () -> assertEquals(book.getBookPublishingHouseId(), actual.getBookPublishingHouse().getId()),
                () -> assertEquals(book.getBookSeriesId(), actual.getBookSeries().getId()),
                () -> assertEquals(book.getBookStatusId(), actual.getBookStatus().getId()),
                () -> assertEquals(book.getOrderId(), actual.getOrder().getId(), "Book order IDs must match."),
                () -> assertEquals(book.getAuthorsId().size(), actual.getAuthors().size()),
                () -> assertEquals(book.getGenresId().size(), actual.getGenres().size())
        );
    }

    @Test
    @DisplayName("Find book by id.")
    void findById() {
        var expected = new BookReadDto(
                ConstantUtil.BOOK_ID_21,
                ConstantUtil.BOOK_TITLE_DARK_TOWER_I,
                null,
                ConstantUtil.BOOK_YEAR_2016,
                ConstantUtil.BOOK_QUANTITY_4,
                ConstantUtil.BOOK_DARK_TOWN_ISBN_10,
                ConstantUtil.BOOK_DARK_TOWN_ISBN_13,
                ConstantUtil.BOOK_DARK_TOWN_COVER,
                bookStatusReadMapper.map(ConstantUtil.getBookStatus()),
                ConstantUtil.getBookLanguage(1),
                null,
                null,
                bookSeriesReadMapper.map(ConstantUtil.getBookSeries()),
                null,
                List.of(ConstantUtil.getAuthor()),
                List.of(ConstantUtil.getBookGenre()),
                ConstantUtil.CREATE_AT,
                ConstantUtil.ADMIN_NAME_STELL
        );

        var actual = bookService.findById(expected.getId());

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(expected.getTitle(), entity.getTitle(), "Book titles must match."),
                        () -> assertEquals(expected.getSubtitle(), entity.getSubtitle()),
                        () -> assertEquals(expected.getYear(), entity.getYear()),
                        () -> assertEquals(expected.getIsbn10(), entity.getIsbn10()),
                        () -> assertEquals(expected.getIsbn13(), entity.getIsbn13()),
                        () -> assertEquals(expected.getImage(), entity.getImage()),
                        () -> assertEquals(expected.getBookStatus().getId(), entity.getBookStatus().getId()),
                        () -> assertEquals(expected.getBookLanguage().getId(), entity.getBookLanguage().getId()),
                        () -> assertNull(entity.getBookFormat()),
                        () -> assertNull(expected.getBookPublishingHouse()),
                        () -> assertEquals(expected.getBookSeries().getId(), entity.getBookSeries().getId()),
                        () -> assertEquals(expected.getBookStatus().getId(), entity.getBookStatus().getId()),
                        () -> assertNull(entity.getOrder()),
                        () -> assertEquals(expected.getAuthors().size(), entity.getAuthors().size()),
                        () -> assertEquals(expected.getGenres().size(), entity.getGenres().size())
                )
        );
    }

    @Test
    @DisplayName("Find all book with filter.")
    void findAllWithFilter() {
        var expected = 1;
        var filter = new BookFilter();
        filter.setTitle(ConstantUtil.BOOK_TITLE_TWILIGHT);

        var actual = bookService.findAll(filter, Pageable.ofSize(ConstantUtil.PAGE_SIZE));

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book series id.")
    void findAllBySeriesId() {
        var expected = 1;

        var actual = bookService.findAllBySeriesId(ConstantUtil.BOOK_SERIES_ID_3);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by order id.")
    void findAllByOrderId() {
        var expected = 2;

        var actual = bookService.findAllByOrderId(ConstantUtil.BOOK_ID_1);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book publishing house id.")
    void findAllByBookPublishingHouseId() {
        var expected = 1;

        var actual = bookService.findAllByBookPublishingHouseId(
                ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_13
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book genre id.")
    void findAllByBookGenreId() {
        var expected = 2;

        var actual = bookService.findAllByBookGenreId(ConstantUtil.BOOK_GENRE_ID_1);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by author id.")
    void findAllByAuthorId() {
        var expected = 1;

        var actual = bookService.findAllByAuthorId(ConstantUtil.AUTHOR_ID_15);

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Update book.")
    void update() {
        var publishingHouse = new BookPublishingHouseReadDto(
                ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1, ConstantUtil.BOOK_PUBLISHING_HOUSE_NAME_APRESS
        );
        var bookFormat = new BookFormatReadDto(ConstantUtil.BOOK_FORMAT_ID_4, ConstantUtil.BOOK_FORMAT_NAME_EBOOK);
        var book = BookCreateEditDto.builder()
                .title(ConstantUtil.NEW + ConstantUtil.UPDATE)
                .subtitle(ConstantUtil.NEW + ConstantUtil.UPDATE)
                .year(ConstantUtil.BOOK_YEAR_2023)
                .quantity(ConstantUtil.BOOK_QUANTITY_4)
                .isbn10(ConstantUtil.BOOK_ISBN_10)
                .isbn13(ConstantUtil.BOOK_ISBN_13)
                .image(new MockMultipartFile(ConstantUtil.NAME_IMAGE_TEST, new byte[0]))
                .bookLanguageId(ConstantUtil.BOOK_LANGUAGE_ID_10)
                .bookStatusId(ConstantUtil.BOOK_STATUS_ID_1)
                .bookFormatId(bookFormat.getId())
                .bookPublishingHouseId(publishingHouse.getId())
                .bookSeriesId(ConstantUtil.BOOK_SERIES_ID_3)
                .orderId(ConstantUtil.ORDER_ID_2)
                .authorsId(List.of(ConstantUtil.AUTHOR_ID_2, ConstantUtil.AUTHOR_ID_8, ConstantUtil.AUTHOR_ID_15))
                .genresId(List.of(ConstantUtil.BOOK_GENRE_ID_11))
                .build();

        var actual = bookService.update(ConstantUtil.BOOK_ID_18, book);

        actual.ifPresent(entity ->
                assertAll(
                        () -> assertEquals(book.getTitle(), entity.getTitle(), "Book titles must match."),
                        () -> assertEquals(book.getSubtitle(), entity.getSubtitle()),
                        () -> assertEquals(book.getYear(), entity.getYear(), "The years must coincide."),
                        () -> assertEquals(book.getIsbn10(), entity.getIsbn10()),
                        () -> assertEquals(book.getIsbn13(), entity.getIsbn13()),
                        () -> assertEquals(ConstantUtil.BOOK_COVER_18, entity.getImage()),
                        () -> assertEquals(book.getBookStatusId(), entity.getBookStatus().getId()),
                        () -> assertEquals(book.getBookLanguageId(), entity.getBookLanguage().getId()),
                        () -> assertEquals(bookFormat, entity.getBookFormat(), "Book formats must match."),
                        () -> assertEquals(publishingHouse, entity.getBookPublishingHouse()),
                        () -> assertEquals(book.getBookSeriesId(), entity.getBookSeries().getId()),
                        () -> assertEquals(book.getBookStatusId(), entity.getBookStatus().getId()),
                        () -> assertEquals(ConstantUtil.ORDER_ID_2, entity.getOrder().getId()),
                        () -> assertEquals(book.getAuthorsId().size(), entity.getAuthors().size()),
                        () -> assertEquals(book.getGenresId().size(), entity.getGenres().size())
                )
        );
    }

    @Test
    @DisplayName("Delete book.")
    void delete() {
        assertAll(
                () -> assertTrue(bookService.delete(ConstantUtil.BOOK_ID_17)),
                () -> assertFalse(bookService.delete(ConstantUtil.BOOK_ID_99))
        );
    }

    @Test
    @DisplayName("Find book image.")
    void findImage() {
        var expected = 32241;

        var actual = bookService.findImage(ConstantUtil.BOOK_ID_1);

        actual.ifPresent(entity -> assertEquals(expected, entity.length));
    }
}
