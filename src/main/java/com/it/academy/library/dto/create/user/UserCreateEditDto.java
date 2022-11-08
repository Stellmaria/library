package com.it.academy.library.dto.create.user;

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
    @Size(min = 3, max = 50)
    @NotBlank
    private String username;

    @Size(min = 3, max = 99)
    @NotBlank
    private String firstName;

    @Size(min = 3, max = 99)
    @NotBlank
    private String lastName;

    @Email
    private String email;

    @NotBlank(groups = LruCache.CreateAction.class)
    private String rawPassword;

    private Integer userRoleId;

    private Integer userStatusId;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;

    private MultipartFile image;
}
