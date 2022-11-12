package com.it.academy.library.mapper.read.author;

import com.it.academy.library.mapper.Mapper;
import com.it.academy.library.model.entity.author.AuthorRole;
import com.it.academy.library.service.dto.read.author.AuthorRoleReadDto;
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
