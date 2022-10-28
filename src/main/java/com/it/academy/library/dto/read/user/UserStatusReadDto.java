package com.it.academy.library.dto.read.user;

import com.it.academy.library.model.entity.user.UserStatus;
import lombok.Value;

/**
 * A DTO for the {@link UserStatus} entity.
 */
@Value
public class UserStatusReadDto {
    Integer id;

    String name;
}
