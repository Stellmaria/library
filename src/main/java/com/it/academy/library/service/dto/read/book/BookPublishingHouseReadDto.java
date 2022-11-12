package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookPublishingHouse;
import lombok.Value;

/**
 * A DTO for the {@link BookPublishingHouse} entity.
 */
@Value
public class BookPublishingHouseReadDto {
    Integer id;

    String name;
}
