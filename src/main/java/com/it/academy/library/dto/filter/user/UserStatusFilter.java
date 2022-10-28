package com.it.academy.library.dto.filter.user;

import com.it.academy.library.model.entity.user.UserStatus;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.user.QUserStatus.userStatus;

/**
 * A DTO for the {@link UserStatus} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserStatusFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull UserStatusFilter userStatusFilter) {
        return QueryPredicates.builder()
                .add(userStatusFilter.getName(), userStatus.name::containsIgnoreCase)
                .build();
    }
}
