package com.it.academy.library.model.mapper.filter.author;

import com.it.academy.library.dto.filter.author.AuthorRoleFilter;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorRoleFilterMapper implements Mapper<AuthorRole, AuthorRoleFilter> {
    @Override
    public AuthorRoleFilter map(@NotNull AuthorRole object) {
        return new AuthorRoleFilter(object.getName());
    }
}
