package com.it.academy.library.dto.create.book;

import com.it.academy.library.model.entity.book.Book;
import lombok.Value;
import org.hibernate.validator.constraints.ISBN;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * A DTO for the {@link Book} entity.
 */
@Value
public class BookCreateEditDto {
    @NotBlank
    String title;

    String subtitle;

    @Positive
    Integer year;

    @Positive
    Short page;

    @ISBN(type = ISBN.Type.ISBN_10)
    String isbn10;

    @ISBN(type = ISBN.Type.ISBN_13)
    String isbn13;

    @NotBlank
    String imagePath;

    Integer bookStatusId;

    Integer bookLanguageId;

    Integer bookFormatId;

    Integer bookPublishingHouseId;

    Integer bookSeriesId;

    Long orderId;
}
