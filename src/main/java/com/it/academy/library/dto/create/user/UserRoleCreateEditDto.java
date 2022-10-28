package com.it.academy.library.dto.create.user;

import com.it.academy.library.model.entity.user.UserRole;
import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link UserRole} entity.
 */
@Value
public class UserRoleCreateEditDto {
    @Size(min = 3, max = 64)
    @NotBlank
    String name;
}
