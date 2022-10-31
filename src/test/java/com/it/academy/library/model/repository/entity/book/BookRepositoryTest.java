package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.author.AuthorFilterMapper;
import com.it.academy.library.mapper.filter.book.BookAdditionalFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFormatFilterMapper;
import com.it.academy.library.mapper.filter.book.BookGenreFilterMapper;
import com.it.academy.library.mapper.filter.book.BookLanguageFilterMapper;
import com.it.academy.library.mapper.filter.book.BookPublishingHouseFilterMapper;
import com.it.academy.library.mapper.filter.book.BookSeriesFilterMapper;
import com.it.academy.library.mapper.filter.book.BookStatusFilterMapper;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book repository test.")
class BookRepositoryTest extends IntegrationTestBase {
    private static final String BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK = "Mass Market Paperback";
    private static final BigDecimal BOOK_ADDITIONAL_PRICE_20 = BigDecimal.valueOf(20);
    private static final String BOOK_LANGUAGE_NAME_POLISH = "Polish";
    private static final Long BOOK_ID_4 = 4L;
    private static final Long BOOK_ID_6 = 6L;
    private static final String BOOK_SERIES_MILLENNIUM = "Millennium";

    private final BookRepository bookRepository;
    private final BookAdditionalFilterMapper bookAdditionalFilterMapper;
    private final AuthorFilterMapper authorFilterMapper;
    private final BookFormatFilterMapper bookFormatFilterMapper;
    private final BookGenreFilterMapper bookGenreFilterMapper;
    private final BookLanguageFilterMapper bookLanguageFilterMapper;
    private final BookPublishingHouseFilterMapper bookPublishingHouseFilterMapper;
    private final BookFilterMapper bookFilterMapper;
    private final BookSeriesFilterMapper bookSeriesFilterMapper;
    private final BookStatusFilterMapper bookStatusFilterMapper;
    private final OrderFilterMapper orderFilterMapper;
    private final UserFilterMapper userFilterMapper;

    @Test
    @DisplayName("Save book.")
    void saveBook() {
        var expectedCount = bookRepository.count() + 1;

        var actual = bookRepository.save(
                Book.builder()
                        .title(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .subtitle(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .year(ConstantUtil.BOOK_YEAR_2023)
                        .page(ConstantUtil.BOOK_PAGE_200)
                        .isbn10(ConstantUtil.ISBN_10)
                        .isbn13(ConstantUtil.ISBN_13)
                        .imagePath(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .bookStatus(
                                BookStatus.builder()
                                        .id(ConstantUtil.BOOK_STATUS_ID_1)
                                        .build()
                        )
                        .bookLanguage(
                                BookLanguage.builder()
                                        .id(ConstantUtil.BOOK_LANGUAGE_ID_13)
                                        .build()
                        )
                        .bookFormat(
                                BookFormat.builder()
                                        .id(ConstantUtil.BOOK_FORMAT_ID_4)
                                        .build()
                        )
                        .bookPublishingHouse(
                                BookPublishingHouse.builder()
                                        .id(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1)
                                        .build()
                        )
                        .bookSeries(
                                BookSeries.builder()
                                        .id(ConstantUtil.BOOK_SERIES_ID_3)
                                        .build()
                        )
                        .order(
                                Order.builder()
                                        .id(ConstantUtil.ORDER_ID_4)
                                        .build()
                        )
                        .build()
        );
        var actualCount = bookRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getTitle()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getSubtitle()),
                () -> assertEquals(ConstantUtil.BOOK_YEAR_2023, actual.getYear()),
                () -> assertEquals(ConstantUtil.BOOK_PAGE_200, actual.getPage()),
                () -> assertEquals(ConstantUtil.ISBN_10, actual.getIsbn10()),
                () -> assertEquals(ConstantUtil.ISBN_13, actual.getIsbn13()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getImagePath()),
                () -> assertEquals(ConstantUtil.BOOK_STATUS_ID_1, actual.getBookStatus().getId()),
                () -> assertEquals(ConstantUtil.BOOK_LANGUAGE_ID_13, actual.getBookLanguage().getId()),
                () -> assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, actual.getBookFormat().getId()),
                () -> assertEquals(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1, actual.getBookPublishingHouse().getId()),
                () -> assertEquals(ConstantUtil.BOOK_SERIES_ID_3, actual.getBookSeries().getId()),
                () -> assertEquals(ConstantUtil.ORDER_ID_4, actual.getOrder().getId())
        );
    }

    @Test
    @DisplayName("Update book.")
    void updateBook() {
        var book = bookRepository.findById(BOOK_ID_4);

        book.ifPresent(it -> {
            it.setTitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setSubtitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setYear(ConstantUtil.BOOK_YEAR_2023);
            it.setPage(ConstantUtil.BOOK_PAGE_200);
            it.setIsbn10(ConstantUtil.ISBN_10);
            it.setIsbn13(ConstantUtil.ISBN_13);
            it.setImagePath(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setImagePath(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setBookStatus(
                    BookStatus.builder()
                            .id(ConstantUtil.BOOK_STATUS_ID_1)
                            .build()
            );
            it.setBookLanguage(
                    BookLanguage.builder()
                            .id(ConstantUtil.BOOK_LANGUAGE_ID_13)
                            .build()
            );
            it.setBookFormat(
                    BookFormat.builder()
                            .id(ConstantUtil.BOOK_FORMAT_ID_4)
                            .build()
            );
            it.setBookPublishingHouse(
                    BookPublishingHouse.builder()
                            .id(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1)
                            .build()
            );
            it.setBookSeries(
                    BookSeries.builder()
                            .id(ConstantUtil.BOOK_SERIES_ID_3)
                            .build()
            );
            it.setOrder(
                    Order.builder()
                            .id(ConstantUtil.ORDER_ID_4)
                            .build()
            );
            bookRepository.save(it);
        });
        var actual = bookRepository.findById(BOOK_ID_4);

        actual.ifPresent(it -> {
            assertEquals(BOOK_ID_4, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getTitle());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getSubtitle());
            assertEquals(ConstantUtil.BOOK_YEAR_2023, it.getYear());
            assertEquals(ConstantUtil.BOOK_PAGE_200, it.getPage());
            assertEquals(ConstantUtil.ISBN_10, it.getIsbn10());
            assertEquals(ConstantUtil.ISBN_13, it.getIsbn13());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getImagePath());
            assertEquals(ConstantUtil.BOOK_STATUS_ID_1, it.getBookStatus().getId());
            assertEquals(ConstantUtil.BOOK_LANGUAGE_ID_13, it.getBookLanguage().getId());
            assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, it.getBookFormat().getId());
            assertEquals(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1, it.getBookPublishingHouse().getId());
            assertEquals(ConstantUtil.BOOK_SERIES_ID_3, it.getBookSeries().getId());
            assertEquals(ConstantUtil.ORDER_ID_4, it.getOrder().getId());
        });
    }

    @Test
    @DisplayName("Delete book.")
    void deleteBook() {
        var expected = bookRepository.count() - 1;

        bookRepository.deleteById(BOOK_ID_6);
        var actual = bookRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all book by book language filter.")
    void findAllBookByBooksLanguageFilter() {
        var actual = bookRepository.findAllByBookLanguageFilter(bookLanguageFilterMapper.map(
                BookLanguage.builder()
                        .name(BOOK_LANGUAGE_NAME_POLISH)
                        .build())
        );

        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("Find all book by book format filter.")
    void findAllBookByBookFormatFilter() {
        var actual = bookRepository.findAllByBookFormatFilter(bookFormatFilterMapper.map(
                BookFormat.builder()
                        .name(BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by book publishing house filter.")
    void findAllBookByBookPublishingHouseFilter() {
        var actual = bookRepository.findAllByBookPublishingHouseFilter(
                bookPublishingHouseFilterMapper.map(
                        BookPublishingHouse.builder()
                                .name(ConstantUtil.BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS)
                                .build()
                )
        );

        assertThat(actual).hasSize(3);
    }

    @Test
    @DisplayName("Find all book by book status filter.")
    void findAllBookByBookStatusFilter() {
        var actual = bookRepository.findAllByBookStatusFilter(bookStatusFilterMapper.map(
                BookStatus.builder()
                        .name(ConstantUtil.BOOK_STATUS_NAME_READING_ROOM)
                        .build())
        );

        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("Find all book by book series filter.")
    void findAllBookByBookSeriesFilter() {
        var actual = bookRepository.findAllByBookSeriesFilter(bookSeriesFilterMapper.map(
                BookSeries.builder()
                        .name(BOOK_SERIES_MILLENNIUM)
                        .build())
        );

        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("Find all book by order filter.")
    void findAllBookByOrderFilter() {
        var actual = bookRepository.findAllByOrderFilter(orderFilterMapper.map(
                Order.builder()
                        .orderDate(ConstantUtil.ORDER_DATE_10)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by user filter.")
    void findAllBookByUserFilter() {
        var actual = bookRepository.findAllByUserFilter(userFilterMapper.map(
                User.builder()
                        .firstName(ConstantUtil.USER_FIRST_NAME_SVETA)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by book genre filter.")
    void findAllBookByGenreFilter() {
        var actual = bookRepository.findAllByBookGenreFilter(bookGenreFilterMapper.map(
                BookGenre.builder()
                        .name(ConstantUtil.BOOK_GENRE_FRAGMENT_NAME_SS)
                        .build())
        );

        assertThat(actual).hasSize(6);
    }

    @Test
    @DisplayName("Find all book by book additional.")
    void findAllBookByBookAdditionalFilter() {
        var actual = bookRepository.findAllByBookAdditionalFilter(bookAdditionalFilterMapper.map(
                BookAdditional.builder()
                        .price(BOOK_ADDITIONAL_PRICE_20)
                        .build())
        );

        assertThat(actual).hasSize(3);
    }

    @Test
    @DisplayName("Find all book by author filter.")
    void findAllBookByAuthorFilter() {
        var actual = bookRepository.findAllByAuthorFilter(authorFilterMapper.map(
                Author.builder()
                        .firstName("Stephenie")
                        .build())
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by book filter.")
    void findAllBookByBookFilter() {
        var actual = bookRepository.findAllByBookFilter(bookFilterMapper.map(
                Book.builder()
                        .title(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP)
                        .build())
        );

        assertThat(actual).hasSize(1);
    }
}