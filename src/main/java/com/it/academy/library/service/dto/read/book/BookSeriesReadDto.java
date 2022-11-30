package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookSeries;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * A DTO for the {@link BookSeries} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookSeriesReadDto {
    private Integer id;

    private String name;

    private Collection<Book> books;
}
