package com.it.academy.library.service.dto.filter;

import com.it.academy.library.model.entity.Author;
import com.it.academy.library.querydsl.QueryPredicates;
import com.querydsl.core.types.Predicate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

import static com.it.academy.library.model.entity.QAuthor.author;


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
                .build();
    }
}