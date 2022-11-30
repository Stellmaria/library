package com.it.academy.library.service.dto.filter.user;

import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.user.QUserStatus.userStatus;

/**
 * A DTO for the {@link UserStatus} entity.
 */
@Getter
@AllArgsConstructor
public class UserStatusFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull UserStatusFilter userStatusFilter) {
        return QueryPredicates.builder()
                .add(userStatusFilter.getId(), userStatus.id::eq)
                .add(userStatusFilter.getName(), userStatus.name::containsIgnoreCase)
                .build();
    }
}
