package com.it.academy.library.service.dto.read;

import com.it.academy.library.model.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A DTO for the {@link Author} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorReadDto {
    private Long id;

    private String firstName;

    private String lastName;

    private String image;

    private LocalDate birthday;

    private LocalDate dateDeath;

    private String description;
}
