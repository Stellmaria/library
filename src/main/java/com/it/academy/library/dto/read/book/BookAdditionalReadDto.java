package com.it.academy.library.dto.read.book;

import com.it.academy.library.model.entity.book.BookAdditional;
import lombok.Value;

import java.math.BigDecimal;

/**
 * A DTO for the {@link BookAdditional} entity.
 */
@Value
public class BookAdditionalReadDto {
    Long id;

    BookReadDto book;

    Short volume;

    Integer serialNo;

    BigDecimal price;

    String link;
}
