package com.it.academy.library.model.mapper.filter.author;

import com.it.academy.library.dto.filter.author.AuthorFilter;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorFilterMapper implements Mapper<Author, AuthorFilter> {
    @Override
    public AuthorFilter map(@NotNull Author object) {
        return new AuthorFilter(
                object.getFirstName(),
                object.getLastName(),
                object.getAuthorRole() != null ? object.getAuthorRole().getId() : null,
                object.getBirthday(),
                object.getDateDeath(),
                object.getDescription()
        );
    }
}
