package com.it.academy.library.dto.filter.book;

import com.it.academy.library.dto.filter.order.OrderFilter;
import com.it.academy.library.dto.filter.order.OrderStatusFilter;
import com.it.academy.library.dto.filter.order.OrderTypeFilter;
import com.it.academy.library.dto.filter.user.UserFilter;
import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import static com.it.academy.library.model.entity.book.QBook.book;

/**
 * A DTO for the {@link Book} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilter {
    private Long id;

    private String title;

    private String subtitle;

    private Integer year;

    private Short page;

    private String isbn10;

    private String isbn13;

    private BookStatusFilter bookStatus;

    private BookLanguageFilter bookLanguage;

    private BookFormatFilter bookFormat;

    private BookPublishingHouseFilter bookPublishingHouse;

    private BookSeriesFilter bookSeries;

    private OrderFilter order;

    public static Predicate queryPredicates(@NotNull BookFilter bookFilter) {
        return QueryPredicates.builder()
                .add(bookFilter.getId(), book.id::eq)
                .add(bookFilter.getTitle(), book.title::containsIgnoreCase)
                .add(bookFilter.getSubtitle(), book.subtitle::containsIgnoreCase)
                .add(bookFilter.getYear(), book.year::eq)
                .add(bookFilter.getPage(), book.page::eq)
                .add(bookFilter.getIsbn10(), book.isbn10::containsIgnoreCase)
                .add(bookFilter.getIsbn13(), book.isbn13::containsIgnoreCase)
                .add(getBookStatusId(bookFilter), book.bookStatus.id::eq)
                .add(getBookStatusName(bookFilter), book.bookStatus.name::containsIgnoreCase)
                .add(getBookLanguageId(bookFilter), book.bookLanguage.id::eq)
                .add(getBookLanguageName(bookFilter), book.bookLanguage.name::containsIgnoreCase)
                .add(getBookFormatId(bookFilter), book.bookFormat.id::eq)
                .add(getBookFormatName(bookFilter), book.bookFormat.name::containsIgnoreCase)
                .add(getBookPublishingHouseId(bookFilter), book.bookPublishingHouse.id::eq)
                .add(getBookPublishingHouseName(bookFilter), book.bookPublishingHouse.name::containsIgnoreCase)
                .add(getBookSeriesId(bookFilter), book.bookSeries.id::eq)
                .add(getBookSeriesName(bookFilter), book.bookSeries.name::containsIgnoreCase)
                .add(getOrderId(bookFilter), book.order.id::eq)
                .add(getUserId(bookFilter), book.order.user.id::eq)
                .add(getUsername(bookFilter), book.order.user.username::containsIgnoreCase)
                .add(getUserFirstName(bookFilter), book.order.user.firstName::containsIgnoreCase)
                .add(getUserLastName(bookFilter), book.order.user.lastName::containsIgnoreCase)
                .add(getUserPassword(bookFilter), book.order.user.password::containsIgnoreCase)
                .add(getBirthday(bookFilter), book.order.user.birthday::eq)
                .add(getOrderStatusId(bookFilter), book.order.orderStatus.id::eq)
                .add(getOrderStatusName(bookFilter), book.order.orderStatus.name::containsIgnoreCase)
                .add(getOrderTypeId(bookFilter), book.order.orderType.id::eq)
                .add(getOrderTypeName(bookFilter), book.order.orderType.name::containsIgnoreCase)
                .add(getOrderDate(bookFilter), book.order.orderDate::eq)
                .add(getOrderReturnDate(bookFilter), book.order.returnDate::eq)
                .build();
    }

    @Nullable
    private static String getOrderTypeName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getOrderType)
                .map(OrderTypeFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getOrderTypeId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getOrderType)
                .map(OrderTypeFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getOrderStatusName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getOrderStatus)
                .map(OrderStatusFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getOrderStatusId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getOrderStatus)
                .map(OrderStatusFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static LocalDate getBirthday(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getBirthday)
                .orElse(null);
    }

    @Nullable
    private static String getUserPassword(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getPassword)
                .orElse(null);
    }

    @Nullable
    private static String getUserLastName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getLastName)
                .orElse(null);
    }

    @Nullable
    private static String getUserFirstName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getFirstName)
                .orElse(null);
    }

    @Nullable
    private static String getUsername(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getUsername)
                .orElse(null);
    }

    @Nullable
    private static Long getUserId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getUser)
                .map(UserFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static LocalDateTime getOrderReturnDate(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getReturnDate)
                .orElse(null);
    }

    @Nullable
    private static LocalDateTime getOrderDate(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getOrderDate)
                .orElse(null);
    }

    @Nullable
    private static Long getOrderId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getOrder())
                .map(OrderFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookSeriesName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookSeries())
                .map(BookSeriesFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookSeriesId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookSeries())
                .map(BookSeriesFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookPublishingHouseName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookPublishingHouse())
                .map(BookPublishingHouseFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookPublishingHouseId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookPublishingHouse())
                .map(BookPublishingHouseFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookFormatName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookFormat())
                .map(BookFormatFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookFormatId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookFormat())
                .map(BookFormatFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookLanguageName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookLanguage())
                .map(BookLanguageFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookLanguageId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookLanguage())
                .map(BookLanguageFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getBookStatusName(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookStatus())
                .map(BookStatusFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getBookStatusId(@NotNull BookFilter bookFilter) {
        return Optional.ofNullable(bookFilter.getBookStatus())
                .map(BookStatusFilter::getId)
                .orElse(null);
    }
}
