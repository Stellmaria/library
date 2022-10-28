package com.it.academy.library.dto.read.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import lombok.Value;

/**
 * A DTO for the {@link BookLanguage} entity.
 */
@Value
public class BookLanguageReadDto {
    Integer id;

    String name;
}
