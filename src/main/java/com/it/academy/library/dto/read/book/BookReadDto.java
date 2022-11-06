package com.it.academy.library.dto.read.book;

import com.it.academy.library.dto.read.order.OrderReadDto;
import com.it.academy.library.model.entity.book.Book;
import lombok.Value;

/**
 * A DTO for the {@link Book} entity.
 */
@Value
public class BookReadDto {
    Long id;

    String title;

    String subtitle;

    Integer year;

    Short page;

    String isbn10;

    String isbn13;

    String image;

    BookStatusReadDto bookStatus;

    BookLanguageReadDto bookLanguage;

    BookFormatReadDto bookFormat;

    BookPublishingHouseReadDto bookPublishingHouse;

    BookSeriesReadDto bookSeries;

    OrderReadDto order;
}
