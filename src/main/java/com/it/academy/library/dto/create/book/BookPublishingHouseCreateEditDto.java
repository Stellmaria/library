package com.it.academy.library.dto.create.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link BookPublishingHouse} entity.
 */
@Value
public class BookPublishingHouseCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String name;
}
