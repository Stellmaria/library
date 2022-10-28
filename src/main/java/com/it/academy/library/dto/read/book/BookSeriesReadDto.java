package com.it.academy.library.dto.read.book;

import com.it.academy.library.model.entity.book.BookSeries;
import lombok.Value;

/**
 * A DTO for the {@link BookSeries} entity.
 */
@Value
public class BookSeriesReadDto {
    Integer id;

    String name;
}
