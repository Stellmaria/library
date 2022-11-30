package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookGenre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link BookGenre} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookGenreReadDto {
    private Integer id;

    private String name;
}
