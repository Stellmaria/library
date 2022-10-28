package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookGenre.bookGenre;

/**
 * A DTO for the {@link BookGenre} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookGenreFilter {
    private String name;

    private String description;

    public static Predicate queryPredicates(@NotNull BookGenreFilter bookGenreFilter) {
        return QueryPredicates.builder()
                .add(bookGenreFilter.getName(), bookGenre.name::containsIgnoreCase)
                .add(bookGenreFilter.getDescription(), bookGenre.description::containsIgnoreCase)
                .build();
    }
}
