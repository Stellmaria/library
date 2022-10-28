package com.it.academy.library.dto.read.book;

import com.it.academy.library.model.entity.book.BookGenre;
import lombok.Value;

/**
 * A DTO for the {@link BookGenre} entity.
 */
@Value
public class BookGenreReadDto {
    Integer id;

    String name;

    String description;
}
