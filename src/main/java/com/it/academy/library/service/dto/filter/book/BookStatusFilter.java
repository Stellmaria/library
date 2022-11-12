package com.it.academy.library.service.dto.filter.book;

import com.it.academy.library.model.entity.book.BookStatus;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookStatus.bookStatus;

/**
 * A DTO for the {@link BookStatus} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookStatusFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull BookStatusFilter bookStatusFilter) {
        return QueryPredicates.builder()
                .add(bookStatusFilter.getId(), bookStatus.id::eq)
                .add(bookStatusFilter.getName(), bookStatus.name::containsIgnoreCase)
                .build();
    }
}
