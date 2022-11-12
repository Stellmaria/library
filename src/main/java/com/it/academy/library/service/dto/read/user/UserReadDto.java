package com.it.academy.library.service.dto.read.user;

import com.it.academy.library.model.entity.user.User;
import lombok.Value;

import java.time.LocalDate;

/**
 * A DTO for the {@link User} entity.
 */
@Value
public class UserReadDto {
    Long id;

    String username;

    String firstName;

    String lastName;

    String email;

    String password;

    UserRoleReadDto userRole;

    UserStatusReadDto userStatus;

    LocalDate birthday;

    String image;
}