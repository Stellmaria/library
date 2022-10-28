package com.it.academy.library.model.mapper.read.author;

import com.it.academy.library.dto.read.author.AuthorReadDto;
import com.it.academy.library.dto.read.author.AuthorRoleReadDto;
import com.it.academy.library.model.entity.author.Author;
import com.it.academy.library.model.mapper.Mapper;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AuthorReadMapper implements Mapper<Author, AuthorReadDto> {
    private final AuthorRoleReadMapper authorRoleReadMapper;

    @Override
    public AuthorReadDto map(@NotNull Author object) {
        return new AuthorReadDto(
                object.getId(),
                object.getFirstName(),
                object.getLastName(),
                object.getImagePath(),
                getAuthorRole(object),
                object.getBirthday(),
                object.getDateDeath(),
                object.getDescription()
        );
    }

    private AuthorRoleReadDto getAuthorRole(@NotNull Author object) {
        return Optional.ofNullable(object.getAuthorRole())
                .map(authorRoleReadMapper::map)
                .orElse(null);
    }
}
