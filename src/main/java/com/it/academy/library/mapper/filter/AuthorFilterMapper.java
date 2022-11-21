package com.it.academy.library.mapper.filter;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.Author;
import com.it.academy.library.service.dto.filter.AuthorFilter;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorFilterMapper implements Mapper<Author, AuthorFilter> {
    @Override
    public AuthorFilter map(@NotNull Author object) {
        return new AuthorFilter(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getBirthday(),
                object.getDateDeath(),
                object.getDescription()
        );
    }
}
