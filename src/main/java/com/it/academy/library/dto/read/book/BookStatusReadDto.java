package com.it.academy.library.dto.read.book;

import com.it.academy.library.model.entity.book.BookStatus;
import lombok.Value;

/**
 * A DTO for the {@link BookStatus} entity.
 */
@Value
public class BookStatusReadDto {
    Integer id;

    String name;
}
