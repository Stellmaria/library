package com.it.academy.library.dto.read.user;

import com.it.academy.library.model.entity.user.UserRole;
import lombok.Value;

/**
 * A DTO for the {@link UserRole} entity.
 */
@Value
public class UserRoleReadDto {
    Integer id;

    String name;
}
