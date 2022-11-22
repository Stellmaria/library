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
import java.time.LocalDate;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldNameConstants
public class UserCreateEditDto {
    private String username;

    private String firstName;

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
