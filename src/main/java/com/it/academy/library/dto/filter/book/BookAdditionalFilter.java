package com.it.academy.library.dto.filter.book;

import com.it.academy.library.dto.filter.order.OrderFilter;
import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.it.academy.library.model.entity.book.QBookAdditional.bookAdditional;

/**
 * A DTO for the {@link BookAdditional} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAdditionalFilter {
    private Long id;

    private BookFilter book;

    private Short volume;

    private Integer serialNo;

    private BigDecimal price;

    private String link;

    public static Predicate queryPredicates(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return QueryPredicates.builder()
                .add(bookAdditionalFilter.getId(), bookAdditional.id::eq)
                .add(getBookId(bookAdditionalFilter), bookAdditional.book.id::eq)
                .add(getBookTitle(bookAdditionalFilter), bookAdditional.book.title::containsIgnoreCase)
                .add(getBookSubtitle(bookAdditionalFilter), bookAdditional.book.subtitle::containsIgnoreCase)
                .add(getBookYear(bookAdditionalFilter), bookAdditional.book.year::eq)
                .add(getBookPage(bookAdditionalFilter), bookAdditional.book.page::eq)
                .add(getBookIsbn10(bookAdditionalFilter), bookAdditional.book.isbn10::containsIgnoreCase)
                .add(getBookIsbn13(bookAdditionalFilter), bookAdditional.book.isbn13::containsIgnoreCase)
                .add(getBookStatusId(bookAdditionalFilter), bookAdditional.book.bookStatus.id::eq)
                .add(getBookStatusName(bookAdditionalFilter), bookAdditional.book.bookStatus.name::containsIgnoreCase)
                .add(getBookLanguageId(bookAdditionalFilter), bookAdditional.book.bookLanguage.id::eq)
                .add(
                        getBookLanguageName(bookAdditionalFilter),
                        bookAdditional.book.bookLanguage.name::containsIgnoreCase
                )
                .add(getBookFormatId(bookAdditionalFilter), bookAdditional.book.bookFormat.id::eq)
                .add(getBookFormatName(bookAdditionalFilter), bookAdditional.book.bookFormat.name::containsIgnoreCase)
                .add(getBookPublishingHouseId(bookAdditionalFilter), bookAdditional.book.bookPublishingHouse.id::eq)
                .add(
                        getBookPublishingHouseName(bookAdditionalFilter),
                        bookAdditional.book.bookPublishingHouse.name::containsIgnoreCase)
                .add(getBookSeriesId(bookAdditionalFilter), bookAdditional.book.bookSeries.id::eq)
                .add(getBookSeriesName(bookAdditionalFilter), bookAdditional.book.bookSeries.name::containsIgnoreCase)
                .add(getOrderId(bookAdditionalFilter), bookAdditional.book.order.id::eq)
                .add(getOrderDate(bookAdditionalFilter), bookAdditional.book.order.orderDate::eq)
                .add(getOrderReturnDate(bookAdditionalFilter), bookAdditional.book.order.returnDate::eq)
                .add(bookAdditionalFilter.getVolume(), bookAdditional.volume::eq)
                .add(bookAdditionalFilter.getSerialNo(), bookAdditional.serialNo::eq)
                .add(bookAdditionalFilter.getPrice(), bookAdditional.price::eq)
                .add(bookAdditionalFilter.getLink(), bookAdditional.link::eq)
                .build();
    }

    @Nullable
    private static LocalDateTime getOrderReturnDate(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getOrder)
                .map(OrderFilter::getReturnDate)
                .orElse(null);
    }

    @Nullable
    private static LocalDateTime getOrderDate(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getOrder)
                .map(OrderFilter::getOrderDate)
                .orElse(null);
    }

    @Nullable
    private static Long getOrderId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getOrder)
                .map(OrderFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookSeriesName(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookSeries)
                .map(BookSeriesFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookSeriesId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookSeries)
                .map(BookSeriesFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookPublishingHouseName(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookPublishingHouse)
                .map(BookPublishingHouseFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookPublishingHouseId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookPublishingHouse)
                .map(BookPublishingHouseFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookFormatName(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookFormat)
                .map(BookFormatFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookFormatId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookFormat)
                .map(BookFormatFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookLanguageName(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookLanguage)
                .map(BookLanguageFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookLanguageId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookLanguage)
                .map(BookLanguageFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookStatusName(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookStatus)
                .map(BookStatusFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookStatusId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getBookStatus)
                .map(BookStatusFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookIsbn13(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getIsbn13)
                .orElse(null);
    }

    @Nullable
    private static String getBookIsbn10(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getIsbn10)
                .orElse(null);
    }

    @Nullable
    private static Short getBookPage(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getPage)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookYear(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getYear)
                .orElse(null);
    }

    @Nullable
    private static String getBookSubtitle(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getSubtitle)
                .orElse(null);
    }

    @Nullable
    private static String getBookTitle(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getTitle)
                .orElse(null);
    }

    @Nullable
    private static Long getBookId(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return Optional.ofNullable(bookAdditionalFilter.getBook())
                .map(BookFilter::getId)
                .orElse(null);
    }
}