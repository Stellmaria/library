package com.it.academy.library.service.dto.filter.book;

import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookFormat.bookFormat;

/**
 * A DTO for the {@link BookFormat} entity.
 */
@Getter
@AllArgsConstructor
public class BookFormatFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull BookFormatFilter bookFormatFilter) {
        return QueryPredicates.builder()
                .add(bookFormatFilter.getId(), bookFormat.id::eq)
                .add(bookFormatFilter.getName(), bookFormat.name::containsIgnoreCase)
                .build();
    }
}
