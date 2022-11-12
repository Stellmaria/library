package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookFormat;
import lombok.Value;

/**
 * A DTO for the {@link BookFormat} entity.
 */
@Value
public class BookFormatReadDto {
    Integer id;

    String name;
}
