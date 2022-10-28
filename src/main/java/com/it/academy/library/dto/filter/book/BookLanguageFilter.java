package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookLanguage.bookLanguage;

/**
 * A DTO for the {@link BookLanguage} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookLanguageFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull BookLanguageFilter bookLanguageFilter) {
        return QueryPredicates.builder()
                .add(bookLanguageFilter.getName(), bookLanguage.name::containsIgnoreCase)
                .build();
    }
}
