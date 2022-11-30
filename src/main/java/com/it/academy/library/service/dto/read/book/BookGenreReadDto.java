package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO for the {@link BookGenre} entity.
 */
@Data
@AllArgsConstructor
@Builder
public class BookGenreReadDto {
    private Integer id;

    private String name;
}
