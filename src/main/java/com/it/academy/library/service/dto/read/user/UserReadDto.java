package com.it.academy.library.service.dto.read.user;

import com.it.academy.library.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserReadDto {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserRoleReadDto userRole;

    private UserStatusReadDto userStatus;

    private LocalDate birthday;

    private String image;
}
