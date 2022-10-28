package com.it.academy.library.dto.create.author;

import com.it.academy.library.model.entity.author.Author;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link Author} entity.
 */
@Value
public class AuthorCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    String firstName;

    @NotBlank
    @Size(min = 3, max = 64)
    String lastName;

    @NotBlank
    String imagePath;

    Integer authorRoleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    LocalDate dateDeath;

    String description;
}
