package com.it.academy.library.service.dto.create.author;

import com.it.academy.library.model.entity.author.Author;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * A DTO for the {@link Author} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorCreateEditDto {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    private MultipartFile imagePath;

    private Integer authorRoleId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateDeath;

    private String description;
}
