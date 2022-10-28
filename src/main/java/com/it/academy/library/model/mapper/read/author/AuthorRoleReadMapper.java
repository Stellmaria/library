package com.it.academy.library.model.mapper.read.author;

import com.it.academy.library.dto.read.author.AuthorRoleReadDto;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.model.mapper.Mapper;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

@Component
public class AuthorRoleReadMapper implements Mapper<AuthorRole, AuthorRoleReadDto> {
    @Override
    public AuthorRoleReadDto map(@NotNull AuthorRole object) {
        return new AuthorRoleReadDto(
                object.getId(),
                object.getName()
        );
    }
}
