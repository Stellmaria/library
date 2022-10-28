package com.it.academy.library.model.mapper.create.author;

import com.it.academy.library.dto.create.author.AuthorRoleCreateEditDto;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorRoleCreateEditMapper implements Mapper<AuthorRoleCreateEditDto, AuthorRole> {
    @Override
    public AuthorRole map(@NotNull AuthorRoleCreateEditDto object) {
        var authorRole = new AuthorRole();
        authorRole.setName(object.getName());
        return authorRole;
    }
}
