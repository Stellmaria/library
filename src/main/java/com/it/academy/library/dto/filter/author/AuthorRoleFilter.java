package com.it.academy.library.dto.filter.author;

import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import static com.it.academy.library.model.entity.author.QAuthorRole.authorRole;

/**
 * A DTO for the {@link AuthorRole} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorRoleFilter {
    private String name;

    public static Predicate queryPredicates(@NotNull AuthorRoleFilter authorRoleFilter) {
        return QueryPredicates.builder()
                .add(authorRoleFilter.getName(), authorRole.name::containsIgnoreCase)
                .build();
    }
}
