package com.it.academy.library.dto.filter.user;

import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.it.academy.library.model.entity.user.QUser.user;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFilter {
    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private Integer userRoleId;

    private Integer userStatusId;

    private LocalDate birthday;

    public static Predicate queryPredicates(@NotNull UserFilter userFilter) {
        return QueryPredicates.builder()
                .add(userFilter.getLogin(), user.login::containsIgnoreCase)
                .add(userFilter.getFirstName(), user.firstName::containsIgnoreCase)
                .add(userFilter.getLastName(), user.lastName::containsIgnoreCase)
                .add(userFilter.getEmail(), user.email::containsIgnoreCase)
                .add(userFilter.getPassword(), user.password::containsIgnoreCase)
                .add(userFilter.getUserRoleId(), user.userRole.id::eq)
                .add(userFilter.getUserStatusId(), user.userStatus.id::eq)
                .add(userFilter.getBirthday(), user.birthday::eq)
                .build();
    }
}
