package com.it.academy.library.service.dto.filter.book;

import com.it.academy.library.model.entity.book.BookSeries;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookSeries.bookSeries;

/**
 * A DTO for the {@link BookSeries} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookSeriesFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull BookSeriesFilter bookSeriesFilter) {
        return QueryPredicates.builder()
                .add(bookSeriesFilter.getId(), bookSeries.id::eq)
                .add(bookSeriesFilter.getName(), bookSeries.name::containsIgnoreCase)
                .build();
    }
}
