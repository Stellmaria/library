package com.it.academy.library.dto.create.user;

import com.it.academy.library.model.entity.user.User;
import lombok.Value;
import lombok.experimental.FieldNameConstants;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

/**
 * A DTO for the {@link User} entity.
 */
@Value
@FieldNameConstants
public class UserCreateEditDto {
    @Size(min = 3, max = 50)
    @NotBlank
    String login;

    @Size(min = 3, max = 99)
    @NotBlank
    String firstName;

    @Size(min = 3, max = 99)
    @NotBlank
    String lastName;

    @Email
    String email;

    @NotBlank
    @Size(min = 6, max = 32)
    String password;

    Integer userRoleId;

    Integer userStatusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    LocalDate birthday;

    MultipartFile image;
}
