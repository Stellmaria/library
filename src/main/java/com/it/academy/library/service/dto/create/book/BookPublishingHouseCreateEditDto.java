package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookPublishingHouse} entity.
 */
@Value
public class BookPublishingHouseCreateEditDto {
    @NotBlank(message = "The name of the book publishing house must not be empty.")
    @Size(
            min = 1,
            max = 64,
            message = "The name of the book publishing house must not be less than" +
                    " 1 character and not more than 64 characters."
    )
    String name;
}
