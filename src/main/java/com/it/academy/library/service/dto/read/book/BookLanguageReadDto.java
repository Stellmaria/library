package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.BookLanguage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * A DTO for the {@link BookLanguage} entity.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLanguageReadDto {
    private Integer id;

    private String name;
}
