package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBook.book;

/**
 * A DTO for the {@link Book} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFilter {
    private String title;

    private String subtitle;

    private Integer year;

    private Short page;

    private String isbn10;

    private String isbn13;

    private Integer bookStatusId;

    private Integer bookLanguageId;

    private Integer bookFormatId;

    private Integer bookPublishingHouseId;

    private Integer bookSeriesId;

    private Long orderId;

    public static Predicate queryPredicates(@NotNull BookFilter bookFilter) {
        return QueryPredicates.builder()
                .add(bookFilter.getTitle(), book.title::containsIgnoreCase)
                .add(bookFilter.getSubtitle(), book.subtitle::containsIgnoreCase)
                .add(bookFilter.getYear(), book.year::eq)
                .add(bookFilter.getPage(), book.page::eq)
                .add(bookFilter.getIsbn10(), book.isbn10::containsIgnoreCase)
                .add(bookFilter.getIsbn13(), book.isbn13::containsIgnoreCase)
                .add(bookFilter.getBookStatusId(), book.bookStatus.id::eq)
                .add(bookFilter.getBookLanguageId(), book.bookLanguage.id::eq)
                .add(bookFilter.getBookPublishingHouseId(), book.bookPublishingHouse.id::eq)
                .add(bookFilter.getBookSeriesId(), book.bookSeries.id::eq)
                .add(bookFilter.getOrderId(), book.order.id::eq)
                .build();
    }
}
