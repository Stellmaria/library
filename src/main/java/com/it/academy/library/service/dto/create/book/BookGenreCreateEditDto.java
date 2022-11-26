package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.BookGenre;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookGenre} entity.
 */
@Value
public class BookGenreCreateEditDto {
    @NotBlank(message = "The genre name cannot be empty.")
    @Size(
            min = 1,
            max = 64,
            message = "The genre name cannot be less than 1 character and more than 64 characters."
    )
    String name;
}
