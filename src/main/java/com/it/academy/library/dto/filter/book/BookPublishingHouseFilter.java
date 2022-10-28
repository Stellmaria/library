package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.book.QBookPublishingHouse.bookPublishingHouse;

/**
 * A DTO for the {@link BookPublishingHouse} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookPublishingHouseFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull BookPublishingHouseFilter bookPublishingHouseFilter) {
        return QueryPredicates.builder()
                .add(bookPublishingHouseFilter.getName(), bookPublishingHouse.name::containsIgnoreCase)
                .build();
    }
}
