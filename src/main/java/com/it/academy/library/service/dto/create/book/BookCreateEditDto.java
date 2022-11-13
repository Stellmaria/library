package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

/**
 * A DTO for the {@link Book} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateEditDto {
    @NotBlank
    private String title;

    private String subtitle;

    @Positive
    private Integer year;

    @Positive
    private Short pages;

    @ISBN(type = ISBN.Type.ISBN_10)
    private String isbn10;

    @ISBN(type = ISBN.Type.ISBN_13)
    private String isbn13;

    private MultipartFile image;

    private Integer bookStatusId;

    private Integer bookLanguageId;

    private Integer bookFormatId;

    private Integer bookPublishingHouseId;

    private Integer bookSeriesId;

    private Long orderId;
}
