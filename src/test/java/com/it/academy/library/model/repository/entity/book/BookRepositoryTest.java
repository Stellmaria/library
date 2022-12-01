package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.IntegrationTestBase;
import com.it.academy.library.mapper.filter.AuthorFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFilterMapper;
import com.it.academy.library.mapper.filter.book.BookFormatFilterMapper;
import com.it.academy.library.mapper.filter.book.BookGenreFilterMapper;
import com.it.academy.library.mapper.filter.book.BookLanguageFilterMapper;
import com.it.academy.library.mapper.filter.book.BookPublishingHouseFilterMapper;
import com.it.academy.library.mapper.filter.book.BookSeriesFilterMapper;
import com.it.academy.library.mapper.filter.book.BookStatusFilterMapper;
import com.it.academy.library.mapper.filter.order.OrderFilterMapper;
import com.it.academy.library.mapper.filter.user.UserFilterMapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static com.it.academy.library.util.ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book repository test.")
class BookRepositoryTest extends IntegrationTestBase {
    private final BookRepository bookRepository;

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

    @Nested
    @DisplayName("CRUD methods.")
    class CRUD {
        @Test
        @DisplayName("Save book.")
        void saveBook() {
            var expectedCount = bookRepository.count() + 1;
            var book = Book.builder()
                    .title(ConstantUtil.NEW + ConstantUtil.SAVE)
                    .subtitle(ConstantUtil.NEW + ConstantUtil.SAVE)
                    .year(ConstantUtil.BOOK_YEAR_2023)
                    .isbn10(ConstantUtil.BOOK_ISBN_10)
                    .isbn13(ConstantUtil.BOOK_ISBN_13)
                    .image(ConstantUtil.NEW + ConstantUtil.SAVE)
                    .bookStatus(ConstantUtil.getBookStatus())
                    .bookLanguage(ConstantUtil.getBookLanguage())
                    .bookFormat(ConstantUtil.getBookFormat())
                    .bookPublishingHouse(ConstantUtil.getBookPublishingHouse())
                    .bookSeries(ConstantUtil.getBookSeries())
                    .order(ConstantUtil.getOrder())
                    .quantity(ConstantUtil.BOOK_QUANTITY_4)
                    .build();

            var actual = bookRepository.save(book);
            var actualCount = bookRepository.count();

            assertAll(
                    () -> assertEquals(expectedCount, actualCount, "The number of books must match."),
                    () -> assertEquals(book.getTitle(), actual.getTitle(), "Book titles must match."),
                    () -> assertEquals(book.getSubtitle(), actual.getSubtitle(), "Book subtitles must match."),
                    () -> assertEquals(book.getYear(), actual.getYear(), "The year of the books must match."),
                    () -> assertEquals(book.getIsbn10(), actual.getIsbn10()),
                    () -> assertEquals(book.getIsbn13(), actual.getIsbn13()),
                    () -> assertEquals(book.getImage(), actual.getImage(), "Book covers must match."),
                    () -> assertEquals(book.getBookStatus().getId(), actual.getBookStatus().getId()),
                    () -> assertEquals(book.getBookLanguage().getId(), actual.getBookLanguage().getId()),
                    () -> assertEquals(book.getBookFormat().getId(), actual.getBookFormat().getId()),
                    () -> assertEquals(book.getBookPublishingHouse().getId(), actual.getBookPublishingHouse().getId()),
                    () -> assertEquals(book.getBookSeries().getId(), actual.getBookSeries().getId()),
                    () -> assertEquals(book.getOrder().getId(), actual.getOrder().getId())
            );
        }

        @Test
        @DisplayName("Update book.")
        void updateBook() {
            var book = bookRepository.findById(ConstantUtil.BOOK_ID_4);

            book.ifPresent(entity -> {
                entity.setTitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
                entity.setSubtitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
                entity.setYear(ConstantUtil.BOOK_YEAR_2023);
                entity.setIsbn10(ConstantUtil.BOOK_ISBN_10);
                entity.setIsbn13(ConstantUtil.BOOK_ISBN_13);
                entity.setImage(ConstantUtil.NEW + ConstantUtil.UPDATE);
                entity.setBookStatus(ConstantUtil.getBookStatus());
                entity.setBookLanguage(ConstantUtil.getBookLanguage());
                entity.setBookFormat(ConstantUtil.getBookFormat());
                entity.setBookPublishingHouse(ConstantUtil.getBookPublishingHouse());
                entity.setBookSeries(ConstantUtil.getBookSeries());
                entity.setOrder(ConstantUtil.getOrder());

                bookRepository.save(entity);
            });
            var actual = bookRepository.findById(ConstantUtil.BOOK_ID_4);

            actual.ifPresent(entity ->
                    assertAll(
                            () -> assertEquals(ConstantUtil.BOOK_ID_4, entity.getId(), "Book ids must match."),
                            () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getTitle()),
                            () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getSubtitle()),
                            () -> assertEquals(ConstantUtil.BOOK_YEAR_2023, entity.getYear()),
                            () -> assertEquals(ConstantUtil.BOOK_ISBN_10, entity.getIsbn10()),
                            () -> assertEquals(ConstantUtil.BOOK_ISBN_13, entity.getIsbn13()),
                            () -> assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, entity.getImage()),
                            () -> assertEquals(ConstantUtil.BOOK_STATUS_ID_1, entity.getBookStatus().getId()),
                            () -> assertEquals(ConstantUtil.BOOK_LANGUAGE_ID_13, entity.getBookLanguage().getId()),
                            () -> assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, entity.getBookFormat().getId()),
                            () -> assertEquals(BOOK_PUBLISHING_HOUSE_ID_1, entity.getBookPublishingHouse().getId()),
                            () -> assertEquals(ConstantUtil.BOOK_SERIES_ID_3, entity.getBookSeries().getId()),
                            () -> assertEquals(ConstantUtil.ORDER_ID_4, entity.getOrder().getId())
                    )
            );
        }

        @Test
        @DisplayName("Delete book.")
        void deleteBook() {
            var expected = bookRepository.count() - 1;

            bookRepository.deleteById(ConstantUtil.BOOK_ID_6);
            var actual = bookRepository.count();

            assertEquals(expected, actual, "The number of books must match.");
        }
    }

    @Test
    @DisplayName("Find all book by book language filter.")
    void findAllBookByBooksLanguageFilter() {
        var expected = 2;
        var bookLanguage = new BookLanguage();
        bookLanguage.setName(ConstantUtil.BOOK_LANGUAGE_NAME_POLISH);

        var actual = bookRepository.findAllByBookLanguageFilter(
                bookLanguageFilterMapper.map(bookLanguage)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book format filter.")
    void findAllBookByBookFormatFilter() {
        var expected = 1;
        var bookFormat = new BookFormat();
        bookFormat.setName(ConstantUtil.BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK);

        var actual = bookRepository.findAllByBookFormatFilter(
                bookFormatFilterMapper.map(bookFormat)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book publishing house filter.")
    void findAllBookByBookPublishingHouseFilter() {
        var expected = 3;
        var bookPublishingHouse = new BookPublishingHouse();
        bookPublishingHouse.setName(ConstantUtil.BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS);

        var actual = bookRepository.findAllByBookPublishingHouseFilter(
                bookPublishingHouseFilterMapper.map(bookPublishingHouse)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book status filter.")
    void findAllBookByBookStatusFilter() {
        var bookStatus = new BookStatus();
        bookStatus.setName(ConstantUtil.BOOK_STATUS_NAME_READING_ROOM);

        var actual = bookRepository.findAllByBookStatusFilter(
                bookStatusFilterMapper.map(bookStatus)
        );

        assertThat(actual).isEmpty();
    }

    @Test
    @DisplayName("Find all book by book series filter.")
    void findAllBookByBookSeriesFilter() {
        var expected = 4;
        var bookSeries = new BookSeries();
        bookSeries.setName(ConstantUtil.BOOK_SERIES_NAME_MILLENNIUM);

        var actual = bookRepository.findAllByBookSeriesFilter(
                bookSeriesFilterMapper.map(bookSeries)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by order filter.")
    void findAllBookByOrderFilter() {
        var expected = 1;
        var order = new Order();
        order.setOrderDate(ConstantUtil.ORDER_DATE_2022_10_23_T_14_35);

        var actual = bookRepository.findAllByOrderFilter(
                orderFilterMapper.map(order)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by user filter.")
    void findAllBookByUserFilter() {
        var expected = 1;
        var user = new User();
        user.setFirstName(ConstantUtil.USER_FIRST_NAME_SVETA);

        var actual = bookRepository.findAllByUserFilter(
                userFilterMapper.map(user)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book genre filter.")
    void findAllBookByGenreFilter() {
        var expected = 6;
        var bookGenre = new BookGenre();
        bookGenre.setName(ConstantUtil.BOOK_GENRE_FRAGMENT_NAME_SS);

        var actual = bookRepository.findAllByBookGenreFilter(
                bookGenreFilterMapper.map(bookGenre)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by author filter.")
    void findAllBookByAuthorFilter() {
        var expected = 4;
        var author = new Author();
        author.setFirstName(ConstantUtil.AUTHOR_FIRST_NAME_STIEG);

        var actual = bookRepository.findAllByAuthorFilter(
                authorFilterMapper.map(author)
        );

        assertThat(actual).hasSize(expected);
    }

    @Test
    @DisplayName("Find all book by book filter.")
    void findAllBookByBookFilter() {
        var expected = 1;
        var book = new Book();
        book.setTitle(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP);

        var actual = bookRepository.findAllByBookFilter(
                bookFilterMapper.map(book)
        );

        assertThat(actual).hasSize(expected);
    }
}
