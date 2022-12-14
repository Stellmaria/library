package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.read.AuthorReadDto;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import lombok.AllArgsConstructor;
import lombok.Value;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * A DTO for the {@link Book} entity.
 */
@Value
@AllArgsConstructor
public class BookReadDto {
    Long id;

    String title;

    String subtitle;

    Integer year;

    Long quantity;

    String isbn10;

    String isbn13;

    String image;

    BookStatusReadDto bookStatus;

    BookLanguageReadDto bookLanguage;

    BookFormatReadDto bookFormat;

    BookPublishingHouseReadDto bookPublishingHouse;

    BookSeriesReadDto bookSeries;

    OrderReadDto order;

    Collection<AuthorReadDto> authors;

    Collection<BookGenreReadDto> genres;

    LocalDateTime createAt;

    String createBy;
}
