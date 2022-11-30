package com.it.academy.library.service.dto.filter.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
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
public class BookPublishingHouseFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull BookPublishingHouseFilter bookPublishingHouseFilter) {
        return QueryPredicates.builder()
                .add(bookPublishingHouseFilter.getId(), bookPublishingHouse.id::eq)
                .add(bookPublishingHouseFilter.getName(), bookPublishingHouse.name::containsIgnoreCase)
                .build();
    }
}
