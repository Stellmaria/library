package com.it.academy.library.dto.create.book;

import com.it.academy.library.model.entity.book.BookStatus;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookStatus} entity.
 */
@Value
public class BookStatusCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String name;
}
