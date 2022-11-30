package com.it.academy.library.service.dto.read.user;

import com.it.academy.library.model.entity.user.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO for the {@link UserRole} entity.
 */
@Data
@AllArgsConstructor
@Builder
public class UserRoleReadDto {
    private Integer id;

    private String name;
}
