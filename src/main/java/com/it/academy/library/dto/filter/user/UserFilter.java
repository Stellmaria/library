package com.it.academy.library.dto.filter.user;

import com.it.academy.library.model.entity.user.User;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Optional;

import static com.it.academy.library.model.entity.user.QUser.user;

/**
 * A DTO for the {@link User} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserFilter {
    private Long id;

    private String username;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserRoleFilter userRole;

    private UserStatusFilter userStatus;

    private LocalDate birthday;

    public static Predicate queryPredicates(@NotNull UserFilter userFilter) {
        return QueryPredicates.builder()
                .add(userFilter.getId(), user.id::eq)
                .add(userFilter.getUsername(), user.username::containsIgnoreCase)
                .add(userFilter.getFirstName(), user.firstName::containsIgnoreCase)
                .add(userFilter.getLastName(), user.lastName::containsIgnoreCase)
                .add(userFilter.getEmail(), user.email::containsIgnoreCase)
                .add(userFilter.getPassword(), user.password::containsIgnoreCase)
                .add(getUserRoleId(userFilter), user.userRole.id::eq)
                .add(getUserRoleName(userFilter), user.userRole.name::containsIgnoreCase)
                .add(getUserStatusId(userFilter), user.userStatus.id::eq)
                .add(getUserStatusName(userFilter), user.userStatus.name::containsIgnoreCase)
                .add(userFilter.getBirthday(), user.birthday::eq)
                .build();
    }

    @Nullable
    private static String getUserStatusName(@NotNull UserFilter userFilter) {
        return Optional.ofNullable(userFilter.getUserStatus())
                .map(UserStatusFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getUserStatusId(@NotNull UserFilter userFilter) {
        return Optional.ofNullable(userFilter.getUserStatus())
                .map(UserStatusFilter::getId)
                .orElse(null);
    }

    @Nullable
    private static String getUserRoleName(@NotNull UserFilter userFilter) {
        return Optional.ofNullable(userFilter.getUserRole())
                .map(UserRoleFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getUserRoleId(@NotNull UserFilter userFilter) {
        return Optional.ofNullable(userFilter.getUserRole())
                .map(UserRoleFilter::getId)
                .orElse(null);
    }
}
