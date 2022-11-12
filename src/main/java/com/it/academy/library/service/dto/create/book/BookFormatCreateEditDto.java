package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.BookFormat;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookFormat} entity.
 */
@Value
public class BookFormatCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String name;
}
