package com.it.academy.library.dto.create.book;

import com.it.academy.library.model.entity.book.BookAdditional;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

/**
 * A DTO for the {@link BookAdditional} entity.
 */
@Value
public class BookAdditionalCreateEditDto {
    Long bookId;

    @Positive
    Short volume;

    @Positive
    Integer serialNo;

    @Positive
    BigDecimal price;

    @NotBlank
    String link;
}
