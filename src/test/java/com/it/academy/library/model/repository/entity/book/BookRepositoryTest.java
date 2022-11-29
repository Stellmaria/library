package com.it.academy.library.model.repository.entity.book;

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
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book repository test.")
class BookRepositoryTest extends IntegrationTestBase {
    private static final String BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK = "Mass Market Paperback";
    private static final String BOOK_LANGUAGE_NAME_POLISH = "Polish";
    private static final Long BOOK_ID_4 = 4L;
    private static final Long BOOK_ID_6 = 6L;
    private static final String BOOK_SERIES_MILLENNIUM = "Millennium";
    private static final String FIRST_NAME_STIEG = "Stieg";
    private static final long QUANTITY_4 = 4L;

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

    @Test
    @DisplayName("Save book.")
    void saveBook() {
        var expectedCount = bookRepository.count() + 1;
        var bookStatus = BookStatus.builder()
                .id(ConstantUtil.BOOK_STATUS_ID_1)
                .build();
        var bookLanguage = BookLanguage.builder()
                .id(ConstantUtil.BOOK_LANGUAGE_ID_13)
                .build();
        var bookFormat = BookFormat.builder()
                .id(ConstantUtil.BOOK_FORMAT_ID_4)
                .build();
        var bookPublishingHouse = BookPublishingHouse.builder()
                .id(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1)
                .build();
        var bookSeries = BookSeries.builder()
                .id(ConstantUtil.BOOK_SERIES_ID_3)
                .build();
        var order = Order.builder()
                .id(ConstantUtil.ORDER_ID_4)
                .build();
        var book = Book.builder()
                .title(ConstantUtil.NEW + ConstantUtil.SAVE)
                .subtitle(ConstantUtil.NEW + ConstantUtil.SAVE)
                .year(ConstantUtil.BOOK_YEAR_2023)
                .isbn10(ConstantUtil.ISBN_10)
                .isbn13(ConstantUtil.ISBN_13)
                .image(ConstantUtil.NEW + ConstantUtil.SAVE)
                .bookStatus(bookStatus)
                .bookLanguage(bookLanguage)
                .bookFormat(bookFormat)
                .bookPublishingHouse(bookPublishingHouse)
                .bookSeries(bookSeries)
                .quantity(QUANTITY_4)
                .order(order)
                .build();

        var actual = bookRepository.save(book);
        var actualCount = bookRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getTitle()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getSubtitle()),
                () -> assertEquals(ConstantUtil.BOOK_YEAR_2023, actual.getYear()),
                () -> assertEquals(ConstantUtil.ISBN_10, actual.getIsbn10()),
                () -> assertEquals(ConstantUtil.ISBN_13, actual.getIsbn13()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getImage()),
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
        var bookStatus = BookStatus.builder()
                .id(ConstantUtil.BOOK_STATUS_ID_1)
                .build();
        var bookLanguage = BookLanguage.builder()
                .id(ConstantUtil.BOOK_LANGUAGE_ID_13)
                .build();
        var bookFormat = BookFormat.builder()
                .id(ConstantUtil.BOOK_FORMAT_ID_4)
                .build();
        var bookPublishingHouse = BookPublishingHouse.builder()
                .id(ConstantUtil.BOOK_PUBLISHING_HOUSE_ID_1)
                .build();
        var bookSeries = BookSeries.builder()
                .id(ConstantUtil.BOOK_SERIES_ID_3)
                .build();
        var order = Order.builder()
                .id(ConstantUtil.ORDER_ID_4)
                .build();
        var book = bookRepository.findById(BOOK_ID_4);

        book.ifPresent(it -> {
            it.setTitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setSubtitle(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setYear(ConstantUtil.BOOK_YEAR_2023);
            it.setIsbn10(ConstantUtil.ISBN_10);
            it.setIsbn13(ConstantUtil.ISBN_13);
            it.setImage(ConstantUtil.NEW + ConstantUtil.UPDATE);
            it.setBookStatus(bookStatus);
            it.setBookLanguage(bookLanguage);
            it.setBookFormat(bookFormat);
            it.setBookPublishingHouse(bookPublishingHouse);
            it.setBookSeries(bookSeries);
            it.setOrder(order);
            bookRepository.save(it);
        });
        var actual = bookRepository.findById(BOOK_ID_4);

        actual.ifPresent(it -> {
            assertEquals(BOOK_ID_4, it.getId());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getTitle());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getSubtitle());
            assertEquals(ConstantUtil.BOOK_YEAR_2023, it.getYear());
            assertEquals(ConstantUtil.ISBN_10, it.getIsbn10());
            assertEquals(ConstantUtil.ISBN_13, it.getIsbn13());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getImage());
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
        var bookLanguage = new BookLanguage();
        bookLanguage.setName(BOOK_LANGUAGE_NAME_POLISH);

        var actual = bookRepository.findAllByBookLanguageFilter(
                bookLanguageFilterMapper.map(bookLanguage)
        );

        assertThat(actual).hasSize(2);
    }

    @Test
    @DisplayName("Find all book by book format filter.")
    void findAllBookByBookFormatFilter() {
        var bookFormat = new BookFormat();
        bookFormat.setName(BOOK_FORMAT_NAME_MASS_MARKET_PAPERBACK);

        var actual = bookRepository.findAllByBookFormatFilter(
                bookFormatFilterMapper.map(bookFormat)
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by book publishing house filter.")
    void findAllBookByBookPublishingHouseFilter() {
        var bookPublishingHouse = new BookPublishingHouse();
        bookPublishingHouse.setName(ConstantUtil.BOOK_PUBLISHING_HOUSE_FRAGMENT_NAME_BOOKS);

        var actual = bookRepository.findAllByBookPublishingHouseFilter(
                bookPublishingHouseFilterMapper.map(bookPublishingHouse)
        );

        assertThat(actual).hasSize(3);
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
        var bookSeries = new BookSeries();
        bookSeries.setName(BOOK_SERIES_MILLENNIUM);

        var actual = bookRepository.findAllByBookSeriesFilter(
                bookSeriesFilterMapper.map(bookSeries)
        );

        assertThat(actual).hasSize(4);
    }

    @Test
    @DisplayName("Find all book by order filter.")
    void findAllBookByOrderFilter() {
        var order = new Order();
        order.setOrderDate(ConstantUtil.ORDER_DATE_10);

        var actual = bookRepository.findAllByOrderFilter(
                orderFilterMapper.map(order)
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by user filter.")
    void findAllBookByUserFilter() {
        var user = new User();
        user.setFirstName(ConstantUtil.USER_FIRST_NAME_SVETA);

        var actual = bookRepository.findAllByUserFilter(
                userFilterMapper.map(user)
        );

        assertThat(actual).hasSize(1);
    }

    @Test
    @DisplayName("Find all book by book genre filter.")
    void findAllBookByGenreFilter() {
        var bookGenre = new BookGenre();
        bookGenre.setName(ConstantUtil.BOOK_GENRE_FRAGMENT_NAME_SS);

        var actual = bookRepository.findAllByBookGenreFilter(
                bookGenreFilterMapper.map(bookGenre)
        );

        assertThat(actual).hasSize(6);
    }

    @Test
    @DisplayName("Find all book by author filter.")
    void findAllBookByAuthorFilter() {
        var author = new Author();
        author.setFirstName(FIRST_NAME_STIEG);

        var actual = bookRepository.findAllByAuthorFilter(
                authorFilterMapper.map(author)
        );

        assertThat(actual).hasSize(4);
    }

    @Test
    @DisplayName("Find all book by book filter.")
    void findAllBookByBookFilter() {
        var book = new Book();
        book.setTitle(ConstantUtil.BOOK_TITLE_FRAGMENT_PHP);

        var actual = bookRepository.findAllByBookFilter(
                bookFilterMapper.map(book)
        );

        assertThat(actual).hasSize(1);
    }
}
