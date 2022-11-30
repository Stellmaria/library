package com.it.academy.library.service.dto.filter.book;

import com.it.academy.library.model.entity.book.BookGenre;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
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
public class BookGenreFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull BookGenreFilter bookGenreFilter) {
        return QueryPredicates.builder()
                .add(bookGenreFilter.getId(), bookGenre.id::eq)
                .add(bookGenreFilter.getName(), bookGenre.name::containsIgnoreCase)
                .build();
    }
}
