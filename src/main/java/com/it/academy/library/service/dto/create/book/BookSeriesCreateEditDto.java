package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.BookSeries;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookSeries} entity.
 */
@Value
public class BookSeriesCreateEditDto {
    @NotBlank(message = "The book series name cannot be empty.")
    @Size(
            min = 1,
            max = 64,
            message = "The name of a book series cannot be less than 1 character and more than 64 characters."
    )
    String name;
}
