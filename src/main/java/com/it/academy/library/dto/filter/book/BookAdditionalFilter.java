package com.it.academy.library.dto.filter.book;

import com.it.academy.library.model.entity.book.BookAdditional;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import static com.it.academy.library.model.entity.book.QBookAdditional.bookAdditional;

/**
 * A DTO for the {@link BookAdditional} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookAdditionalFilter {
    private Long id;

    private Long bookId;

    private Short volume;

    private Integer serialNo;

    private BigDecimal price;

    private String link;

    public static Predicate queryPredicates(@NotNull BookAdditionalFilter bookAdditionalFilter) {
        return QueryPredicates.builder()
                .add(bookAdditionalFilter.getId(), bookAdditional.id::eq)
                .add(bookAdditionalFilter.getBookId(), bookAdditional.book.id::eq)
                .add(bookAdditionalFilter.getVolume(), bookAdditional.volume::eq)
                .add(bookAdditionalFilter.getSerialNo(), bookAdditional.serialNo::eq)
                .add(bookAdditionalFilter.getPrice(), bookAdditional.price::eq)
                .add(bookAdditionalFilter.getLink(), bookAdditional.link::eq)
                .build();
    }
}