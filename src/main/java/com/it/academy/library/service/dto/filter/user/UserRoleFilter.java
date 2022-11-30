package com.it.academy.library.service.dto.filter.user;

import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.user.QUserRole.userRole;

/**
 * A DTO for the {@link UserRole} entity.
 */
@Getter
@AllArgsConstructor
public class UserRoleFilter {
    private Integer id;

    private String name;

    public static Predicate queryPredicates(@NotNull UserRoleFilter userRoleFilter) {
        return QueryPredicates.builder()
                .add(userRoleFilter.getId(), userRole.id::eq)
                .add(userRoleFilter.getName(), userRole.name::containsIgnoreCase)
                .build();
    }
}
