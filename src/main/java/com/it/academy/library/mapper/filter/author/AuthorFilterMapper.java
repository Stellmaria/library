package com.it.academy.library.mapper.filter.author;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.service.dto.filter.author.AuthorFilter;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorFilterMapper implements Mapper<Author, AuthorFilter> {
    private final AuthorRoleFilterMapper authorRoleFilterMapper;

    @Override
    public AuthorFilter map(@NotNull Author object) {
        var authorRole = Optional.ofNullable(object.getAuthorRole())
                .map(authorRoleFilterMapper::map)
                .orElse(null);

        return new AuthorFilter(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                authorRole,
                object.getBirthday(),
                object.getDateDeath(),
                object.getDescription()
        );
    }
}
