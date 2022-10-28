package com.it.academy.library.dto.filter.user;

import com.it.academy.library.model.entity.user.UserRole;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.user.QUserRole.userRole;

/**
 * A DTO for the {@link UserRole} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRoleFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull UserRoleFilter userRoleFilter) {
        return QueryPredicates.builder()
                .add(userRoleFilter.getName(), userRole.name::containsIgnoreCase)
                .build();
    }
}
