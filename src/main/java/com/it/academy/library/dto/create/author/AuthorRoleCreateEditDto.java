package com.it.academy.library.dto.create.author;

import com.it.academy.library.model.entity.author.AuthorRole;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link AuthorRole} entity.
 */
@Value
public class AuthorRoleCreateEditDto {
    @Size(min = 3, max = 64)
    @NotBlank
    String name;
}
