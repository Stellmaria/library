package com.it.academy.library.service.dto.create.book;

import com.it.academy.library.model.entity.book.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.ISBN;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Collection;

/**
 * A DTO for the {@link Book} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookCreateEditDto {
    @NotBlank(message = "The title must not be empty.")
    private String title;

    private String subtitle;

    @Positive(message = "The year must not be negative number.")
    private Integer year;

    @Positive(message = "The number must not be negative number.")
    private Long quantity;

    @Positive(message = "Pages must not be a negative number.")
    private Short pages;

    @ISBN(
            type = ISBN.Type.ISBN_10,
            message = "ISBN 10 incorrect."
    )
    private String isbn10;

    @ISBN(
            type = ISBN.Type.ISBN_13,
            message = "ISBN 13 incorrect."
    )
    private String isbn13;

    private MultipartFile image;

    private Integer bookStatusId;

    private Integer bookLanguageId;

    private Integer bookFormatId;

    private Integer bookPublishingHouseId;

    private Integer bookSeriesId;

    private Long orderId;

    @NotNull(message = "A book must have at least one author.")
    private Collection<Long> authorsId;

    @NotNull(message = "The book must have at least one genre.")
    private Collection<Integer> genresId;
}
