package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * A DTO for the {@link BookStatus} entity.
 */
@Data
@AllArgsConstructor
public class BookStatusReadDto {
    private Integer id;

    private String name;
}
