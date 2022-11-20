package com.it.academy.library.service.dto.read.author;

import com.it.academy.library.model.entity.author.Author;
import lombok.Value;

import java.time.LocalDate;

/**
 * A DTO for the {@link Author} entity.
 */
@Value
public class AuthorReadDto {
    Long id;

    String firstName;

    String lastName;

    String image;

    LocalDate birthday;

    LocalDate dateDeath;

    String description;
}
