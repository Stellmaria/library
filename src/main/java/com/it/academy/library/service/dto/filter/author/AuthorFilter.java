package com.it.academy.library.service.dto.filter.author;

import com.it.academy.library.model.entity.author.Author;
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

import static com.it.academy.library.model.entity.author.QAuthor.author;

/**
 * A DTO for the {@link Author} entity.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorFilter {
    private Long id;

    private String firstName;

    private String lastName;

    private AuthorRoleFilter authorRole;

    private LocalDate birthday;

    private LocalDate dateDeath;

    private String description;

    public static Predicate queryPredicates(@NotNull AuthorFilter authorFilter) {
        return QueryPredicates.builder()
                .add(authorFilter.getId(), author.id::eq)
                .add(authorFilter.getFirstName(), author.firstName::containsIgnoreCase)
                .add(authorFilter.getLastName(), author.lastName::containsIgnoreCase)
                .add(authorFilter.getBirthday(), author.birthday::eq)
                .add(authorFilter.getDateDeath(), author.dateDeath::eq)
                .add(authorFilter.getDescription(), author.description::containsIgnoreCase)
                .add(getAuthorRoleId(authorFilter), author.authorRole.id::eq)
                .add(getAuthorRoleName(authorFilter), author.authorRole.name::containsIgnoreCase)
                .build();
    }

    @Nullable
    private static String getAuthorRoleName(@NotNull AuthorFilter authorFilter) {
        return Optional.ofNullable(authorFilter.getAuthorRole())
                .map(AuthorRoleFilter::getName)
                .orElse(null);
    }

    @Nullable
    private static Integer getAuthorRoleId(@NotNull AuthorFilter authorFilter) {
        return Optional.ofNullable(authorFilter.getAuthorRole())
                .map(AuthorRoleFilter::getId)
                .orElse(null);
    }
}
