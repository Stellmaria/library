package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookLanguage} entity.
 */
@Value
public class BookLanguageCreateEditDto {
    @Size(min = 3, max = 64)
    @NotBlank
    String name;
}
