package com.it.academy.library.service.dto.read.author;

import com.it.academy.library.model.entity.author.AuthorRole;
import lombok.Value;

/**
 * A DTO for the {@link AuthorRole} entity.
 */
@Value
public class AuthorRoleReadDto {
    Integer id;

    String name;
}
