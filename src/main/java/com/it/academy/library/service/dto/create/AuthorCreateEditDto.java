package com.it.academy.library.service.dto.create;

import com.it.academy.library.model.entity.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link Author} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateEditDto {
    @NotBlank
    @Size(min = 3, max = 64)
    private String firstName;

    @NotBlank
    @Size(min = 3, max = 64)
    private String lastName;

    private MultipartFile image;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeath;

    private String description;
}