package com.it.academy.library.service.dto.read.user;

import com.it.academy.library.model.entity.user.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * A DTO for the {@link UserStatus} entity.
 */
@Data
@AllArgsConstructor
@Builder
public class UserStatusReadDto {
    private Integer id;

    private String name;
}
