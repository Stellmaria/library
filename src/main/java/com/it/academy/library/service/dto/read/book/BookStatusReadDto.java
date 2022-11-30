package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link BookStatus} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookStatusReadDto {
    private Integer id;

    private String name;
}
