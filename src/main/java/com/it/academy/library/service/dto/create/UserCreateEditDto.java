package com.it.academy.library.service.dto.create;

import com.it.academy.library.model.entity.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldNameConstants;
import org.postgresql.util.LruCache;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {
    @NotBlank(message = "The username cannot be empty.")
    @Size(
            min = 3,
            max = 50,
            message = "The username cannot contain less than 1 character and more than 99 characters."
    )
    private String username;

    @NotBlank(message = "The first name cannot be empty.")
    @Size(
            min = 1,
            max = 99,
            message = "The first name cannot contain less than 1 character and more than 64 characters."
    )
    private String firstName;

    @NotBlank(message = "Last name cannot be empty.")
    @Size(
            min = 1,
            max = 99,
            message = "Last name cannot be less than 1 character and more than 64 characters."
    )
    private String lastName;

    @Email(message = "Email entered incorrectly.")
    @NotBlank(message = "Email cannot be empty.")
    @Size(
            min = 10,
            max = 50,
            message = "Email cannot contain less than 10 characters and more than 50 characters."
    )
    private String email;

    @NotBlank(
            groups = LruCache.CreateAction.class,
            message = "The password cannot be empty."
    )
    private String rawPassword;

    private Integer userRoleId;

    private Integer userStatusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private MultipartFile image;
}
