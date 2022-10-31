package com.it.academy.library.model.repository.entity.book;

import com.it.academy.library.mapper.filter.book.BookAdditionalFilterMapper;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.model.entity.order.Order;
import com.it.academy.library.model.repository.entity.IntegrationTestBase;
import com.it.academy.library.util.ConstantUtil;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static com.it.academy.library.util.ConstantUtil.BOOK_PUBLISHING_HOURS_ID_15;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RequiredArgsConstructor
@DisplayName("Book additional repository test.")
class BookAdditionalRepositoryTest extends IntegrationTestBase {
    private static final BigDecimal BOOK_ADDITIONAL_PRICE_40 = BigDecimal.valueOf(40);
    private static final Long ORDER_ID_2 = 2L;
    private static final Short BOOK_ADDITIONAL_VOLUME_1 = 1;
    private static final Integer BOOK_ADDITIONAL_SERIAL_NO_1 = 1;
    private static final Long BOOK_ADDITIONAL_ID_10 = 10L;
    private static final Long BOOK_ADDITIONAL_ID_15 = 15L;
    private static final BigDecimal BOOK_ADDITIONAL_PRICE_10 = BigDecimal.TEN;
    private final BookAdditionalRepository bookAdditionalRepository;

    private final BookRepository bookRepository;
    private final BookAdditionalFilterMapper bookAdditionalFilterMapper;

    @Test
    @DisplayName("Save book additional.")
    void saveBookAdditional() {
        var expectedCount = bookAdditionalRepository.count() + 1;
        var book = Book.builder()
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
                                .id(ConstantUtil.BOOK_LANGUAGE_ID_10)
                                .build()
                )
                .bookFormat(
                        BookFormat.builder()
                                .id(ConstantUtil.BOOK_FORMAT_ID_4)
                                .build()
                )
                .bookPublishingHouse(
                        BookPublishingHouse.builder()
                                .id(BOOK_PUBLISHING_HOURS_ID_15)
                                .build()
                )
                .bookSeries(
                        BookSeries.builder()
                                .id(ConstantUtil.BOOK_SERIES_ID_3)
                                .build()
                )
                .order(
                        Order.builder()
                                .id(ORDER_ID_2)
                                .build()
                )
                .build();
        bookRepository.save(book);

        var actual = bookAdditionalRepository.save(
                BookAdditional.builder()
                        .book(book)
                        .volume(BOOK_ADDITIONAL_VOLUME_1)
                        .serialNo(BOOK_ADDITIONAL_SERIAL_NO_1)
                        .price(BOOK_ADDITIONAL_PRICE_10)
                        .link(ConstantUtil.NEW + ConstantUtil.SAVE)
                        .build()
        );
        var actualCount = bookAdditionalRepository.count();

        assertAll(
                () -> assertEquals(expectedCount, actualCount),
                () -> assertEquals(book, actual.getBook()),
                () -> assertEquals(BOOK_ADDITIONAL_VOLUME_1, actual.getVolume()),
                () -> assertEquals(BOOK_ADDITIONAL_SERIAL_NO_1, actual.getSerialNo()),
                () -> assertEquals(BOOK_ADDITIONAL_PRICE_10, actual.getPrice()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getLink()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getBook().getTitle()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getBook().getSubtitle()),
                () -> assertEquals(ConstantUtil.BOOK_YEAR_2023, actual.getBook().getYear()),
                () -> assertEquals(ConstantUtil.BOOK_PAGE_200, actual.getBook().getPage()),
                () -> assertEquals(ConstantUtil.ISBN_10, actual.getBook().getIsbn10()),
                () -> assertEquals(ConstantUtil.ISBN_13, actual.getBook().getIsbn13()),
                () -> assertEquals(ConstantUtil.NEW + ConstantUtil.SAVE, actual.getBook().getImagePath()),
                () -> assertEquals(ConstantUtil.BOOK_STATUS_ID_1, actual.getBook().getBookStatus().getId()),
                () -> assertEquals(ConstantUtil.BOOK_LANGUAGE_ID_10, actual.getBook().getBookLanguage().getId()),
                () -> assertEquals(ConstantUtil.BOOK_FORMAT_ID_4, actual.getBook().getBookFormat().getId()),
                () -> assertEquals(BOOK_PUBLISHING_HOURS_ID_15, actual.getBook().getBookPublishingHouse().getId()),
                () -> assertEquals(ConstantUtil.BOOK_SERIES_ID_3, actual.getBook().getBookSeries().getId()),
                () -> assertEquals(ORDER_ID_2, actual.getBook().getOrder().getId())
        );
    }

    @Test
    @DisplayName("Update book additional.")
    void updateBookAdditional() {
        var bookAdditional = bookAdditionalRepository.findById(BOOK_ADDITIONAL_ID_10);

        bookAdditional.ifPresent(it -> {
            it.setVolume(BOOK_ADDITIONAL_VOLUME_1);
            it.setSerialNo(BOOK_ADDITIONAL_SERIAL_NO_1);
            it.setPrice(BOOK_ADDITIONAL_PRICE_10);
            it.setLink(ConstantUtil.NEW + ConstantUtil.UPDATE);
            bookAdditionalRepository.save(it);
        });
        var actual = bookAdditionalRepository.findById(BOOK_ADDITIONAL_ID_10);

        actual.ifPresent(it -> {
            assertEquals(BOOK_ADDITIONAL_VOLUME_1, it.getVolume());
            assertEquals(BOOK_ADDITIONAL_SERIAL_NO_1, it.getSerialNo());
            assertEquals(BOOK_ADDITIONAL_PRICE_10, it.getPrice());
            assertEquals(ConstantUtil.NEW + ConstantUtil.UPDATE, it.getLink());
        });
    }

    @Test
    @DisplayName("Delete book additional.")
    void deleteBookAdditional() {
        var expected = bookAdditionalRepository.count() - 1;

        bookAdditionalRepository.deleteById(BOOK_ADDITIONAL_ID_15);
        var actual = bookAdditionalRepository.count();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Find all book additional by book additional filter.")
    void findAllBookAdditionalByBookAdditionalFilter() {
        var actual = bookAdditionalRepository.findAllByBookAdditionalFilter(
                bookAdditionalFilterMapper.map(
                        BookAdditional.builder()
                                .price(BOOK_ADDITIONAL_PRICE_40)
                                .build()
                )
        );

        assertThat(actual).hasSize(1);
    }
}