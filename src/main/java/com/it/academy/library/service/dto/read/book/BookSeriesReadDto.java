package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.model.entity.book.BookSeries;
import lombok.Value;

import java.util.Collection;

/**
 * A DTO for the {@link BookSeries} entity.
 */
@Value
public class BookSeriesReadDto {
    Integer id;

    String name;

    Collection<Book> books;
}
