package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.BookFormat;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookFormat.bookFormat;

/**
 * A DTO for the {@link BookFormat} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookFormatFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull BookFormatFilter bookFormatFilter) {
        return QueryPredicates.builder()
                .add(bookFormatFilter.getName(), bookFormat.name::containsIgnoreCase)
                .build();
    }
}
