package com.it.academy.library.service.dto.read.book;

import com.it.academy.library.model.entity.book.Book;
import com.it.academy.library.service.dto.read.author.AuthorReadDto;
import com.it.academy.library.service.dto.read.order.OrderReadDto;
import lombok.Value;

import java.util.Collection;

/**
 * A DTO for the {@link Book} entity.
 */
@Value
public class BookReadDto {
    Long id;

    String title;

    String subtitle;

    Integer year;

    Short pages;

    String isbn10;

    String isbn13;

    String image;

    BookStatusReadDto bookStatus;

    BookLanguageReadDto bookLanguage;

    BookFormatReadDto bookFormat;

    BookPublishingHouseReadDto bookPublishingHouse;

    BookSeriesReadDto bookSeries;

    OrderReadDto order;

    Collection<AuthorReadDto> booksAuthors;
}
